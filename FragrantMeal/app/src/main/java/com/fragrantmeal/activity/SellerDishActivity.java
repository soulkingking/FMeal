package com.fragrantmeal.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.fragrantmeal.App;
import com.fragrantmeal.EventBus.EventBusSelect;
import com.fragrantmeal.R;
import com.fragrantmeal.adapter.CommentAdapter;
import com.fragrantmeal.adapter.LeftMenuAdapter;
import com.fragrantmeal.adapter.RightSellerDishAdapter;
import com.fragrantmeal.adapter.SellerDishPageAdapter;
import com.fragrantmeal.entity.Car;
import com.fragrantmeal.entity.OrderCar;
import com.fragrantmeal.entity.Seller;
import com.fragrantmeal.entity.SellerComment;
import com.fragrantmeal.entity.SellerData;
import com.fragrantmeal.entity.SellerDish;
import com.fragrantmeal.entity.SellerDishData;
import com.fragrantmeal.entity.SellerMenuType;
import com.fragrantmeal.entity.UserInfo;
import com.fragrantmeal.util.Config;
import com.fragrantmeal.util.GsonUtil;
import com.fragrantmeal.util.ShowToast;
import com.fragrantmeal.view.PinnedHeaderListView;
import com.mj.core.util.L;
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
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import de.hdodenhof.circleimageview.CircleImageView;
import medusa.theone.waterdroplistview.view.WaterDropListView;
import rx.android.app.AppObservable;

/**
 * Created by CaoBin on 2016/4/21.
 */
public class SellerDishActivity extends Activity implements ViewPager.OnPageChangeListener, AdapterView.OnItemClickListener, AbsListView.OnScrollListener {


    @InjectView(R.id.seller_icon)
    CircleImageView sellerIcon;
    @InjectView(R.id.seller_name)
    TextView sellerName;
    @InjectView(R.id.seller_degree)
    RatingBar sellerDegree;
    @InjectView(R.id.seller_degree2)
    TextView sellerDegree2;
    @InjectView(R.id.ll_1)
    LinearLayout ll1;
    @InjectView(R.id.ll_2)
    LinearLayout ll2;
    @InjectView(R.id.ll_3)
    LinearLayout ll3;
    @InjectView(R.id.seller_dish_pager)
    ViewPager sellerDishPager;
    @InjectView(R.id.btn_collection)
    CheckBox btnCollection;

