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
import android.widget.LinearLayout;

import com.baidu.location.BDLocation;
import com.fragrantmeal.App;
import com.fragrantmeal.EventBus.EventBusSelect;
import com.fragrantmeal.R;
import com.fragrantmeal.activity.SellerDishActivity;
import com.fragrantmeal.activity.SellerMentyActivity;
import com.fragrantmeal.adapter.HeadPageAdapter;
import com.fragrantmeal.adapter.SellerAdapter;
import com.fragrantmeal.entity.Seller;
import com.fragrantmeal.util.Config;
import com.fragrantmeal.util.GsonUtil;
import com.fragrantmeal.util.ShowToast;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.IconHintView;
import com.mj.core.util.L;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
public class SellerFragment extends Fragment implements
        WaterDropListView.IWaterDropListViewListener, AdapterView.OnItemClickListener, View.OnClickListener {
    @InjectView(R.id.lv_seller)
    WaterDropListView lvSeller;

    private View view;
    private List<Seller> sellers;
    private List<Seller> list;
    private SellerAdapter sellerAdapter;
    private BDLocation location;
    private int current=5;
    private Intent intent;
    private List<String> imgs;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1 :
                    lvSeller.stopRefresh();
                    current=5;
                    getData();
                    break;
                case 2 :
                    lvSeller.stopLoadMore();
                    if (list.size()-(current+5)>=5){
                        for (;current<current+5;current++){
                            sellers.add(list.get(current));
                        }
                    }else if (list.size()-current<=0){
                        ShowToast.showToast(getActivity(),"没有数据了");
                    }else {
                        for (;current<list.size();current++){
                            sellers.add(list.get(current));
                        }
                    }
                    sellerAdapter.setSellers(sellers);
                    break;
            }

        }
    };

    private View head_view;
    private RollPagerView rollPagerView;
    private HeadPageAdapter headPageAdapter;
    private LinearLayout ll_meishi;
    private LinearLayout ll_chaoshi;
    private LinearLayout ll_xianguo;
    private LinearLayout ll_yinliao;
    private LinearLayout ll_zhuansong;
    private LinearLayout ll_xianhua;
    private LinearLayout ll_dangao;
    private LinearLayout ll_yaoping;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_seller, container, false);
        }
        ButterKnife.inject(this, view);
        EventBus.getDefault().register(this);
        initData();
        return view;
    }

    public void initData(){
        sellers=new ArrayList<>();
        list=new ArrayList<>();
        imgs=new ArrayList<>();
        head_view=LayoutInflater.from(getActivity()).inflate(R.layout.head_view,null);
        initHeadView();
        rollPagerView= (RollPagerView) head_view.findViewById(R.id.roll_view_pager);
        rollPagerView.setPlayDelay(3000);
        rollPagerView.setAnimationDurtion(500);
        headPageAdapter=new HeadPageAdapter(rollPagerView, getActivity(), imgs);
        rollPagerView.setAdapter(headPageAdapter);
        rollPagerView.setHintView(new IconHintView(getActivity(), R.drawable.point_focus, R.drawable.point_normal));
        lvSeller.addHeaderView(head_view);
        sellerAdapter=new SellerAdapter(getActivity(),sellers);
        lvSeller.setAdapter(sellerAdapter);
        lvSeller.setWaterDropListViewListener(this);
        lvSeller.setOnItemClickListener(this);
        lvSeller.setPullLoadEnable(true);

    }

    private void initHeadView() {
        ll_meishi= (LinearLayout) head_view.findViewById(R.id.ll_meishi);
        ll_chaoshi= (LinearLayout) head_view.findViewById(R.id.ll_chaoshi);
        ll_xianguo= (LinearLayout) head_view.findViewById(R.id.ll_xianguo);
        ll_yinliao= (LinearLayout) head_view.findViewById(R.id.ll_yinliao);
        ll_zhuansong= (LinearLayout) head_view.findViewById(R.id.ll_zhuansong);
        ll_xianhua= (LinearLayout) head_view.findViewById(R.id.ll_xianhua);
        ll_dangao= (LinearLayout) head_view.findViewById(R.id.ll_dangao);
        ll_yaoping= (LinearLayout) head_view.findViewById(R.id.ll_yaoping);
        ll_meishi.setOnClickListener(this);
        ll_chaoshi.setOnClickListener(this);
        ll_xianguo.setOnClickListener(this);
        ll_yinliao.setOnClickListener(this);
        ll_zhuansong.setOnClickListener(this);
        ll_xianhua.setOnClickListener(this);
        ll_dangao.setOnClickListener(this);
        ll_yaoping.setOnClickListener(this);


    }

    private void getData(){
//        AppObservable.bindFragment(this,App.getfMealNet().findAll(location.getLongitude(),location.getLatitude())).subscribe(
//                result -> {
//                    sellerAdapter.setSellers(result);
//                },
//                error -> {
//
//
//                }
//        );
        AppObservable.bindFragment(this, App.getfMealNet().getImage()).subscribe(
                success -> {
                    headPageAdapter.setImgs(success);
                },
                e -> {

                }
        );

        AppObservable.bindFragment(this,App.getfMealNet().findAll(location.getLongitude(),location.getLatitude())).subscribe(
                result -> {
                    sellers.clear();
                    Collections.reverse(result);
                    list = result;
                    if (result.size() >= 5) {
                        for (int i = 0; i < 5; i++) {
                            sellers.add(result.get(i));
                        }
                    } else {
                        for (int i = 0; i < result.size(); i++) {
                            sellers.add(result.get(i));
                        }
                    }
                    sellerAdapter.setSellers(sellers);
                },
                error -> {
                    current = 5;
                }
        );
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
        if (event.getActionType().equals(Config.LOCATION)) {
            location = (BDLocation) GsonUtil.fromJson(event.getMessage(), BDLocation.class);
            Config.location=new BDLocation();
            Config.location.setLongitude(location.getLongitude());
            Config.location.setLatitude(location.getLatitude());
            getData();
            App.mLocationClient.stop();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        L.e("点了"+position);
        if (position>1){
            intent=new Intent(getActivity(), SellerDishActivity.class);
            Seller seller= sellerAdapter.getSellers().get(position-2);
            intent.putExtra("sellerInfo",seller);
            startActivity(intent);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_meishi:
                intent=new Intent(getActivity(),SellerMentyActivity.class);
                intent.putExtra("titleName","美食");
                startActivity(intent);
                break;
            case R.id.ll_chaoshi:
                ShowToast.showToast(getActivity(),"正在开发,敬请期待");
                break;
            case R.id.ll_xianguo:
                intent=new Intent(getActivity(),SellerMentyActivity.class);
                intent.putExtra("titleName","鲜果购");
                startActivity(intent);
                break;
            case R.id.ll_yinliao:
                intent=new Intent(getActivity(),SellerMentyActivity.class);
                intent.putExtra("titleName","甜品饮料");
                startActivity(intent);
                break;
            case R.id.ll_zhuansong:
                ShowToast.showToast(getActivity(),"正在开发,敬请期待");
                break;
            case R.id.ll_xianhua:
                ShowToast.showToast(getActivity(),"正在开发,敬请期待");
                break;
            case R.id.ll_dangao:
                ShowToast.showToast(getActivity(),"正在开发,敬请期待");
                break;
            case R.id.ll_yaoping:
                ShowToast.showToast(getActivity(),"正在开发,敬请期待");
                break;
        }
    }
}
