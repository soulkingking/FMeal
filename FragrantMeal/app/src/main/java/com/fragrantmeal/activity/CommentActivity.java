package com.fragrantmeal.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.fragrantmeal.App;
import com.fragrantmeal.EventBus.EventBusSelect;
import com.fragrantmeal.R;
import com.fragrantmeal.entity.Seller;
import com.fragrantmeal.entity.SellerComment;
import com.fragrantmeal.entity.TakeoutOrder;
import com.fragrantmeal.entity.UserInfo;
import com.fragrantmeal.util.Config;
import com.fragrantmeal.util.GsonUtil;
import com.mj.core.util.SharedPrefsUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import rx.android.app.AppObservable;

/**
 * Created by CaoBin on 2016/4/29.
 */
public class CommentActivity extends Activity {

    @InjectView(R.id.btn_back)
    Button btnBack;
    @InjectView(R.id.ratingBar1)
    RatingBar ratingBar1;
    @InjectView(R.id.ratingBar2)
    RatingBar ratingBar2;
    @InjectView(R.id.et_content)
    EditText etContent;

    private UserInfo user;
    private Intent intent;
    private Seller seller;
    private TakeoutOrder takeoutOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ButterKnife.inject(this);
        EventBus.getDefault().register(this);
        init();
    }

    public void init(){
        intent=getIntent();
        user = (UserInfo) GsonUtil.fromJson(SharedPrefsUtil.getString(this, "LoginUser", "user"), UserInfo.class);
        seller= (Seller) intent.getSerializableExtra("seller");
        takeoutOrder= (TakeoutOrder) intent.getSerializableExtra("takeoutOrder");
    }


    @OnClick({R.id.btn_back,R.id.btn_fabu})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_fabu:
                SellerComment comment=new SellerComment();
                comment.setS_id(seller.getS_id());
                comment.setSc_content(etContent.getText().toString().trim());
                comment.setSc_eat((int)ratingBar1.getRating());
                comment.setSc_service((int)ratingBar2.getRating());
                comment.setSeller_name(seller.getSeller_name());
                comment.setU_id(user.getU_id());
                comment.setUser_alias(user.getUser_alias());
                comment.setTo_id(takeoutOrder.getTo_id());
                addComment(comment);
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void  addComment(SellerComment comment){
        AppObservable.bindActivity(CommentActivity.this, App.getfMealNet().addComment(comment)).subscribe(
                success -> {
                    EventBus.getDefault().post(new EventBusSelect(Config.COMMENT, ""));
                    finish();
                },
                e -> {

                }
        );
    }

    public void onEventMainThread(EventBusSelect event) {
    }
}
