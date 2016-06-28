package com.fragrantmeal.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.fragrantmeal.App;
import com.fragrantmeal.EventBus.EventBusSelect;
import com.fragrantmeal.R;
import com.fragrantmeal.adapter.SellerAdapter;
import com.fragrantmeal.entity.Seller;
import com.fragrantmeal.util.Config;
import com.fragrantmeal.util.ShowToast;
import com.mj.core.util.L;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import medusa.theone.waterdroplistview.view.WaterDropListView;
import rx.android.app.AppObservable;


/**
 * Created by CaoBin on 2016/3/20.
 */
public class SellerMentyActivity extends Activity implements
        WaterDropListView.IWaterDropListViewListener, AdapterView.OnItemClickListener {
    @InjectView(R.id.lv_seller)
    WaterDropListView lvSeller;
    @InjectView(R.id.btn_back)
    Button btnBack;
    @InjectView(R.id.tv_ll_title)
    TextView tvLlTitle;

    private List<Seller> sellers;
    private List<Seller> list;
    private SellerAdapter sellerAdapter;
    private int current = 5;
    private Intent intent;
    private String titleName;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    lvSeller.stopRefresh();
                    current = 5;
                    getData();
                    break;
                case 2:
                    lvSeller.stopLoadMore();
                    if (list.size() - (current + 5) >= 5) {
                        for (; current < current + 5; current++) {
                            sellers.add(list.get(current));
                        }
                    } else if (list.size() - current <= 0) {
                        ShowToast.showToast(SellerMentyActivity.this, "没有数据了");
                    } else {
                        for (; current < list.size(); current++) {
                            sellers.add(list.get(current));
                        }
                    }
                    sellerAdapter.setSellers(sellers);
                    break;
            }

        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_menty);
        ButterKnife.inject(this);
        EventBus.getDefault().register(this);
        initData();
    }


    public void initData() {
        intent=getIntent();
        titleName = intent.getStringExtra("titleName");
        tvLlTitle.setText(titleName);
        sellers = new ArrayList<>();
        list = new ArrayList<>();
        sellerAdapter = new SellerAdapter(this, sellers);
        lvSeller.setAdapter(sellerAdapter);
        lvSeller.setWaterDropListViewListener(this);
        lvSeller.setOnItemClickListener(this);
        lvSeller.setPullLoadEnable(true);
        getData();

    }

    private void getData() {

        AppObservable.bindActivity(this, App.getfMealNet().findSpeciesAll(Config.location.getLongitude(), Config.location.getLatitude(),titleName)).subscribe(
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

    }

    @OnClick({R.id.btn_back})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
        EventBus.getDefault().unregister(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        L.e("点了" + position);
        if (position > 1) {
            intent = new Intent(this, SellerDishActivity.class);
            Seller seller = sellerAdapter.getSellers().get(position - 2);
            intent.putExtra("sellerInfo", seller);
            startActivity(intent);
        }

    }
}
