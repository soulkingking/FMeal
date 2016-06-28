package com.fragrantmeal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fragrantmeal.R;
import com.fragrantmeal.entity.SellerMenuType;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by CaoBin on 2016/4/22.
 */
public class LeftMenuAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Context context;
    private List<SellerMenuType> sellerMenuTypes;

    public LeftMenuAdapter(Context context, List<SellerMenuType> sellerMenuTypes) {
        this.context = context;
        this.sellerMenuTypes = sellerMenuTypes;
        inflater = LayoutInflater.from(context);
    }

    public List<SellerMenuType> getSellerMenuTypes() {
        return sellerMenuTypes;
    }

    public void setSellerMenuTypes(List<SellerMenuType> sellerMenuTypes) {
        this.sellerMenuTypes = sellerMenuTypes;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return sellerMenuTypes.size();
    }

    @Override
    public Object getItem(int position) {
        return sellerMenuTypes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_left_menu, null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.leftMenu.setText(sellerMenuTypes.get(position).getSmt_name());
        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.left_menu)
        TextView leftMenu;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
