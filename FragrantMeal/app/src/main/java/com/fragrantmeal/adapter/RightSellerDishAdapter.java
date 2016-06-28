package com.fragrantmeal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fragrantmeal.App;
import com.fragrantmeal.EventBus.EventBusSelect;
import com.fragrantmeal.R;
import com.fragrantmeal.entity.Car;
import com.fragrantmeal.entity.SellerDish;
import com.fragrantmeal.entity.SellerDishData;
import com.fragrantmeal.entity.SellerMenuType;
import com.fragrantmeal.util.Config;
import com.fragrantmeal.util.GsonUtil;
import com.fragrantmeal.view.SectionedBaseAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.greenrobot.event.EventBus;

/**
 * Created by CaoBin on 2016/4/22.
 */
public class RightSellerDishAdapter extends SectionedBaseAdapter{
    private List<SellerMenuType> sellerMenuTypes;
    private List<SellerDishData> sellerDishDatases;
    private Context context;
    private LayoutInflater inflater;
    private Map<SellerDish,Integer> map;


    public RightSellerDishAdapter(List<SellerMenuType> sellerMenuTypes, Context context, List<SellerDishData> sellerDishDatases) {
        this.sellerMenuTypes = sellerMenuTypes;
        this.context = context;
        this.sellerDishDatases = sellerDishDatases;
        inflater = LayoutInflater.from(context);
        map=new HashMap<>();
        for (SellerDishData sellerDishData : sellerDishDatases) {
            for (SellerDish sellerDish : sellerDishData.getSellerDishs()) {
                map.put(sellerDish,0);
            }
        }
    }

    public List<SellerDishData> getSellerDishs() {
        return sellerDishDatases;
    }

    public void setSellerDishs(List<SellerDishData> sellerDishDatases) {
        this.sellerDishDatases = sellerDishDatases;
    }

    public List<SellerMenuType> getSellerMenuTypes() {
        return sellerMenuTypes;
    }

    public void setSellerMenuTypes(List<SellerMenuType> sellerMenuTypes) {
        this.sellerMenuTypes = sellerMenuTypes;
    }

    public void setData(List<SellerMenuType> sellerMenuTypes, List<SellerDishData> sellerDishDatases) {
        this.sellerMenuTypes = sellerMenuTypes;
        this.sellerDishDatases = sellerDishDatases;
        for (SellerDishData sellerDishData : sellerDishDatases) {
            for (SellerDish sellerDish : sellerDishData.getSellerDishs()) {
                map.put(sellerDish,0);
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public Object getItem(int section, int position) {
        return sellerDishDatases.get(section).getSellerDishs().get(position);
    }

    @Override
    public long getItemId(int section, int position) {
        return position;
    }

    @Override
    public int getSectionCount() {
        return sellerMenuTypes.size();
    }

    @Override
    public int getCountForSection(int section) {
        return sellerDishDatases.get(section).getSellerDishs().size();
    }

    @Override
    public View getItemView(int section, int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        SellerDish sellerDish= (SellerDish) getItem(section,position);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_right_seller_dish, null);
            viewHolder=new ViewHolder(convertView);
            final ViewHolder finalViewHolder = viewHolder;
            viewHolder.btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int num = 0;
                    SellerDish sellerDishData= (SellerDish) finalViewHolder.btnAdd.getTag();
                    num = map.get(sellerDishData) + 1;
                    Car car = new Car();
                    car.setNum(num);
                    car.setSellerDish(sellerDishData);
                    map.put(sellerDishData, num);
                    EventBus.getDefault().post(new EventBusSelect(Config.BUY_CAR, GsonUtil.toJson(car)));
                    notifyDataSetChanged();
                }
            });

