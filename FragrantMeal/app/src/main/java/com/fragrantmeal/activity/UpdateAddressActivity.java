package com.fragrantmeal.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.fragrantmeal.App;
import com.fragrantmeal.EventBus.EventBusSelect;
import com.fragrantmeal.R;
import com.fragrantmeal.entity.DeliveryAddress;
import com.fragrantmeal.util.Config;
import com.fragrantmeal.util.GsonUtil;
import com.fragrantmeal.util.ShowToast;
import com.mj.core.util.IntentUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import rx.android.app.AppObservable;

/**
 * Created by CaoBin on 2016/4/24.
 */
public class UpdateAddressActivity extends Activity {

    @InjectView(R.id.btn_back)
    Button btnBack;
    @InjectView(R.id.btn_save)
    Button btnSave;
    @InjectView(R.id.et_da_name)
    EditText etDaName;
    @InjectView(R.id.et_da_phone)
    EditText etDaPhone;
    @InjectView(R.id.iv_address)
    ImageView ivAddress;
    @InjectView(R.id.tv_address_1)
    TextView tvAddress1;
    @InjectView(R.id.et_address2)
    EditText etAddress2;

    private Intent intent;
    private DeliveryAddress deliveryAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_address);
        ButterKnife.inject(this);
        EventBus.getDefault().register(this);
        initView();
    }

    public void initView(){
        intent=getIntent();
        deliveryAddress = (DeliveryAddress) GsonUtil.fromJson(intent.getStringExtra(Config.UPDATE_ADDRESS), DeliveryAddress.class);
        ivAddress.setVisibility(View.GONE);
        tvAddress1.setText(deliveryAddress.getDa_address().substring(0, deliveryAddress.getDa_address().indexOf("*")));
        etAddress2.setText(deliveryAddress.getDa_address().substring(deliveryAddress.getDa_address().indexOf("*")+ 1));
        etDaName.setText(deliveryAddress.getDa_name());
        etDaPhone.setText(deliveryAddress.getDa_phone());

    }

    @OnClick({R.id.btn_back,R.id.btn_save,R.id.tv_address_1})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_save:
                if (tvAddress1.getText().toString().trim()!=""&&etAddress2.getText().toString().trim()!=""&&etDaName.getText().toString().trim()!=""&&etDaPhone.getText().toString().trim()!=""){
                    DeliveryAddress deliveryAddress=new DeliveryAddress();
                    deliveryAddress.setDa_id(this.deliveryAddress.getDa_id());
                    deliveryAddress.setDa_name(etDaName.getText().toString().trim());
                    deliveryAddress.setDa_phone(etDaPhone.getText().toString().trim());
                    deliveryAddress.setU_id(this.deliveryAddress.getU_id());
                    deliveryAddress.setUser_alias(this.deliveryAddress.getUser_alias());
                    deliveryAddress.setDa_address(tvAddress1.getText().toString().trim() + "*" + etAddress2.getText().toString().trim());
                    updateAddress(deliveryAddress);
                }else {
                    ShowToast.showToast(this, "参数不能为空");
                }
                break;
            case R.id.tv_address_1:
                IntentUtils.forward(this, BaiduMapAddress.class);
                ivAddress.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void updateAddress(DeliveryAddress deliveryAddress){
        AppObservable.bindActivity(this, App.getfMealNet().UpdateUserAddress(deliveryAddress)).subscribe(
                success -> {
                    EventBus.getDefault().post(new EventBusSelect(Config.ADDRESS, GsonUtil.toJson(success)));
                    finish();
                },
                e -> {

                }

        );

    }

    public void onEventMainThread(EventBusSelect event) {
        switch (event.getActionType()){
            case Config.MYADDRESS:
                tvAddress1.setText(event.getMessage());
                break;
        }

    }
}
