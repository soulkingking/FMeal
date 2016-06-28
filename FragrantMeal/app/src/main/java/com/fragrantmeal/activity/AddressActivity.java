package com.fragrantmeal.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

import com.fragrantmeal.App;
import com.fragrantmeal.EventBus.EventBusSelect;
import com.fragrantmeal.R;
import com.fragrantmeal.adapter.AddressAdapter;
import com.fragrantmeal.entity.DeliveryAddress;
import com.fragrantmeal.entity.UserInfo;
import com.fragrantmeal.util.Config;
import com.fragrantmeal.util.GsonUtil;
import com.google.gson.reflect.TypeToken;
import com.mj.core.util.IntentUtils;
import com.mj.core.util.SharedPrefsUtil;
import com.yydcdut.sdlv.Menu;
import com.yydcdut.sdlv.MenuItem;
import com.yydcdut.sdlv.SlideAndDragListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import rx.android.app.AppObservable;

/**
 * Created by CaoBin on 2016/4/24.
 */
public class AddressActivity extends Activity implements SlideAndDragListView.OnItemDeleteListener, SlideAndDragListView.OnMenuItemClickListener {

    @InjectView(R.id.lv_address)
    SlideAndDragListView lvAddress;

    private Menu menu;
    private AddressAdapter addressAdapter;
    private List<DeliveryAddress> deliveryAddresses;
    private UserInfo user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        ButterKnife.inject(this);
        EventBus.getDefault().register(this);
        initMenu();
        initData();
    }

    public void initMenu(){
        menu = new Menu(true, false, 0);//the second parameter is whether can slide over
        menu.addItem(new MenuItem.Builder().setWidth(90)//set Width
                .setBackground(new ColorDrawable(Color.RED))// set background
                .setText("删除")//set text string
                .setTextColor(Color.WHITE)//set text color
                .setTextSize(20)//set text size
                .build());
//        menu.addItem(new MenuItem.Builder().setWidth(120)
//                .setBackground(new ColorDrawable(Color.BLACK))
//                .setDirection(MenuItem.DIRECTION_RIGHT)//set direction (default DIRECTION_LEFT)
//                .setIcon(getResources().getDrawable(R.drawable.takeout_ic_delete))// set icon
//                .build());
        lvAddress.setMenu(menu);
    }

    public void initData(){
        deliveryAddresses=new ArrayList<>();
        addressAdapter=new AddressAdapter(this,deliveryAddresses);
        lvAddress.setAdapter(addressAdapter);
        getData();
        lvAddress.setOnItemDeleteListener(this);
        lvAddress.setOnMenuItemClickListener(this);
    }





    @OnClick({R.id.btn_back,R.id.btn_add_address})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_add_address:
                IntentUtils.forward(this,AddAddressActivity.class);
                break;
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onItemDelete(View view, int position) {
//        deliveryAddresses.remove(position);
//        addressAdapter.setDeliveryAddresses(deliveryAddresses);
    }

    public void getData(){
        user = (UserInfo) GsonUtil.fromJson(SharedPrefsUtil.getString(this, "LoginUser", "user"), UserInfo.class);
        AppObservable.bindActivity(this, App.getfMealNet().findUserAddress(user.getU_id())).subscribe(
                result -> {
                    deliveryAddresses=result;
                    addressAdapter.setDeliveryAddresses(result);
                },

                error -> {

                }
        );
    }

    public void onEventMainThread(EventBusSelect event) {
        switch (event.getActionType()){
            case Config.ADDRESS:
                deliveryAddresses= (List<DeliveryAddress>) GsonUtil.fromJson(event.getMessage(),new TypeToken<List<DeliveryAddress>>() {
                }.getType());
                addressAdapter.setDeliveryAddresses(deliveryAddresses);
                break;
            case Config.UPDATE_ADDRESS:
                Intent intent=new Intent(this,UpdateAddressActivity.class);
                intent.putExtra(Config.UPDATE_ADDRESS, event.getMessage());
                startActivity(intent);
                break;
            case Config.ORDER_ADDRESS:
                finish();
                break;
        }

    }

    @Override
    public int onMenuItemClick(View v, int itemPosition, int buttonPosition, int direction) {
        switch (direction) {
            case MenuItem.DIRECTION_LEFT:
                switch (buttonPosition) {
                    case 0:
                        deleteAddress(deliveryAddresses.get(itemPosition).getDa_id(),deliveryAddresses.get(itemPosition).getU_id());
                        return Menu.ITEM_SCROLL_BACK;
                }
                break;
        }
        return Menu.ITEM_NOTHING;
    }

    public void deleteAddress(String da_id,String u_id){
        AppObservable.bindActivity(this, App.getfMealNet().DeleteUserAddress(da_id,u_id)).subscribe(
                result -> {
                    deliveryAddresses = result;
                    addressAdapter.setDeliveryAddresses(result);
                },

                error -> {

                }
        );
    }

}
