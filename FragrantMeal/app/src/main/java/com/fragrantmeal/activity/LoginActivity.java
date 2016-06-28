package com.fragrantmeal.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.fragrantmeal.App;
import com.fragrantmeal.EventBus.EventBusSelect;
import com.fragrantmeal.R;
import com.fragrantmeal.entity.UserInfo;
import com.fragrantmeal.util.Config;
import com.fragrantmeal.util.GsonUtil;
import com.fragrantmeal.util.ShowToast;
import com.mj.core.util.IntentUtils;
import com.mj.core.util.SharedPrefsUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import rx.android.app.AppObservable;

/**
 * Created by CaoBin on 2016/3/21.
 */
public class LoginActivity extends Activity {
    @InjectView(R.id.tv_user_phone)
    EditText tvUserPhone;
    @InjectView(R.id.tv_user_pass)
    EditText tvUserPass;
    @InjectView(R.id.btn_login)
    Button btnLogin;
    @InjectView(R.id.btn_forget_pass)
    TextView btnForgetPass;
    @InjectView(R.id.save)
    CheckBox save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        EventBus.getDefault().register(this);
        UserInfo user= (UserInfo) GsonUtil.fromJson(SharedPrefsUtil.getString(this, "userInfo", "user"), UserInfo.class);
        if (user==null){
            save.setChecked(false);
            save.setButtonDrawable(R.drawable.ic_checkbox_unselected);
        }else {
            save.setChecked(true);
            save.setButtonDrawable(R.drawable.ic_checkbox_selected);
            tvUserPhone.setText(user.getUsername());
            tvUserPass.setText(user.getPassword());
        }
    }

    @OnClick({R.id.btn_login, R.id.btn_forget_pass,R.id.save,R.id.tv_registered})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login :
                login();
                break;
            case R.id.save:
                if (save.isChecked()){
                    save.setButtonDrawable(R.drawable.ic_checkbox_selected);
                }else {
                    save.setButtonDrawable(R.drawable.ic_checkbox_unselected);
                    SharedPrefsUtil.clear(LoginActivity.this,"userInfo");
                }
                break;
            case R.id.btn_forget_pass :
                IntentUtils.forward(this,ForgotPassWordActivity.class);
                break;
            case R.id.tv_registered:
                IntentUtils.forward(this,RegisterAcitivty.class);
                break;
        }

    }

    public void login() {
        if (!tvUserPhone.getText().toString().trim().equals("")
                && !tvUserPass.getText().toString().trim().equals("")) {

            String userPhone = tvUserPhone.getText().toString().trim();
            String userPass = tvUserPass.getText().toString().trim();
            UserInfo user = new UserInfo();
            user.setUsername(userPhone);
            user.setPassword(userPass);
            AppObservable.bindActivity(this, App.getfMealNet().login(user))
                    .subscribe(
                            success -> {
                                if (success!=null) {
                                    if (save.isChecked()){
                                        SharedPrefsUtil.setValue(this, "userInfo", "user", GsonUtil.toJson(user));
                                    }
                                    SharedPrefsUtil.setValue(this,"LoginUser","user", GsonUtil.toJson(success));
                                    IntentUtils.forward(this,MainActivity.class);
                                    this.finish();
                                } else {
                                    ShowToast.showToast(this, "登陆失败，请重新输入用户名和密码！");
                                    tvUserPhone.setText("");
                                    tvUserPass.setText("");
                                }
                            },
                            e -> {
                                ShowToast.showToast(this, "网路连接异常");
                            });

        } else {
            if (tvUserPhone.getText().toString().trim().equals("")) {
                tvUserPhone.setError("手机号不能为空！");
            }
            if (tvUserPass.getText().toString().trim().equals("")) {
                tvUserPass.setError("密码不能为空！");
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
        EventBus.getDefault().unregister(this);
    }

    public void onEventMainThread(EventBusSelect event) {

        switch (event.getActionType()){
            case Config.REGISTER:
                UserInfo userInfo= (UserInfo) GsonUtil.fromJson(event.getMessage(),UserInfo.class);
                tvUserPhone.setText(userInfo.getUsername());
                tvUserPass.setText(userInfo.getPassword());
                login();
                break;
        }

    }

}
