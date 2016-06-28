package com.fragrantmeal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fragrantmeal.R;
import com.fragrantmeal.entity.Car;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by CaoBin on 2016/4/25.
 */
public class OrderSellerAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Context context;
    private List<Car> cars;

    public OrderSellerAdapter(Context context, List<Car> cars) {
        this.context = context;
        this.cars = cars;
        inflater=LayoutInflater.from(context);
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return cars.size();
    }

    @Override
    public Object getItem(int position) {
        return cars.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_order_seller_dish, null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.orderSellerName.setText(cars.get(position).getSellerDish().getSd_name());
        viewHolder.orderSellerNum.setText("x"+cars.get(position).getNum());
        viewHolder.orderSellerPrice.setText("￥"+cars.get(position).getSellerDish().getSd_price());
        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.order_seller_name)
        TextView orderSellerName;
        @InjectView(R.id.order_seller_num)
        TextView orderSellerNum;
        @InjectView(R.id.order_seller_price)
        TextView orderSellerPrice;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
