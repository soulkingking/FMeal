package com.fragrantmeal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;
import com.fragrantmeal.App;
import com.fragrantmeal.R;
import com.fragrantmeal.entity.Seller;
import com.fragrantmeal.util.Config;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by CaoBin on 2016/4/21.
 */
public class SellerAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<Seller> sellers;

    public SellerAdapter(Context context, List<Seller> sellers) {
        this.context = context;
        this.sellers = sellers;
        inflater = LayoutInflater.from(context);

    }

    public List<Seller> getSellers() {
        return sellers;
    }

    public void setSellers(List<Seller> sellers) {
        this.sellers = sellers;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return sellers.size();
    }

    @Override
    public Object getItem(int position) {
        return sellers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_seller, null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        App.getImage(context, viewHolder.sellerIcon, sellers.get(position).getSeller_icon());
        viewHolder.sellerName.setText(sellers.get(position).getSeller_name());
        viewHolder.sellerDegree.setRating(sellers.get(position).getSeller_degree());
        viewHolder.sellerDeliverytime.setText(sellers.get(position).getSeller_deliverytime() + "分钟");
        viewHolder.sellerSendprice.setText("起送价￥"+sellers.get(position).getSeller_sendprice());
        viewHolder.sellerDf.setText("配送费￥" + sellers.get(position).getSeller_df());
        viewHolder.sellerNotice.setText(sellers.get(position).getSeller_notice());
        LatLng latLng=new LatLng(Config.location.getLatitude(),Config.location.getLongitude());
        LatLng latLng1=new LatLng(sellers.get(position).getSeller_latitude(),sellers.get(position).getSeller_longitude());
        DecimalFormat df   = new DecimalFormat("######0");
        viewHolder.sellerDistance.setText(df.format(DistanceUtil.getDistance(latLng, latLng1))+"m");
        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.seller_icon)
        ImageView sellerIcon;
        @InjectView(R.id.seller_name)
        TextView sellerName;
        @InjectView(R.id.seller_distance)
        TextView sellerDistance;
        @InjectView(R.id.seller_degree)
        RatingBar sellerDegree;
        @InjectView(R.id.seller_deliverytime)
        TextView sellerDeliverytime;
        @InjectView(R.id.seller_sendprice)
        TextView sellerSendprice;
        @InjectView(R.id.seller_df)
        TextView sellerDf;
        @InjectView(R.id.seller_notice)
        TextView sellerNotice;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
