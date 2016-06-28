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
import com.fragrantmeal.entity.BuyOrder;
import com.fragrantmeal.entity.Car;
import com.fragrantmeal.util.Config;
import com.fragrantmeal.util.GsonUtil;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.greenrobot.event.EventBus;

/**
 * Created by CaoBin on 2016/4/26.
 */
public class OrderAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Context context;
    private List<BuyOrder> orders;

    public OrderAdapter(Context context, List<BuyOrder> orders) {
        this.context = context;
        this.orders = orders;
        inflater = LayoutInflater.from(context);
    }

    public List<BuyOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<BuyOrder> orders) {
        this.orders = orders;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return orders.size();
    }

    @Override
    public Object getItem(int position) {
        return orders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_order, null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.orderSellerName.setText(orders.get(position).getSeller().getSeller_name());
        viewHolder.tvOrderStatus.setText(orders.get(position).getTakeoutOrderStatus().getTos_status());
        viewHolder.orderSellerTime.setText(orders.get(position).getTakeoutOrderStatus().getTos_time());
        int price=0;
        for (Car car : orders.get(position).getCars()) {
            price=price+(car.getSellerDish().getSd_price()*car.getNum());
        }
        String notice=orders.get(position).getSeller().getSeller_notice();
        String man=notice.substring((notice.indexOf("满") + 1), notice.indexOf("减"));
        String jian=notice.substring((notice.indexOf("减")+1),notice.lastIndexOf("元"));
        if (price>=Integer.parseInt(man)){
            viewHolder.tvOrderZong.setText("￥" + (price-Integer.parseInt(jian)+orders.get(position).getSeller().getSeller_df()));
        }else {
            viewHolder.tvOrderZong.setText("￥" +(price+orders.get(position).getSeller().getSeller_df()));
        }
        if (orders.get(position).getTakeoutOrderStatus().getTos_status().equals("已完成")){
            viewHolder.ll1.setVisibility(View.VISIBLE);
        }else {
            viewHolder.ll1.setVisibility(View.GONE);
        }
        viewHolder.btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new EventBusSelect(Config.BUY_CAR_ARGO, GsonUtil.toJson(orders.get(position))));
            }
        });

        viewHolder.ll2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new EventBusSelect(Config.BUY_CAR_INFO, GsonUtil.toJson(orders.get(position))));
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.order_seller_name)
        TextView orderSellerName;
        @InjectView(R.id.tv_order_status)
        TextView tvOrderStatus;
        @InjectView(R.id.order_seller_time)
        TextView orderSellerTime;
        @InjectView(R.id.tv_order_zong)
        TextView tvOrderZong;
        @InjectView(R.id.btn_buy)
        Button btnBuy;
        @InjectView(R.id.ll_1)
        LinearLayout ll1;
        @InjectView(R.id.ll_2)
        LinearLayout ll2;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
