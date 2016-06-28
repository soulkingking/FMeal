package com.fragrantmeal.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.fragrantmeal.App;
import com.fragrantmeal.EventBus.EventBusSelect;
import com.fragrantmeal.R;
import com.fragrantmeal.adapter.OrderPageAdapter;
import com.fragrantmeal.adapter.OrderSellerAdapter;
import com.fragrantmeal.entity.BuyOrder;
import com.fragrantmeal.entity.Car;
import com.fragrantmeal.entity.DeliveryAddress;
import com.fragrantmeal.entity.OrderCar;
import com.fragrantmeal.entity.Seller;
import com.fragrantmeal.entity.SellerDish;
import com.fragrantmeal.entity.TakeoutOrder;
import com.fragrantmeal.entity.TakeoutOrderStatus;
import com.fragrantmeal.entity.UserInfo;
import com.fragrantmeal.util.Config;
import com.fragrantmeal.util.GsonUtil;
import com.fragrantmeal.util.ShowToast;
import com.fragrantmeal.util.StringHandler;
import com.mj.core.util.IntentUtils;
import com.mj.core.util.SharedPrefsUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import rx.android.app.AppObservable;

/**
 * Created by CaoBin on 2016/4/24.
 */
public class OrderActivity extends Activity implements ViewPager.OnPageChangeListener {

    @InjectView(R.id.btn_back)
    Button btnBack;
    @InjectView(R.id.tv_order_info)
    TextView tvOrderInfo;
    @InjectView(R.id.tv_order_status)
    TextView tvOrderStatus;
    @InjectView(R.id.ll_1)
    LinearLayout ll1;
    @InjectView(R.id.ll_2)
    LinearLayout ll2;
    @InjectView(R.id.vp_order)
    ViewPager vpOrder;


    private List<View> views;
    private OrderPageAdapter orderPageAdapter;
    private Intent intent;
    private Map<SellerDish,Integer> cars;
    private Seller seller;
    private OrderCar orderCar;
    private TextView order_seller_name;
    private ListView order_seller_dish;
    private TextView order_seller_df;
    private TextView order_seller_notice;
    private TextView order_seller_zong;
    private TextView order_seller_youhui;
    private TextView order_seller_shifu;
    private TextView order_address;
    private Button btn_buy;
    private DeliveryAddress deliveryAddress;
    private TextView order_people;
    private TextView order_phone;
    private List<Car> carList;
    private OrderSellerAdapter orderSellerAdapter;
    private EditText et_extra;
    private UserInfo user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderinfo);
        ButterKnife.inject(this);
        EventBus.getDefault().register(this);
        initView();
    }

    public void initView(){
        views=new ArrayList<>();
        carList=new ArrayList<>();
        user = (UserInfo) GsonUtil.fromJson(SharedPrefsUtil.getString(this, "LoginUser", "user"), UserInfo.class);
        views.add(LayoutInflater.from(this).inflate(R.layout.vp_order_infor,null));
//        views.add(LayoutInflater.from(this).inflate(R.layout.vp_order_status,null));
        orderPageAdapter=new OrderPageAdapter(views);
        vpOrder.setAdapter(orderPageAdapter);
        vpOrder.setOnPageChangeListener(this);
        vpOrder.setCurrentItem(0);
        initOrderInfo();
        initData();

    }



    public void initData(){
        cars=new HashMap<>();
        intent=getIntent();
        orderCar=(OrderCar)intent.getSerializableExtra("order");
        cars=orderCar.getCars();
        seller=orderCar.getSeller();
        for (SellerDish sellerDish : cars.keySet()) {
            if (cars.get(sellerDish)>0){
                Car car=new Car();
                car.setSellerDish(sellerDish);
                car.setNum(cars.get(sellerDish));
                carList.add(car);
            }
        }
        orderSellerAdapter=new OrderSellerAdapter(this,carList);
        order_seller_dish.setAdapter(orderSellerAdapter);
        setListViewHeightBasedOnChildren(order_seller_dish);
        setData();
    }


    @OnClick({R.id.btn_back, R.id.tv_order_info})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back :
                finish();
                break;
