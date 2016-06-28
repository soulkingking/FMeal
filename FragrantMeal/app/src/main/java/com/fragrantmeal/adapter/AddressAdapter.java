package com.fragrantmeal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fragrantmeal.EventBus.EventBusSelect;
import com.fragrantmeal.R;
import com.fragrantmeal.entity.DeliveryAddress;
import com.fragrantmeal.util.Config;
import com.fragrantmeal.util.GsonUtil;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.greenrobot.event.EventBus;

/**
 * Created by CaoBin on 2016/4/24.
 */
public class AddressAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Context context;
    private List<DeliveryAddress> deliveryAddresses;

    public AddressAdapter(Context context, List<DeliveryAddress> deliveryAddresses) {
        this.context = context;
        this.deliveryAddresses = deliveryAddresses;
        inflater = LayoutInflater.from(context);
    }

    public List<DeliveryAddress> getDeliveryAddresses() {
        return deliveryAddresses;
    }

    public void setDeliveryAddresses(List<DeliveryAddress> deliveryAddresses) {
        this.deliveryAddresses = deliveryAddresses;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return deliveryAddresses.size();
    }

    @Override
    public Object getItem(int position) {
        return deliveryAddresses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_address, null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.tvName.setText(deliveryAddresses.get(position).getDa_name());
        String address1=deliveryAddresses.get(position).getDa_address().substring(0, deliveryAddresses.get(position).getDa_address().indexOf("*"));
        String address2=deliveryAddresses.get(position).getDa_address().substring(deliveryAddresses.get(position).getDa_address().indexOf("*")+ 1);
        viewHolder.tvAddress.setText(address1+address2);
        viewHolder.tvPhone.setText(deliveryAddresses.get(position).getDa_phone());
        viewHolder.updateAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new EventBusSelect(Config.UPDATE_ADDRESS, GsonUtil.toJson(deliveryAddresses.get(position))));
            }
        });
        viewHolder.llAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new EventBusSelect(Config.ORDER_ADDRESS, GsonUtil.toJson(deliveryAddresses.get(position))));
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.tv_name)
        TextView tvName;
        @InjectView(R.id.tv_phone)
        TextView tvPhone;
        @InjectView(R.id.tv_address)
        TextView tvAddress;
        @InjectView(R.id.update_address)
        Button updateAddress;
        @InjectView(R.id.ll_address)
        LinearLayout llAddress;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
