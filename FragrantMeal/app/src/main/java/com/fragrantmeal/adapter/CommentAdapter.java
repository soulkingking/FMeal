package com.fragrantmeal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.fragrantmeal.R;
import com.fragrantmeal.entity.SellerComment;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by CaoBin on 2016/4/29.
 */
public class CommentAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<SellerComment> sellerComments;
    private Context context;

    public CommentAdapter(Context context, List<SellerComment> sellerComments) {
        this.context = context;
        this.sellerComments = sellerComments;
        inflater = LayoutInflater.from(context);
    }

    public List<SellerComment> getSellerComments() {
        return sellerComments;
    }

    public void setSellerComments(List<SellerComment> sellerComments) {
        this.sellerComments = sellerComments;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return sellerComments.size();
    }

    @Override
    public Object getItem(int position) {
        return sellerComments.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_all_comment, null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.commentUser.setText(sellerComments.get(position).getUser_alias());
        int starNum=sellerComments.get(position).getSc_service()+sellerComments.get(position).getSc_service();
        if (starNum==0){
            viewHolder.commentRb.setRating(0);
        }else {
            viewHolder.commentRb.setRating(starNum/2);
        }
        String nian=sellerComments.get(position).getSc_id().substring(0,4);
        String yue=sellerComments.get(position).getSc_id().substring(4,6);
        String tian=sellerComments.get(position).getSc_id().substring(6,8);
        viewHolder.commentTime.setText(nian+"-"+yue+"-"+tian);
        viewHolder.comment.setText(sellerComments.get(position).getSc_content());
        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.comment_user)
        TextView commentUser;
        @InjectView(R.id.comment_rb)
        RatingBar commentRb;
        @InjectView(R.id.comment_time)
        TextView commentTime;
        @InjectView(R.id.comment)
        TextView comment;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
