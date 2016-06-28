package com.fragrantmeal.activity;

import android.app.Activity;
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
import com.fragrantmeal.entity.UserInfo;
import com.fragrantmeal.util.Config;
import com.fragrantmeal.util.GsonUtil;
import com.fragrantmeal.util.ShowToast;
import com.fragrantmeal.util.StringHandler;
import com.mj.core.util.IntentUtils;
import com.mj.core.util.SharedPrefsUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import rx.android.app.AppObservable;

/**
 * Created by CaoBin on 2016/4/24.
 */
public class AddAddressActivity extends Activity {

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

    private UserInfo user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        ButterKnife.inject(this);
        EventBus.getDefault().register(this);
    }

    @OnClick({R.id.btn_back,R.id.btn_save,R.id.tv_address_1})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_save:
                user = (UserInfo) GsonUtil.fromJson(SharedPrefsUtil.getString(this, "LoginUser", "user"), UserInfo.class);
                if (tvAddress1.getText().toString().trim()!=""&&etAddress2.getText().toString().trim()!=""&&etDaName.getText().toString().trim()!=""&&etDaPhone.getText().toString().trim()!=""){
                    DeliveryAddress deliveryAddress=new DeliveryAddress();
                    deliveryAddress.setDa_id(StringHandler.createSerialId(System.currentTimeMillis()));
                    deliveryAddress.setDa_name(etDaName.getText().toString().trim());
                    deliveryAddress.setDa_phone(etDaPhone.getText().toString().trim());
                    deliveryAddress.setU_id(user.getU_id());
                    deliveryAddress.setUser_alias(user.getUser_alias());
                    deliveryAddress.setDa_address(tvAddress1.getText().toString().trim() + "*"+etAddress2.getText().toString().trim());
                    addAddress(deliveryAddress);
                }else {
                    ShowToast.showToast(this,"参数不能为空");
                }
                break;
            case R.id.tv_address_1:
                IntentUtils.forward(this,BaiduMapAddress.class);
                ivAddress.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        ButterKnife.reset(this);

    }

    public void  addAddress(DeliveryAddress deliveryAddress){
        AppObservable.bindActivity(AddAddressActivity.this, App.getfMealNet().AddUserAddress(deliveryAddress)).subscribe(
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
