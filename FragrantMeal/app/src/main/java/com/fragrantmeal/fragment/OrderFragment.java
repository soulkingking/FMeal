package com.fragrantmeal.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.fragrantmeal.App;
import com.fragrantmeal.EventBus.EventBusSelect;
import com.fragrantmeal.R;
import com.fragrantmeal.activity.OrderActivity;
import com.fragrantmeal.activity.OrderInfoActivity;
import com.fragrantmeal.adapter.OrderAdapter;
import com.fragrantmeal.entity.BuyOrder;
import com.fragrantmeal.entity.Car;
import com.fragrantmeal.entity.OrderCar;
import com.fragrantmeal.entity.SellerDish;
import com.fragrantmeal.entity.UserInfo;
import com.fragrantmeal.util.Config;
import com.fragrantmeal.util.GsonUtil;
import com.fragrantmeal.util.ShowToast;
import com.mj.core.util.SharedPrefsUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.greenrobot.event.EventBus;
import medusa.theone.waterdroplistview.view.WaterDropListView;
import rx.android.app.AppObservable;

/**
 * Created by CaoBin on 2016/3/20.
 */
public class OrderFragment extends Fragment implements AdapterView.OnItemClickListener, WaterDropListView.IWaterDropListViewListener {
    @InjectView(R.id.lv_order)
    WaterDropListView lvOrder;

    private View view;
    private List<BuyOrder> orders;
    private OrderAdapter orderAdapter;
    private UserInfo user;
    private List<BuyOrder> orderList;
    private int current=10;
    private Intent intent;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1 :
                    lvOrder.stopRefresh();
                    current=10;
                    getData();
                    break;
                case 2 :
                    lvOrder.stopLoadMore();
                    if (orderList.size()-(current+10)>=10){
                        for (;current<current+10;current++){
                            orders.add(orderList.get(current));
                        }
                    }else if (orderList.size()-current<=0){
                        ShowToast.showToast(getActivity(), "没有数据了");
                    }else {
                        for (;current<orderList.size();current++){
                            orders.add(orderList.get(current));
                        }
                    }
                    orderAdapter.setOrders(orderList);
                    break;
            }

        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_order, container, false);
        }
        ButterKnife.inject(this, view);
        EventBus.getDefault().register(this);
        initData();
        return view;
    }

    public void initData(){
        user = (UserInfo) GsonUtil.fromJson(SharedPrefsUtil.getString(getActivity(), "LoginUser", "user"), UserInfo.class);
        orders=new ArrayList<>();
        orderList=new ArrayList<>();
        orderAdapter=new OrderAdapter(getActivity(),orders);
        lvOrder.setAdapter(orderAdapter);
        lvOrder.setOnItemClickListener(this);
        lvOrder.setWaterDropListViewListener(this);
        lvOrder.setPullLoadEnable(true);
        getData();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onRefresh() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            try {
                Thread.sleep(2000);
                handler.sendEmptyMessage(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void onLoadMore() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            try {
                Thread.sleep(2000);
                handler.sendEmptyMessage(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public void onEventMainThread(EventBusSelect event) {
        switch (event.getActionType()){
            case Config.BUY_CAR_ARGO:
                BuyOrder order= (BuyOrder) GsonUtil.fromJson(event.getMessage(),BuyOrder.class);
                OrderCar orderCar=new OrderCar();
                Map<SellerDish,Integer> cars=new HashMap<>();
                List<Car> list=order.getCars();
                for (Car car : list) {
                    cars.put(car.getSellerDish(),car.getNum());
                }
                orderCar.setCars(cars);
                orderCar.setSeller(order.getSeller());
                intent=new Intent(getActivity(), OrderActivity.class);
                intent.putExtra("order",orderCar);
                getActivity().startActivity(intent);
                break;
            case Config.BUY_CAR_INFO:
                BuyOrder orderInfo= (BuyOrder) GsonUtil.fromJson(event.getMessage(),BuyOrder.class);
                intent=new Intent(getActivity(), OrderInfoActivity.class);
                intent.putExtra("buyOrder",orderInfo);
                getActivity().startActivity(intent);
                break;

            case Config.SEND_ORDER:
                getData();
                break;
            case Config.updateStatus:
                getData();
                break;
        }
    }

    private void getData(){
        AppObservable.bindFragment(this, App.getfMealNet().finUserOrder(user.getU_id())).subscribe(
                result -> {
                    orders.clear();
                    Collections.reverse(result);
                    orderList = result;
                    if (result.size() >= 10) {
                        for (int i = 0; i < 10; i++) {
                            orders.add(result.get(i));
                        }
                    } else {
                        for (int i = 0; i < result.size(); i++) {
                            orders.add(result.get(i));
                        }
                    }
                    orderAdapter.setOrders(orders);
                },
                error -> {
                    current=10;
                }
        );
    }
}
