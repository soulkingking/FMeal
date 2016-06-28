package com.fragrantmeal.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.mapapi.search.core.PoiInfo;
import com.fragrantmeal.R;

import java.util.List;

/**
 * poi适配器
 */
public class PoiAdapter extends BaseAdapter {
    private Context context;
    private List<PoiInfo> pois;


    public PoiAdapter(Context context, List<PoiInfo> pois) {
        this.context = context;
        this.pois = pois;
    }

    public List<PoiInfo> getPois() {
        return pois;
    }

    public void setPois(List<PoiInfo> pois) {
        this.pois = pois;
    }

    @Override
    public int getCount() {
        return pois.size();
    }

    @Override
    public Object getItem(int position) {
        return pois.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.locationpois_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (position == 0 ) {
            holder.baidu_icon.setImageResource(R.drawable.baidumap_ico_poi_on);
            holder.locationpoi_name.setTextColor(Color.parseColor("#FF5722"));
        }else {
            holder.baidu_icon.setImageResource(R.drawable.baidumap_ico_off);
            holder.locationpoi_name.setTextColor(Color.parseColor("#000000"));
        }
        PoiInfo poiInfo = pois.get(position);
        holder.locationpoi_name.setText(poiInfo.name);
        holder.locationpoi_address.setText(poiInfo.address);
        return convertView;
    }

    class ViewHolder {
        TextView locationpoi_name;
        TextView locationpoi_address;
        ImageView baidu_icon;

        ViewHolder(View view) {
            locationpoi_name = (TextView) view.findViewById(R.id.locationpois_name);
            locationpoi_address = (TextView) view.findViewById(R.id.locationpois_address);
            baidu_icon= (ImageView) view.findViewById(R.id.baidu_icon);
        }
    }
}
