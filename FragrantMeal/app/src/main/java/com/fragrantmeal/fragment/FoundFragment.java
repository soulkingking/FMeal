package com.fragrantmeal.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.fragrantmeal.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.ButterKnife;
import butterknife.InjectView;
import medusa.theone.waterdroplistview.view.WaterDropListView;

/**
 * Created by CaoBin on 2016/3/20.
 */
public class FoundFragment extends Fragment
        implements
        WaterDropListView.IWaterDropListViewListener {
    @InjectView(R.id.lv_found)
    WaterDropListView lvFound;
    private View view;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1 :
                    lvFound.stopRefresh();
                    break;
                case 2 :
                    lvFound.stopLoadMore();
                    break;
            }

        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_found, container, false);
        }
        ButterKnife.inject(this, view);
        initData();
        return view;
    }
    public void initData(){
        lvFound.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_expandable_list_item_1, getData()));
        lvFound.setWaterDropListViewListener(this);
        lvFound.setPullLoadEnable(true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
    private List<String> getData(){

        List<String> data = new ArrayList<String>();
        data.add("To see a world in a grain of sand,");
        data.add("And a heaven in a wild flower,");
        data.add("Hold infinity in the palm of your hand,");
        data.add("And eternity in an hour.");

        return data;
    }
    @Override
    public void onRefresh() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    handler.sendEmptyMessage(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onLoadMore() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    handler.sendEmptyMessage(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
