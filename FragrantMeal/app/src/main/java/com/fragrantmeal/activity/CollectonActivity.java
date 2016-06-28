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
import com.fragrantmeal.adapter.CollectionAdapter;
import com.fragrantmeal.entity.Seller;
import com.fragrantmeal.entity.UserInfo;
import com.fragrantmeal.util.Config;
import com.fragrantmeal.util.GsonUtil;
import com.mj.core.util.SharedPrefsUtil;
import com.yydcdut.sdlv.Menu;
import com.yydcdut.sdlv.MenuItem;
import com.yydcdut.sdlv.SlideAndDragListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.greenrobot.event.EventBus;
import rx.android.app.AppObservable;

/**
 * Created by CaoBin on 2016/4/26.
 */
public class CollectonActivity extends Activity implements SlideAndDragListView.OnMenuItemClickListener{
    @InjectView(R.id.lv_collection)
    SlideAndDragListView lvCollection;
    private Menu menu;
    private UserInfo user;
    private List<Seller> sellers;
    private CollectionAdapter sellerAdapter;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
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
        lvCollection.setMenu(menu);
    }

    public void initData(){
//        Config.location=new BDLocation();
//        Config.location.setLongitude(120.749);
//        Config.location.setLatitude(31.283);
        user = (UserInfo) GsonUtil.fromJson(SharedPrefsUtil.getString(this, "LoginUser", "user"), UserInfo.class);
        sellers=new ArrayList<>();
        sellerAdapter=new CollectionAdapter(this,sellers);
        lvCollection.setAdapter(sellerAdapter);
        getData();
        lvCollection.setOnMenuItemClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Override
    public int onMenuItemClick(View v, int itemPosition, int buttonPosition, int direction) {
        switch (direction) {
            case MenuItem.DIRECTION_LEFT:
                switch (buttonPosition) {
                    case 0:
                        deleteCollection(sellers.get(itemPosition).getS_id(), user.getU_id());
                        return Menu.ITEM_SCROLL_BACK;
                }
                break;
        }
        return Menu.ITEM_NOTHING;
    }


    private void getData(){
        AppObservable.bindActivity(this, App.getfMealNet().findUserCollection(user.getU_id())).subscribe(
                result -> {
                    sellers=result;
                    sellerAdapter.setSellers(result);
                },
                error -> {

                }
        );
    }

    public void deleteCollection(String s_id,String u_id){
        AppObservable.bindActivity(this, App.getfMealNet().deleteCollection(s_id, u_id)).subscribe(
                result ->{
                    getData();
                },
                error ->{

                }
        );
    }

    public void onEventMainThread(EventBusSelect event) {
        switch (event.getActionType()){
            case Config.COLLECTION:
                getData();
                break;
        }
    }
}