            viewHolder.btnSub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int num = 0;
                    SellerDish sellerDishData= (SellerDish) finalViewHolder.btnAdd.getTag();
                    num = map.get(sellerDishData) - 1;
                    Car car = new Car();
                    car.setNum(num);
                    car.setSellerDish(sellerDishData);
                    map.put(sellerDishData, num);
                    EventBus.getDefault().post(new EventBusSelect(Config.BUY_CAR, GsonUtil.toJson(car)));
                    notifyDataSetChanged();
                }
            });
            convertView.setTag(viewHolder);
            viewHolder.btnAdd.setTag(sellerDish);
            viewHolder.btnSub.setTag(sellerDish);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
            viewHolder.btnAdd.setTag(sellerDish);
            viewHolder.btnSub.setTag(sellerDish);
        }
        App.getImage(context, viewHolder.sellerDishIcon, sellerDishDatases.get(section).getSellerDishs().get(position).getSd_icon());
        viewHolder.sellerDishName.setText(sellerDishDatases.get(section).getSellerDishs().get(position).getSd_name());
        viewHolder.sellerDishNum.setText("月售" + sellerDishDatases.get(section).getSellerDishs().get(position).getSd_saledCount());
        viewHolder.sellerDishPrice.setText("￥" + sellerDishDatases.get(section).getSellerDishs().get(position).getSd_price());
        if (map.get(sellerDishDatases.get(section).getSellerDishs().get(position))>0){
            viewHolder.btnSub.setVisibility(View.VISIBLE);
            viewHolder.buyNum.setVisibility(View.VISIBLE);
            viewHolder.buyNum.setText(""+map.get(sellerDishDatases.get(section).getSellerDishs().get(position)));
        }else {
            viewHolder.btnSub.setVisibility(View.INVISIBLE);
            viewHolder.buyNum.setVisibility(View.INVISIBLE);
            viewHolder.buyNum.setText("0");
        }
//        final ViewHolder finalViewHolder = viewHolder;
//        finalViewHolder.btnAdd.setTag(position);
//        finalViewHolder.buyNum.setTag(position);
//        viewHolder.btnAdd.setOnClickListener(v -> {
//            int num=0;
//            finalViewHolder.btnSub.setVisibility(View.VISIBLE);
//            finalViewHolder.buyNum.setVisibility(View.VISIBLE);
//            num=Integer.parseInt(finalViewHolder.buyNum.getText().toString().trim())+1;
//            finalViewHolder.buyNum.setText(num + "");
//            Car car=new Car();
//            car.setNum(num);
//            car.setSellerDish(sellerDishDatases.get(section).getSellerDishs().get(position));
//            EventBus.getDefault().post(new EventBusSelect(Config.BUY_CAR,GsonUtil.toJson(car)));
//        });
//        viewHolder.btnSub.setOnClickListener(v -> {
//            int num = 0;
//            num = Integer.parseInt(finalViewHolder.buyNum.getText().toString().trim()) - 1;
//            if (num > 0) {
//                finalViewHolder.buyNum.setText(num + "");
//            } else {
//                finalViewHolder.buyNum.setText("0");
//                finalViewHolder.buyNum.setVisibility(View.INVISIBLE);
//                finalViewHolder.btnSub.setVisibility(View.INVISIBLE);
//            }
//            Car car = new Car();
//            car.setNum(num);
//            car.setSellerDish(sellerDishDatases.get(section).getSellerDishs().get(position));
//            EventBus.getDefault().post(new EventBusSelect(Config.BUY_CAR, GsonUtil.toJson(car)));
//        });
        return convertView;
    }

    @Override
    public View getSectionHeaderView(int section, View convertView, ViewGroup parent) {
        ViewHolder1 viewHolder1 = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_sellerdish_header, null);
            viewHolder1 = new ViewHolder1(convertView);
            convertView.setTag(viewHolder1);
        } else {
            viewHolder1 = (ViewHolder1) convertView.getTag();
        }
        viewHolder1.head.setText(sellerMenuTypes.get(section).getSmt_name());
        return convertView;
    }

    static class ViewHolder1 {
        @InjectView(R.id.head)
        TextView head;

        ViewHolder1(View view) {
            ButterKnife.inject(this, view);
        }
    }

    static class ViewHolder {
        @InjectView(R.id.seller_dish_icon)
        ImageView sellerDishIcon;
        @InjectView(R.id.seller_dish_name)
        TextView sellerDishName;
        @InjectView(R.id.seller_dish_num)
        TextView sellerDishNum;
        @InjectView(R.id.seller_dish_price)
        TextView sellerDishPrice;
        @InjectView(R.id.btn_sub)
        Button btnSub;
        @InjectView(R.id.buy_num)
        TextView buyNum;
        @InjectView(R.id.btn_add)
        Button btnAdd;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
