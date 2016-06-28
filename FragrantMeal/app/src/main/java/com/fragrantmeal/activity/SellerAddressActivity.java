package com.fragrantmeal.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.fragrantmeal.R;
import com.fragrantmeal.entity.Seller;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by CaoBin on 2016/5/4.
 */
public class SellerAddressActivity extends Activity {

    @InjectView(R.id.btn_back)
    Button btnBack;
    @InjectView(R.id.seller_address)
    MapView sellerAddress;

    private BaiduMap mBaiduMap;
    private Intent intent;
    private Seller seller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_seller_address);
        ButterKnife.inject(this);
        initMap();
    }

    private void initMap() {
        intent=getIntent();
        seller= (Seller) intent.getSerializableExtra("seller");
        mBaiduMap = sellerAddress.getMap();
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        LatLng point = new LatLng(seller.getSeller_latitude(),seller.getSeller_longitude());
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.map_ic_map_mode_category_farmhouse_normal);
        OverlayOptions option = new MarkerOptions()
                .position(point)
                .icon(bitmap);
        mBaiduMap.clear();
        mBaiduMap.addOverlay(option);
        setNowLocation(point);

    }


    @OnClick({R.id.btn_back})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back:
                finish();
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        sellerAddress.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        sellerAddress.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        sellerAddress.onPause();
    }

    public void setNowLocation(LatLng point) {
        MapStatus mMapStatus = new MapStatus.Builder()
                .target(point)
                .zoom(18)
                .build();
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        mBaiduMap.setMapStatus(mMapStatusUpdate);
    }

}