    private SellerDishPageAdapter sellerDishPageAdapter;
    private Intent intent;
    private List<View> views;
    private ListView leftListView;
    private LeftMenuAdapter leftMenuAdapter;
    private PinnedHeaderListView rightListView;
    private boolean isScroll = true;
    private SellerData sellerData;
    private List<SellerMenuType> sellerMenuTypes;
    private TextView leftMenu;
    private RightSellerDishAdapter rightSellerDishAdapter;
    private List<SellerDishData> sellerDishDatas;
    private TextView tv_deiverytime;
    private TextView tv_sendprice;
    private TextView tv_df;
    private TextView tv_activity;
    private Seller seller;
    private TextView seller_address;
    private LinearLayout btn_phone;
    private TextView df_time;
    private Button btn_car;
    private TextView tv_price;
    private LinearLayout btn_billing;
    private TextView tv_billing;
    private Map<SellerDish, Integer> cars;
    private OrderCar orderCar;
    private UserInfo user;
    private WaterDropListView lvComment;
    private List<SellerComment> sellerComments;
    private CommentAdapter commentAdapter;
    private int current=5;
    private List<SellerComment> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellerdish);
        ButterKnife.inject(this);
        EventBus.getDefault().register(this);
        init();

    }

    public void init() {
        intent = getIntent();
        user = (UserInfo) GsonUtil.fromJson(SharedPrefsUtil.getString(this, "LoginUser", "user"), UserInfo.class);
        seller = (Seller) intent.getSerializableExtra("sellerInfo");
        App.getImage(this, sellerIcon, seller.getSeller_icon());
        sellerName.setText(seller.getSeller_name());
        sellerDegree.setRating(seller.getSeller_degree());
        sellerDegree2.setText(seller.getSeller_degree() + "");
        views = new ArrayList<>();
        views.add(LayoutInflater.from(this).inflate(R.layout.item_seller_dish, null));
        views.add(LayoutInflater.from(this).inflate(R.layout.item_seller_commet, null));
        views.add(LayoutInflater.from(this).inflate(R.layout.item_seller_infor, null));
        initLeft();
        initcomment();
        initinfo();
        initcar();
        sellerDishPageAdapter = new SellerDishPageAdapter(views);
        sellerDishPager.setAdapter(sellerDishPageAdapter);
        sellerDishPager.setCurrentItem(0);
        sellerDishPager.setOnPageChangeListener(this);
        getData(seller.getS_id());

    }

    @OnClick({R.id.tv_seller_good, R.id.tv_comment, R.id.tv_infor, R.id.btn_back,R.id.btn_collection})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_seller_good:
                sellerDishPager.setCurrentItem(0);
                break;
            case R.id.tv_comment:
                sellerDishPager.setCurrentItem(1);
                break;
            case R.id.tv_infor:
                sellerDishPager.setCurrentItem(2);
                break;
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_collection:
                if (btnCollection.isChecked()){
                    addCollection();
                }else {
                    deleteCollection();
                }
                break;

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
        switch (position) {
            case 0:
                ll1.setVisibility(View.VISIBLE);
                ll2.setVisibility(View.INVISIBLE);
                ll3.setVisibility(View.INVISIBLE);
                break;
            case 1:
                ll1.setVisibility(View.INVISIBLE);
                ll2.setVisibility(View.VISIBLE);
                ll3.setVisibility(View.INVISIBLE);
                break;
            case 2:
                ll1.setVisibility(View.INVISIBLE);
                ll2.setVisibility(View.INVISIBLE);
                ll3.setVisibility(View.VISIBLE);
                break;
        }

    }


    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void getData(String s_id) {
        AppObservable.bindActivity(this, App.getfMealNet().findSellerDishA(s_id)).subscribe(
                success -> {
                    for (SellerDishData sellerDishData : success.getSellerDishDatas()) {
                        sellerMenuTypes.add(sellerDishData.getSellerMenuType());
                    }
                    leftMenuAdapter.setSellerMenuTypes(sellerMenuTypes);
                    leftListView.setSelection(0);
                    rightSellerDishAdapter.setData(sellerMenuTypes, success.getSellerDishDatas());
                    rightListView.setSelection(0);
                },

                e -> {

                }
        );

        AppObservable.bindActivity(this,App.getfMealNet().findCollection(seller.getS_id(), user.getU_id())).subscribe(
                result -> {
                    if (result != null) {
                        btnCollection.setChecked(true);
                    }
                },
                error -> {

                }
        );
        getComment();
    }

    public void initLeft() {
        sellerMenuTypes = new ArrayList<>();
        sellerDishDatas = new ArrayList<>();
        leftMenuAdapter = new LeftMenuAdapter(this, sellerMenuTypes);
        rightSellerDishAdapter = new RightSellerDishAdapter(sellerMenuTypes, this, sellerDishDatas);
        leftListView = (ListView) views.get(0).findViewById(R.id.seller_menutype);
        rightListView = (PinnedHeaderListView) views.get(0).findViewById(R.id.seller_dish);
        leftListView.setAdapter(leftMenuAdapter);
        rightListView.setAdapter(rightSellerDishAdapter);
        leftListView.setOnItemClickListener(this);
        rightListView.setOnScrollListener(this);

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        isScroll = false;

        for (int i = 0; i < leftListView.getChildCount(); i++) {
            if (i == position) {
                leftMenu = (TextView) leftListView.getChildAt(i).findViewById(R.id.left_menu);
                leftMenu.setTextColor(getResources().getColor(R.color.login_bg));
                leftListView.getChildAt(i).setBackgroundResource(R.drawable.bodermenu2);
            } else {
                leftMenu = (TextView) leftListView.getChildAt(i).findViewById(R.id.left_menu);
                leftMenu.setTextColor(getResources().getColor(R.color.gay));
                leftListView.getChildAt(i).setBackgroundResource(R.drawable.bodermenu);
            }
        }

        int rightSection = 0;
        for (int i = 0; i < position; i++) {
            rightSection += rightSellerDishAdapter.getCountForSection(i) + 1;
        }
        rightListView.setSelection(rightSection);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (isScroll) {
            for (int i = 0; i < leftListView.getChildCount(); i++) {

                if (i == rightSellerDishAdapter.getSectionForPosition(firstVisibleItem)) {
                    leftMenu = (TextView) leftListView.getChildAt(i).findViewById(R.id.left_menu);
                    leftMenu.setTextColor(getResources().getColor(R.color.login_bg));
                    leftListView.getChildAt(i).setBackgroundResource(R.drawable.bodermenu2);
                } else {
                    leftMenu = (TextView) leftListView.getChildAt(i).findViewById(R.id.left_menu);
                    leftMenu.setTextColor(getResources().getColor(R.color.gay));
                    leftListView.getChildAt(i).setBackgroundResource(R.drawable.bodermenu);
                }
            }

        } else {
            isScroll = true;
        }
    }


    public void initinfo() {
        tv_deiverytime = (TextView) views.get(2).findViewById(R.id.tv_deiverytime);
        tv_sendprice = (TextView) views.get(2).findViewById(R.id.tv_sendprice);
        tv_df = (TextView) views.get(2).findViewById(R.id.tv_df);
        tv_activity = (TextView) views.get(2).findViewById(R.id.tv_activity);
        seller_address = (TextView) views.get(2).findViewById(R.id.seller_address);
        btn_phone = (LinearLayout) views.get(2).findViewById(R.id.btn_phone);
        df_time = (TextView) views.get(2).findViewById(R.id.df_time);
        tv_deiverytime.setText(seller.getSeller_deliverytime() + "分钟");
        tv_sendprice.setText("￥" + seller.getSeller_sendprice());
        tv_df.setText("￥" + seller.getSeller_df());
        tv_activity.setText(seller.getSeller_intro());
        df_time.setText("配送时间：" + seller.getSeller_starttime() + "-" + seller.getSeller_endtime());
        btn_phone.setOnClickListener(v -> {
            intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
                    + seller.getSeller_contact()));
            startActivity(intent);
        });
        seller_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SellerDishActivity.this,SellerAddressActivity.class);
                intent.putExtra("seller",seller);
                startActivity(intent);
            }
        });
    }

    public void initcar() {
        cars = new HashMap<>();
        orderCar = new OrderCar();
        orderCar.setCars(cars);
        orderCar.setSeller(seller);
        btn_car = (Button) views.get(0).findViewById(R.id.btn_car);
        tv_price = (TextView) views.get(0).findViewById(R.id.tv_price);
        btn_billing = (LinearLayout) views.get(0).findViewById(R.id.btn_billing);
        tv_billing = (TextView) views.get(0).findViewById(R.id.tv_billing);
        tv_billing.setText("还差" + seller.getSeller_sendprice() + "元");
        btn_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        tv_billing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderCar.setCars(cars);
                orderCar.setSeller(seller);
                Intent intent = new Intent(SellerDishActivity.this, OrderActivity.class);
                intent.putExtra("order", orderCar);
                startActivity(intent);
            }
        });
    }


    public void onEventMainThread(EventBusSelect event) {
        switch (event.getActionType()) {
            case Config.BUY_CAR:
                int price = 0;
                Car car = (Car) GsonUtil.fromJson(event.getMessage(), Car.class);
                cars.put(car.getSellerDish(), car.getNum());
                for (SellerDish sellerDish : cars.keySet()) {
                    price = price + (sellerDish.getSd_price() * cars.get(sellerDish));
                    L.e(sellerDish.getSd_name() + cars.get(sellerDish));
                }
                if (price >= seller.getSeller_sendprice()) {
                    btn_car.setBackgroundResource(R.drawable.takeout_shopcart_enable);
                    btn_car.setEnabled(true);
                    tv_price.setTextColor(getResources().getColor(R.color.login_bg));
                    tv_billing.setEnabled(true);
                    btn_billing.setBackgroundColor(getResources().getColor(R.color.login_bg));
                    tv_billing.setText("去结算");
                    tv_price.setText("￥" + price);
                } else if (price > 0) {
                    btn_car.setBackgroundResource(R.drawable.takeout_shopcart_enable);
                    btn_car.setEnabled(true);
                    tv_price.setTextColor(getResources().getColor(R.color.login_bg));
                    tv_billing.setEnabled(false);
                    btn_billing.setBackgroundColor(getResources().getColor(R.color.gay4));
                    tv_billing.setText("还差" + (seller.getSeller_sendprice() - price) + "元");
                    tv_price.setText("￥" + price);
                } else {
                    btn_car.setBackgroundResource(R.drawable.takeout_shopcart_disable);
                    btn_car.setEnabled(false);
                    tv_price.setTextColor(getResources().getColor(R.color.gay4));
                    tv_billing.setEnabled(false);
                    btn_billing.setBackgroundColor(getResources().getColor(R.color.gay4));
                    tv_billing.setText("还差" + (seller.getSeller_sendprice()) + "元");
                    tv_price.setText("购物车是空的");
                }
                break;

            case Config.SEND_ORDER:
                ShowToast.showToast(this, "订单已下达");
                cars.clear();
                sellerMenuTypes.clear();
                getData(seller.getS_id());
                btn_car.setBackgroundResource(R.drawable.takeout_shopcart_disable);
                btn_car.setEnabled(false);
                tv_price.setTextColor(getResources().getColor(R.color.gay4));
                tv_billing.setEnabled(false);
                btn_billing.setBackgroundColor(getResources().getColor(R.color.gay4));
                tv_billing.setText("还差" + (seller.getSeller_sendprice()) + "元");
                tv_price.setText("购物车是空的");
                break;
        }


    }

    public void addCollection(){
        AppObservable.bindActivity(this,App.getfMealNet().addCollection(seller.getS_id(), user.getU_id())).subscribe(
                result ->{
                    if (result!=null){
                        btnCollection.setChecked(true);
                        ShowToast.showToast(this, "收藏成功");
                        EventBus.getDefault().post(new EventBusSelect(Config.COLLECTION, ""));
                    }
                },
                error ->{

                }
        );
    }

    public void deleteCollection(){
        AppObservable.bindActivity(this,App.getfMealNet().deleteCollection(seller.getS_id(), user.getU_id())).subscribe(
                result ->{
                    if (result==null){
                        btnCollection.setChecked(false);
                        ShowToast.showToast(this, "取消收藏成功");
                        EventBus.getDefault().post(new EventBusSelect(Config.COLLECTION, ""));
                    }
                },
                error ->{

                }
        );
    }

    private void initcomment(){
        lvComment= (WaterDropListView) views.get(1).findViewById(R.id.lv_comment);
        sellerComments=new ArrayList<>();
        list=new ArrayList<>();
        commentAdapter=new CommentAdapter(this,sellerComments);
        lvComment.setAdapter(commentAdapter);
        lvComment.setWaterDropListViewListener(new WaterDropListView.IWaterDropListViewListener() {
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
        });
        lvComment.setPullLoadEnable(true);
    }
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1 :
                    lvComment.stopRefresh();
                    current=5;
                    getComment();
                    break;
                case 2 :
                    lvComment.stopLoadMore();
                    if (list.size()-(current+5)>=5){
                        for (;current<current+5;current++){
                            sellerComments.add(list.get(current));
                        }
                    }else if (list.size()-current<=0){
                        ShowToast.showToast(SellerDishActivity.this,"没有数据了");
                    }else {
                        for (;current<list.size();current++){
                            sellerComments.add(list.get(current));
                        }
                    }
                    commentAdapter.setSellerComments(sellerComments);
                    break;
            }

        }
    };

    public void getComment(){
        AppObservable.bindActivity(this,App.getfMealNet().findSellerComment(seller.getS_id())).subscribe(
                success ->{
                    sellerComments.clear();
                    Collections.reverse(success);
                    list = success;
                    if (success.size() >= 5) {
                        for (int i = 0; i < 5; i++) {
                            sellerComments.add(success.get(i));
                        }
                    } else {
                        for (int i = 0; i < success.size(); i++) {
                            sellerComments.add(success.get(i));
                        }
                    }
                    commentAdapter.setSellerComments(sellerComments);
                },
                e ->{
                    current = 5;
                }
        );
    }

}
