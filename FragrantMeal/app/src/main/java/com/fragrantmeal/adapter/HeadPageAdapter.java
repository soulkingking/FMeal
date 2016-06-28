package com.fragrantmeal.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.fragrantmeal.App;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;

import java.util.List;

/**
 * Created by CaoBin on 2016/4/26.
 */
public class HeadPageAdapter extends LoopPagerAdapter {
    private Context context;
    private List<String> imgs;
    public HeadPageAdapter(RollPagerView viewPager,Context context,List<String> imgs) {
        super(viewPager);
        this.context=context;
        this.imgs=imgs;
    }

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
        notifyDataSetChanged();
    }

    @Override
    public View getView(ViewGroup container, int position) {
        ImageView view = new ImageView(container.getContext());
        App.getImage(context,view,imgs.get(position));
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return view;
    }

    @Override
    protected int getRealCount() {
        return imgs.size();
    }
}
