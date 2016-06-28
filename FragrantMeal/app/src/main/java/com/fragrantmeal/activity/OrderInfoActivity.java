package com.fragrantmeal.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.fragrantmeal.App;
import com.fragrantmeal.EventBus.EventBusSelect;
import com.fragrantmeal.R;
import com.fragrantmeal.adapter.OrderPageAdapter;
import com.fragrantmeal.adapter.OrderSellerAdapter;
import com.fragrantmeal.entity.BuyOrder;
import com.fragrantmeal.entity.Car;
import com.fragrantmeal.entity.Seller;
import com.fragrantmeal.entity.SellerDish;
import com.fragrantmeal.entity.TakeoutOrder;
import com.fragrantmeal.entity.TakeoutOrderStatus;
import com.fragrantmeal.entity.UserInfo;
import com.fragrantmeal.util.Config;
import com.fragrantmeal.util.GsonUtil;
import com.fragrantmeal.util.ShowToast;
import com.mj.core.util.L;
import com.mj.core.util.SharedPrefsUtil;

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
public class OrderInfoActivity extends Activity implements ViewPager.OnPageChangeListener {

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
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.btn_phone)
    Button btnPhone;


    private List<View> views;
    private OrderPageAdapter orderPageAdapter;
    private Intent intent;
    private Map<SellerDish, Integer> cars;
    private Seller seller;
    private TextView order_seller_name;
    private ListView order_seller_dish;
    private TextView order_seller_df;
    private TextView order_seller_notice;
    private TextView order_seller_zong;
    private TextView order_seller_youhui;
    private TextView order_seller_shifu;
    private TextView order_address;
    private Button btn_buy;
    private TextView order_people;
    private TextView order_phone;
    private List<Car> carList;
    private OrderSellerAdapter orderSellerAdapter;
    private TextView et_extra;
    private UserInfo user;
    private BuyOrder order;
    private TextView order_id;
    private TextView order_time;
    private TakeoutOrder takeoutOrder;
    private TakeoutOrderStatus takeoutOrderStatus;
    private Thread thread;
    private TextView text1;
    private TextView text2;
    private TextView text3;
    private TextView text4;
    private boolean flag = false;
    private EditText et_pay;
    private String shifu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.inject(this);
        EventBus.getDefault().register(this);
        initView();
    }

    public void initView() {
        views = new ArrayList<>();
        carList = new ArrayList<>();
        user = (UserInfo) GsonUtil.fromJson(SharedPrefsUtil.getString(this, "LoginUser", "user"), UserInfo.class);
        views.add(LayoutInflater.from(this).inflate(R.layout.vp_order, null));
        views.add(LayoutInflater.from(this).inflate(R.layout.vp_order_status, null));
        orderPageAdapter = new OrderPageAdapter(views);
        vpOrder.setAdapter(orderPageAdapter);
        vpOrder.setOnPageChangeListener(this);
        vpOrder.setCurrentItem(0);
        initOrderInfo();
        initData();
        setData();

    }


    public void initData() {
        cars = new HashMap<>();
        intent = getIntent();
        order = (BuyOrder) intent.getSerializableExtra("buyOrder");
        seller = order.getSeller();
        carList = order.getCars();
        for (Car car : carList) {
            cars.put(car.getSellerDish(), car.getNum());
        }
        takeoutOrderStatus = order.getTakeoutOrderStatus();
        takeoutOrder = order.getTakeoutOrder();
        orderSellerAdapter = new OrderSellerAdapter(this, carList);
        order_seller_dish.setAdapter(orderSellerAdapter);
        setListViewHeightBasedOnChildren(order_seller_dish);
        setCount();
    }


    @OnClick({R.id.btn_back, R.id.tv_order_info, R.id.tv_order_status,R.id.btn_phone})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.tv_order_info:
                tvOrderInfo.setTextColor(getResources().getColor(R.color.login_bg));
                tvOrderStatus.setTextColor(getResources().getColor(R.color.text_gay));
                ll1.setVisibility(View.VISIBLE);
                ll2.setVisibility(View.INVISIBLE);
                vpOrder.setCurrentItem(0);
                break;
            case R.id.tv_order_status:
                tvOrderStatus.setTextColor(getResources().getColor(R.color.login_bg));
                tvOrderInfo.setTextColor(getResources().getColor(R.color.text_gay));
                ll2.setVisibility(View.VISIBLE);
                ll1.setVisibility(View.INVISIBLE);
                vpOrder.setCurrentItem(1);
                break;
            case R.id.btn_phone:
                intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
                        + seller.getSeller_contact()));
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        flag = false;
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                tvOrderInfo.setTextColor(getResources().getColor(R.color.login_bg));
                tvOrderStatus.setTextColor(getResources().getColor(R.color.text_gay));
                ll1.setVisibility(View.VISIBLE);
                ll2.setVisibility(View.INVISIBLE);
                break;
            case 1:
                tvOrderStatus.setTextColor(getResources().getColor(R.color.login_bg));
                tvOrderInfo.setTextColor(getResources().getColor(R.color.text_gay));
                ll2.setVisibility(View.VISIBLE);
                ll1.setVisibility(View.INVISIBLE);
                break;
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void initOrderInfo() {
        tvTitle.setText("订单详情");
        order_seller_name = (TextView) views.get(0).findViewById(R.id.order_seller_name);
        order_seller_dish = (ListView) views.get(0).findViewById(R.id.order_seller_dish);
        order_seller_df = (TextView) views.get(0).findViewById(R.id.order_seller_df);
        order_seller_notice = (TextView) views.get(0).findViewById(R.id.order_seller_notice);
        order_seller_zong = (TextView) views.get(0).findViewById(R.id.order_seller_zong);
        order_seller_youhui = (TextView) views.get(0).findViewById(R.id.order_seller_youhui);
        order_seller_shifu = (TextView) views.get(0).findViewById(R.id.order_seller_shifu);
        order_address = (TextView) views.get(0).findViewById(R.id.order_address);
        order_people = (TextView) views.get(0).findViewById(R.id.order_people);
        order_phone = (TextView) views.get(0).findViewById(R.id.order_phone);
        btn_buy = (Button) views.get(0).findViewById(R.id.btn_buy);
        et_extra = (TextView) views.get(0).findViewById(R.id.et_extra);
        order_id = (TextView) views.get(0).findViewById(R.id.order_id);
        order_time = (TextView) views.get(0).findViewById(R.id.order_time);
        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btn_buy.getText().equals("评论")){
                    intent = new Intent(OrderInfoActivity.this, CommentActivity.class);
                    intent.putExtra("seller", seller);
                    intent.putExtra("takeoutOrder", takeoutOrder);
                    startActivity(intent);
                }else if (btn_buy.getText().equals("支付")){
                    et_pay=new EditText(OrderInfoActivity.this);
                    et_pay.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    et_pay.setHint("请输入支付密码:");
                    new AlertView(seller.getSeller_name(), "实付:"+shifu+"元", "取消",
                            new String[]{"确定"}, null, OrderInfoActivity.this,
                            AlertView.Style.Alert, new OnItemClickListener() {
                        @Override
                        public void onItemClick(Object o, int position) {
                           if (et_pay.getText().toString().trim().equals(user.getPassword())){
                               pay();
                           }else {
                               ShowToast.showToast(OrderInfoActivity.this,"支付失败,支付密码不正确");
                           }
                        }
                    }).addExtView(et_pay).show();

                }
            }
        });
        text1 = (TextView) views.get(1).findViewById(R.id.text1);
        text2 = (TextView) views.get(1).findViewById(R.id.text2);
        text3 = (TextView) views.get(1).findViewById(R.id.text3);
        text4 = (TextView) views.get(1).findViewById(R.id.text4);
    }

    public void onEventMainThread(EventBusSelect event) {
        switch (event.getActionType()) {
            case Config.COMMENT:
                ShowToast.showToast(OrderInfoActivity.this, "评论成功");
                break;
            case Config.ORDERSTATUS:
                setTextColor();
                switch (event.getMessage()) {
                    case "已支付":
                        text1.setTextColor(getResources().getColor(R.color.login_bg));
                        break;
                    case "商家已接单":
                        text2.setTextColor(getResources().getColor(R.color.login_bg));
                        break;
                    case "配送中":
                        text3.setTextColor(getResources().getColor(R.color.login_bg));
                        break;
                    case "已完成":
                        text4.setTextColor(getResources().getColor(R.color.login_bg));
                        break;
                }
                break;
        }

    }

    public void setData() {
        orderSellerAdapter.setCars(carList);
        setListViewHeightBasedOnChildren(order_seller_dish);
        order_seller_name.setText(seller.getSeller_name());
        order_seller_df.setText("￥" + seller.getSeller_df());
        int price = 0;
        for (Car car : carList) {
            price = price + (car.getSellerDish().getSd_price() * car.getNum());
        }
        order_seller_zong.setText("总计￥" + price);
        String notice = seller.getSeller_notice();
        String man = notice.substring((notice.indexOf("满") + 1), notice.indexOf("减"));
        String jian = notice.substring((notice.indexOf("减") + 1), notice.lastIndexOf("元"));
        if (price >= Integer.parseInt(man)) {
            order_seller_notice.setText("-￥" + jian);
            order_seller_youhui.setText("优惠￥" + jian);
            order_seller_shifu.setText("￥" + (price - Integer.parseInt(jian) + seller.getSeller_df()));
            shifu=""+(price - Integer.parseInt(jian) + seller.getSeller_df());
        } else {
            order_seller_notice.setText("-￥0");
            order_seller_youhui.setText("优惠￥0");
            order_seller_shifu.setText("￥" + (price + seller.getSeller_df()));
            shifu=""+(price + seller.getSeller_df());
        }
        order_id.setText(order.getTakeoutOrder().getTo_id());
        order_time.setText(order.getTakeoutOrderStatus().getTos_time());
        order_address.setText(order.getTakeoutOrder().getTo_address());
        order_phone.setText(order.getTakeoutOrder().getTo_phone());
        order_people.setText(order.getTakeoutOrder().getTo_name());
        et_extra.setText(order.getTakeoutOrder().getTo_extra());
        if (order.getTakeoutOrderStatus().getTos_status().equals("已完成")) {
            btn_buy.setText("评论");
        } else if (order.getTakeoutOrderStatus().getTos_status().equals("未支付")){
            btn_buy.setText("支付");
        } else {
            btn_buy.setClickable(false);
            btn_buy.setEnabled(false);
            btn_buy.setText(order.getTakeoutOrderStatus().getTos_status());
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

    class GetStatus implements Runnable {

        @Override
        public void run() {
            while (flag) {
                App.getfMealNet().getOrderStatus(takeoutOrderStatus.getTos_id()).subscribe(
                        success -> {
                            EventBus.getDefault().post(new EventBusSelect(Config.ORDERSTATUS, success.getTos_status()));
                        }, error -> {
                            L.e(error.getMessage());

                        });
                try {
                    Thread.sleep(60 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setTextColor() {
        text1.setTextColor(getResources().getColor(R.color.text_gay));
        text2.setTextColor(getResources().getColor(R.color.text_gay));
        text3.setTextColor(getResources().getColor(R.color.text_gay));
        text4.setTextColor(getResources().getColor(R.color.text_gay));
    }

    public void setCount() {
        if (thread == null || (!thread.isAlive())) {
            flag = true;
            thread = new Thread(new GetStatus());
            thread.start();
        }
    }

    public void pay(){
        AppObservable.bindActivity(OrderInfoActivity.this, App.getfMealNet().updateOrderStatus(takeoutOrderStatus.getTos_id())).subscribe(
                success -> {
                    L.e(success);
                    if (success.booleanValue()) {
                        btn_buy.setText("已支付");
                        ShowToast.showToast(OrderInfoActivity.this,"支付完成");
                        btn_buy.setClickable(false);
                        btn_buy.setEnabled(false);
                        EventBus.getDefault().post(new EventBusSelect(Config.updateStatus, "1"));
                    }
                }, error -> {
                    L.e(error.getMessage());

                });
    }
}
