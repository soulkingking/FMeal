package com.fragrantmeal.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.bigkoo.alertview.AlertView;
import com.fragrantmeal.App;
import com.fragrantmeal.EventBus.EventBusSelect;
import com.fragrantmeal.R;
import com.fragrantmeal.fragment.FoundFragment;
import com.fragrantmeal.fragment.OrderFragment;
import com.fragrantmeal.fragment.SellerFragment;
import com.fragrantmeal.fragment.UserFragment;
import com.fragrantmeal.util.Config;
import com.fragrantmeal.util.GsonUtil;
import com.mj.core.util.IntentUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

public class MainActivity extends Activity {

    @InjectView(R.id.iv_restaurant)
    ImageView ivRestaurant;
    @InjectView(R.id.tv_restaurant)
    TextView tvRestaurant;
    @InjectView(R.id.btn_restaurant)
    LinearLayout btnRestaurant;
    @InjectView(R.id.iv_found)
    ImageView ivFound;
    @InjectView(R.id.tv_found)
    TextView tvFound;
    @InjectView(R.id.btn_found)
    LinearLayout btnFound;
    @InjectView(R.id.iv_order)
    ImageView ivOrder;
    @InjectView(R.id.tv_order)
    TextView tvOrder;
    @InjectView(R.id.btn_order)
    LinearLayout btnOrder;
    @InjectView(R.id.iv_user)
    ImageView ivUser;
    @InjectView(R.id.tv_user)
    TextView tvUser;
    @InjectView(R.id.btn_user)
    LinearLayout btnUser;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.tv_location)
    TextView tvLocation;

    private SellerFragment restaurantFragment;
    private FoundFragment foundFragment;
    private OrderFragment orderFragment;
    private UserFragment userFragment;
    private AlertView alertView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        EventBus.getDefault().register(this);
        App.mLocationClient.start();
        setSelect(0);
    }

    public void setSelect(int i) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        hideFragment(transaction);
        // 把图片设置为亮的
        // 设置内容区域
        switch (i) {
            case 0:
                if (restaurantFragment == null) {
                    restaurantFragment = new SellerFragment();
                    transaction.add(R.id.fl_menu, restaurantFragment);
                } else {
                    transaction.show(restaurantFragment);
                }
                ivRestaurant.setImageResource(R.drawable.ic_restaurant_on);
                tvRestaurant.setTextColor(getResources().getColor(R.color.menu_on));
                break;

            case 1:
                if (foundFragment == null) {
                    foundFragment = new FoundFragment();
                    transaction.add(R.id.fl_menu, foundFragment);
                } else {
                    transaction.show(foundFragment);
                }
                ivFound.setImageResource(R.drawable.ic_found_on);
                tvFound.setTextColor(getResources().getColor(R.color.menu_on));
                break;

            case 2:
                if (orderFragment == null) {
                    orderFragment = new OrderFragment();
                    transaction.add(R.id.fl_menu, orderFragment);
                } else {
                    transaction.show(orderFragment);
                }
                ivOrder.setImageResource(R.drawable.ic_order_on);
                tvOrder.setTextColor(getResources().getColor(R.color.menu_on));
                break;

            case 3:
                if (userFragment == null) {
                    userFragment = new UserFragment();
                    transaction.add(R.id.fl_menu, userFragment);
                } else {
                    transaction.show(userFragment);
                }
                ivUser.setImageResource(R.drawable.ic_user_on);
                tvUser.setTextColor(getResources().getColor(R.color.menu_on));
                break;

            default:
                break;
        }
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (restaurantFragment != null) {
            transaction.hide(restaurantFragment);
        }
        if (foundFragment != null) {
            transaction.hide(foundFragment);
        }
        if (orderFragment != null) {
            transaction.hide(orderFragment);
        }
        if (userFragment != null) {
            transaction.hide(userFragment);
        }

    }

    @OnClick({R.id.btn_restaurant, R.id.btn_found, R.id.btn_order, R.id.btn_user,R.id.btn_more})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_restaurant:
                resetImage();
                setSelect(0);
                break;
            case R.id.btn_found:
                resetImage();
                setSelect(1);
                break;
            case R.id.btn_order:
                resetImage();
                setSelect(2);
                break;
            case R.id.btn_user:
                resetImage();
                setSelect(3);
                break;
            case R.id.btn_more:
                alertView=new AlertView(null, null, "取消", null,
                        new String[]{"管理地址"},
                        this, AlertView.Style.ActionSheet, (o, position) -> {
                    switch (position){
                        case 0:
                            IntentUtils.forward(MainActivity.this,AddressActivity.class);
                            break;
                        case -1:
                            alertView.dismiss();
                            break;
                    }

                });
                alertView.show();
                break;
        }

    }

    /**
     * 切换图片至暗色
     */
    private void resetImage() {
        ivRestaurant.setImageResource(R.drawable.ic_restaurant_off);
        ivFound.setImageResource(R.drawable.ic_found_off);
        ivOrder.setImageResource(R.drawable.ic_order_off);
        ivUser.setImageResource(R.drawable.ic_user_off);
        tvRestaurant.setTextColor(Color.GRAY);
        tvFound.setTextColor(Color.GRAY);
        tvOrder.setTextColor(Color.GRAY);
        tvUser.setTextColor(Color.GRAY);
    }

    public void onEventMainThread(EventBusSelect event) {
        if (event.getActionType().equals(Config.LOCATION)){
            BDLocation location= (BDLocation) GsonUtil.fromJson(event.getMessage(), BDLocation.class);
            tvLocation.setText(location.getCity()+location.getDistrict());


            if (location.getPoiList()!=null){
                tvTitle.setText(location.getPoiList().get(0).getName());
            }else {
                tvTitle.setText(location.getStreet()+location.getStreetNumber());
            }
//            if (address!=null) {
//                int start1 = address.indexOf("国") + 1;
//                if (address.lastIndexOf("省") != -1) {
//                    start1 = address.indexOf("省") + 1;
//                }
//                if (address.lastIndexOf("州") != -1) {
//                    start1 = address.indexOf("州") + 1;
//                }
//                int end1 = address.lastIndexOf("市") + 1;
//                tvLocation.setText(address.substring(start1, end1));
//                int start = address.indexOf("市") + 1;
//                int end = address.lastIndexOf("-");
//                tvTitle.setText(address.substring(start, end));
//            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