//            case R.id.tv_order_info:
//                tvOrderInfo.setTextColor(getResources().getColor(R.color.login_bg));
//                tvOrderStatus.setTextColor(getResources().getColor(R.color.text_gay));
//                ll1.setVisibility(View.VISIBLE);
//                ll2.setVisibility(View.INVISIBLE);
//                vpOrder.setCurrentItem(0);
//                break;
//            case R.id.tv_order_status:
//                tvOrderStatus.setTextColor(getResources().getColor(R.color.login_bg));
//                tvOrderInfo.setTextColor(getResources().getColor(R.color.text_gay));
//                ll2.setVisibility(View.VISIBLE);
//                ll1.setVisibility(View.INVISIBLE);
//                vpOrder.setCurrentItem(1);
//                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
//        switch (position){
//            case 0:
//                tvOrderInfo.setTextColor(getResources().getColor(R.color.login_bg));
//                tvOrderStatus.setTextColor(getResources().getColor(R.color.text_gay));
//                ll1.setVisibility(View.VISIBLE);
//                ll2.setVisibility(View.INVISIBLE);
//                break;
//            case 1:
//                tvOrderStatus.setTextColor(getResources().getColor(R.color.login_bg));
//                tvOrderInfo.setTextColor(getResources().getColor(R.color.text_gay));
//                ll2.setVisibility(View.VISIBLE);
//                ll1.setVisibility(View.INVISIBLE);
//                break;
//        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void initOrderInfo() {
        order_seller_name= (TextView) views.get(0).findViewById(R.id.order_seller_name);
        order_seller_dish= (ListView) views.get(0).findViewById(R.id.order_seller_dish);
        order_seller_df= (TextView) views.get(0).findViewById(R.id.order_seller_df);
        order_seller_notice= (TextView) views.get(0).findViewById(R.id.order_seller_notice);
        order_seller_zong= (TextView) views.get(0).findViewById(R.id.order_seller_zong);
        order_seller_youhui= (TextView) views.get(0).findViewById(R.id.order_seller_youhui);
        order_seller_shifu= (TextView) views.get(0).findViewById(R.id.order_seller_shifu);
        order_address= (TextView) views.get(0).findViewById(R.id.order_address);
        order_people= (TextView) views.get(0).findViewById(R.id.order_people);
        order_phone= (TextView) views.get(0).findViewById(R.id.order_phone);
        btn_buy= (Button) views.get(0).findViewById(R.id.btn_buy);
        et_extra= (EditText) views.get(0).findViewById(R.id.et_extra);

        order_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.forward(OrderActivity.this,AddressActivity.class);
            }
        });

        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bulidOrder();
            }
        });
    }

    public void onEventMainThread(EventBusSelect event) {
        switch (event.getActionType()){
            case Config.ORDER_ADDRESS:
                deliveryAddress= (DeliveryAddress) GsonUtil.fromJson(event.getMessage(),DeliveryAddress.class);
                String address1=deliveryAddress.getDa_address().substring(0, deliveryAddress.getDa_address().indexOf("*"));
                String address2=deliveryAddress.getDa_address().substring(deliveryAddress.getDa_address().indexOf("*")+ 1);
                order_address.setText(address1+address2);
                order_phone.setText(deliveryAddress.getDa_phone());
                order_people.setText(deliveryAddress.getDa_name());
                break;
        }

    }

    public void setData(){
        orderSellerAdapter.setCars(carList);
        setListViewHeightBasedOnChildren(order_seller_dish);
        order_seller_name.setText(seller.getSeller_name());
        order_seller_df.setText("￥" + seller.getSeller_df());
        int price=0;
        for (Car car : carList) {
            price=price+(car.getSellerDish().getSd_price()*car.getNum());
        }
        order_seller_zong.setText("总计￥"+price);
        String notice=seller.getSeller_notice();
        String man=notice.substring((notice.indexOf("满") + 1), notice.indexOf("减"));
        String jian=notice.substring((notice.indexOf("减")+1),notice.lastIndexOf("元"));
        if (price>=Integer.parseInt(man)){
            order_seller_notice.setText("-￥"+jian);
            order_seller_youhui.setText("优惠￥"+jian);
            order_seller_shifu.setText("￥" + (price-Integer.parseInt(jian)+seller.getSeller_df()));
        }else {
            order_seller_notice.setText("-￥0");
            order_seller_youhui.setText("优惠￥0");
            order_seller_shifu.setText("￥" +(price+seller.getSeller_df()));
        }
    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }

    public void bulidOrder(){
        if (!order_address.getText().toString().trim().equals("")){
            BuyOrder order=new BuyOrder();
            String to_id= StringHandler.createSerialId(System.currentTimeMillis());
            order.setCars(carList);
            order.setSeller(seller);
            TakeoutOrder takeoutOrder=new TakeoutOrder();
            takeoutOrder.setS_id(seller.getS_id());
            takeoutOrder.setU_id(user.getU_id());
            takeoutOrder.setSeller_name(seller.getSeller_name());
            takeoutOrder.setTo_address(order_address.getText().toString().trim());
            takeoutOrder.setTo_boxFee(0);
            String df=order_seller_df.getText().toString().trim();
            takeoutOrder.setTo_deliveryFee(Integer.parseInt(df.substring(df.indexOf("￥") + 1)));
            takeoutOrder.setTo_extra(et_extra.getText().toString().trim());
            takeoutOrder.setTo_id(to_id);
            takeoutOrder.setTo_name(order_people.getText().toString().trim());
            takeoutOrder.setTo_phone(order_phone.getText().toString().trim());
            takeoutOrder.setUser_alias(user.getUser_alias());
            order.setTakeoutOrder(takeoutOrder);
            TakeoutOrderStatus takeoutOrderStatus=new TakeoutOrderStatus();
            takeoutOrderStatus.setTo_id(to_id);
            takeoutOrderStatus.setTos_id(StringHandler.createSerialId(System.currentTimeMillis()));
            takeoutOrderStatus.setTos_status("未支付");
            takeoutOrderStatus.setU_id(user.getU_id());
            takeoutOrderStatus.setUser_alias(user.getUser_alias());
            SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String date=sDateFormat.format(new java.util.Date());
            takeoutOrderStatus.setTos_time(date);
            order.setTakeoutOrderStatus(takeoutOrderStatus);
            sendOrder(order);
        }else {
            ShowToast.showToast(this,"请先选择配送的地址");
        }

    }


    public void sendOrder(BuyOrder order){
        AppObservable.bindActivity(OrderActivity.this,App.getfMealNet().sendOrder(order)).subscribe(
                success ->{
                    EventBus.getDefault().post(new EventBusSelect(Config.SEND_ORDER, GsonUtil.toJson(success)));
                    btn_buy.setEnabled(false);
                    order_address.setEnabled(false);
                    btn_buy.setText(success.getTakeoutOrderStatus().getTos_status());
                    finish();
                },
                e ->{

                }
        );


    }
}
