package com.fragrantmeal.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.fragrantmeal.App;
import com.fragrantmeal.R;
import com.fragrantmeal.entity.User;
import com.fragrantmeal.util.GsonUtil;
import com.fragrantmeal.util.ShowToast;
import com.mj.core.util.IntentUtils;
import com.mj.core.util.SharedPrefsUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
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
        User user= (User) GsonUtil.fromJson(SharedPrefsUtil.getString(this, "userInfo", "user"), User.class);
        if (user==null){
            save.setChecked(false);
            save.setButtonDrawable(R.drawable.ic_checkbox_unselected);
        }else {
            save.setChecked(true);
            save.setButtonDrawable(R.drawable.ic_checkbox_selected);
            tvUserPhone.setText(user.getUserPhone());
            tvUserPass.setText(user.getPassWord());
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

                break;
            case R.id.tv_registered:

                break;
        }

    }

    public void login() {
        if (!tvUserPhone.getText().toString().trim().equals("")
                && !tvUserPass.getText().toString().trim().equals("")) {

            String userPhone = tvUserPhone.getText().toString().trim();
            String userPass = tvUserPass.getText().toString().trim();
            User user = new User(userPhone, userPass);
            AppObservable.bindActivity(this, App.getfMealNet().login(user))
                    .subscribe(
                            success -> {
                                if (1 == Integer.parseInt(success.getLoginState())) {
                                    if (save.isChecked()){
                                        SharedPrefsUtil.setValue(this,"userInfo","user", GsonUtil.toJson(user));
                                    }
                                    IntentUtils.forward(this, MainActivity.class);
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
                tvUserPhone.setError("用户名不能为空！");
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
    }

}
