package com.fragrantmeal.activity;

/**
 * Created by CaoBin on 2016/4/27.
 */
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.fragrantmeal.EventBus.EventBusSelect;
import com.fragrantmeal.R;
import com.fragrantmeal.adapter.PoiAdapter;
import com.fragrantmeal.util.Config;

import java.util.List;

import de.greenrobot.event.EventBus;

public class BaiduMapAddress extends Activity implements BDLocationListener, OnGetGeoCoderResultListener, BaiduMap.OnMapStatusChangeListener, AdapterView.OnItemClickListener {

    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private ListView poisLL;
    /**
     * 定位模式
     */
    private MyLocationConfiguration.LocationMode mCurrentMode;
    /**
     * 定位端
     */
    private LocationClient mLocClient;
    /**
     * 是否是第一次定位
     */
    private boolean isFirstLoc = true;
    /**
     * 定位坐标
     */
    private LatLng locationLatLng;
    /**
     * 定位城市
     */
    private String city;
    /**
     * 反地理编码
     */
    private GeoCoder geoCoder;
    /**
     * 界面上方布局
     */
    private RelativeLayout topRL;

    private  PoiAdapter poiAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_baidumap);
        initView();
    }

    private void initView() {
        mMapView = (MapView) findViewById(R.id.main_bdmap);
        mBaiduMap = mMapView.getMap();

        poisLL = (ListView) findViewById(R.id.main_pois);

        topRL = (RelativeLayout) findViewById(R.id.main_top_RL);


        //定义地图状态
        MapStatus mMapStatus = new MapStatus.Builder()
                .zoom(18)
                .build();
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        //改变地图状态
        mBaiduMap.setMapStatus(mMapStatusUpdate);

        //地图状态改变相关监听
        mBaiduMap.setOnMapStatusChangeListener(this);

        //开启定位图层
        mBaiduMap.setMyLocationEnabled(true);

        //定位图层显示方式
        mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;

        /**
         * 设置定位图层配置信息，只有先允许定位图层后设置定位图层配置信息才会生效
         * customMarker用户自定义定位图标
         * enableDirection是否允许显示方向信息
         * locationMode定位图层显示方式
         */
        mBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(mCurrentMode, true, null));

        //初始化定位
        mLocClient = new LocationClient(this);
        //注册定位监听
        mLocClient.registerLocationListener(this);

        //定位选项
        LocationClientOption option = new LocationClientOption();
        /**
         * coorType - 取值有3个：
         * 返回国测局经纬度坐标系：gcj02
         * 返回百度墨卡托坐标系 ：bd09
         * 返回百度经纬度坐标系 ：bd09ll
         */
        option.setCoorType("bd09ll");
        //设置是否需要地址信息，默认为无地址
        option.setIsNeedAddress(true);
        //设置是否需要返回位置语义化信息，可以在BDLocation.getLocationDescribe()中得到数据，ex:"在天安门附近"， 可以用作地址信息的补充
        option.setIsNeedLocationDescribe(true);
        //设置是否需要返回位置POI信息，可以在BDLocation.getPoiList()中得到数据
        option.setIsNeedLocationPoiList(true);
        /**
         * 设置定位模式
         * Battery_Saving
         * 低功耗模式
         * Device_Sensors
         * 仅设备(Gps)模式
         * Hight_Accuracy
         * 高精度模式
         */
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //设置是否打开gps进行定位
        option.setOpenGps(true);
        //设置扫描间隔，单位是毫秒 当<1000(1s)时，定时定位无效
        option.setScanSpan(1000);

        //设置 LocationClientOption
        mLocClient.setLocOption(option);

        //开始定位
        mLocClient.start();
        poisLL.setOnItemClickListener(this);
    }

    /**
     * 定位监听
     *
     * @param bdLocation
     */
    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        //如果bdLocation为空或mapView销毁后不再处理新数据接收的位置
        if (bdLocation == null || mBaiduMap == null) {
            return;
        }

        //定位数据
        MyLocationData data = new MyLocationData.Builder()
                //定位精度bdLocation.getRadius()
                .accuracy(bdLocation.getRadius())
                        //此处设置开发者获取到的方向信息，顺时针0-360
                .direction(bdLocation.getDirection())
                        //经度
                .latitude(bdLocation.getLatitude())
                        //纬度
                .longitude(bdLocation.getLongitude())
                        //构建
                .build();

        //设置定位数据
        mBaiduMap.setMyLocationData(data);

        //是否是第一次定位
        if (isFirstLoc) {
            isFirstLoc = false;
            LatLng ll = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
            MapStatusUpdate msu = MapStatusUpdateFactory.newLatLngZoom(ll, 18);
            mBaiduMap.animateMapStatus(msu);
        }

        //获取坐标，待会用于POI信息点与定位的距离
        locationLatLng = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
        //获取城市，待会用于POISearch
        city = bdLocation.getCity();


        //创建GeoCoder实例对象
        geoCoder = GeoCoder.newInstance();
        //发起反地理编码请求(经纬度->地址信息)
        ReverseGeoCodeOption reverseGeoCodeOption = new ReverseGeoCodeOption();
        //设置反地理编码位置坐标
        reverseGeoCodeOption.location(new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude()));
        geoCoder.reverseGeoCode(reverseGeoCodeOption);

        //设置查询结果监听者
        geoCoder.setOnGetGeoCodeResultListener(this);
        mLocClient.stop();
    }

    //地理编码查询结果回调函数
    @Override
    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
    }

    //反地理编码查询结果回调函数
    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
        List<PoiInfo> poiInfos = reverseGeoCodeResult.getPoiList();

        poiAdapter = new PoiAdapter(BaiduMapAddress.this,poiInfos);
        poisLL.setAdapter(poiAdapter);
    }


    /**
     * 手势操作地图，设置地图状态等操作导致地图状态开始改变
     *
     * @param mapStatus 地图状态改变开始时的地图状态
     */
    @Override
    public void onMapStatusChangeStart(MapStatus mapStatus) {
    }

    /**
     * 地图状态变化中
     *
     * @param mapStatus 当前地图状态
     */
    @Override
    public void onMapStatusChange(MapStatus mapStatus) {

    }

    /**
     * 地图状态改变结束
     *
     * @param mapStatus 地图状态改变结束后的地图状态
     */
    @Override
    public void onMapStatusChangeFinish(MapStatus mapStatus) {
        //地图操作的中心点
        LatLng cenpt = mapStatus.target;
        geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(cenpt));

    }




    //回退键
    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // activity 恢复时同时恢复地图控件
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // activity 暂停时同时暂停地图控件
        mMapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //退出时停止定位
        mLocClient.stop();
        //退出时关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);

        // activity 销毁时同时销毁地图控件
        mMapView.onDestroy();

        //释放资源
        if (geoCoder != null) {
            geoCoder.destroy();
        }

        mMapView = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        EventBus.getDefault().post(new EventBusSelect(Config.MYADDRESS,poiAdapter.getPois().get(position).name));
        finish();
    }
}

