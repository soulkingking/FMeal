package com.fragrantmeal.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by CaoBin on 2016/4/24.
 */
public class OrderPageAdapter extends PagerAdapter {
    private List<View> views;
    public OrderPageAdapter(List<View> views) {
        this.views=views;
    }

    @Override
    public int getCount() {
        return views.size();
    }
    //滑动切换的时候销毁当前的组件
    @Override
    public void destroyItem(ViewGroup container, int position,
                            Object object) {
        container.removeView(views.get(position));
    }
    //每次滑动的时候生成的组件
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views.get(position));
        return views.get(position);
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }
}
