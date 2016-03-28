package com.fragrantmeal.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fragrantmeal.R;

/**
 * Created by CaoBin on 2016/3/20.
 */
public class OrderFragment extends Fragment {
    private View view;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view==null){
            view=inflater.inflate(R.layout.fragment_order,container,false);
        }
        return view;
    }
}
