package com.mj.core.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mj.core.util.L;

import butterknife.ButterKnife;
import rx.subscriptions.CompositeSubscription;

/**
 * 基础Fragment类
 * Created By: Robin Yang
 * Created At: 2014-11-24 15:23
 */
public abstract class BaseFragment extends Fragment {
    protected Context mContext;

    public final CompositeSubscription compositeSubscription = new CompositeSubscription();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        L.d(getClass().getName());
        L.d(getActivity());
        mContext = getActivity();
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        L.d(getClass().getName());
        View view = inflater.inflate(getLayoutResource(), container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        L.d(getClass().getName());
    }

    @Override
    public void onStop() {
        super.onStop();
        L.d(getClass().getName());
    }

    @Override
    public void onPause() {
        super.onPause();
        L.d(getClass().getName());
    }

    @Override
    public void onDestroy() {
        L.d(getClass().getName());
        ButterKnife.reset(this);
        compositeSubscription.clear();
        super.onDestroy();
    }

    protected abstract int getLayoutResource();
}
