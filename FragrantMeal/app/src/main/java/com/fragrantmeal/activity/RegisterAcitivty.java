package com.fragrantmeal.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.fragrantmeal.App;
import com.fragrantmeal.EventBus.EventBusSelect;
import com.fragrantmeal.R;
import com.fragrantmeal.entity.UserInfo;
import com.fragrantmeal.util.Config;
import com.fragrantmeal.util.GsonUtil;
import com.fragrantmeal.util.ShowToast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import rx.android.app.AppObservable;

/**
 * Created by CaoBin on 2016/4/30.
 */
public class RegisterAcitivty extends Activity {

    @InjectView(R.id.btn_back)
    Button btnBack;
    @InjectView(R.id.register_username)
    EditText registerUsername;
    @InjectView(R.id.register_phone)
    EditText registerPhone;
    @InjectView(R.id.register_password)
    EditText registerPassword;
    @InjectView(R.id.register_repeat_password)
    EditText registerRepeatPassword;
    @InjectView(R.id.user_sex_woman)
    RadioButton userSexWoman;
    @InjectView(R.id.user_sex_man)
    RadioButton userSexMan;
    @InjectView(R.id.user_signature)
    EditText userSignature;
    @InjectView(R.id.go_register)
    Button goRegister;
    @InjectView(R.id.user_age)
    EditText userAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.inject(this);
    }


    @OnClick({R.id.btn_back, R.id.user_sex_woman, R.id.user_sex_man, R.id.go_register})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.user_sex_woman:
                userSexMan.setChecked(false);
                userSexWoman.setChecked(true);
                break;
            case R.id.user_sex_man:
                userSexMan.setChecked(true);
                userSexWoman.setChecked(false);
                break;
            case R.id.go_register:
                UserInfo userInfo = new UserInfo();
                if (!registerUsername.getText().toString().trim().equals("")) {
                    if (!registerPhone.getText().toString().trim().equals("")) {
                        if (!registerPassword.getText().toString().trim().equals("")) {
                            if (!registerRepeatPassword.getText().toString().trim().equals("")) {
                                if (!userSignature.getText().toString().trim().equals("")) {
                                    if(!userAge.getText().toString().trim().equals("")){
                                        if (registerPassword.getText().toString().trim().equals(registerRepeatPassword.getText().toString().trim())){
                                            userInfo.setPassword(registerRepeatPassword.getText().toString().trim());
                                            userInfo.setUser_age(Integer.parseInt(userAge.getText().toString().trim()));
                                            userInfo.setUser_alias(registerUsername.getText().toString().trim());
                                            if (userSexMan.isChecked()){
                                                userInfo.setUser_sex(userSexMan.getText().toString().trim());
                                            }
                                            if (userSexWoman.isChecked()){
                                                userInfo.setUser_sex(userSexWoman.getText().toString().trim());
                                            }
                                            userInfo.setUsername(registerPhone.getText().toString().trim());
                                            userInfo.setUser_signature(userSignature.getText().toString().trim());
                                            findUser(userInfo);
                                        }else {
                                            ShowToast.showToast(this,"两次输入的密码必须相同");
                                        }
                                    }else{
                                        userAge.setError("年龄不能为空");
                                    }
                                } else {
                                    userSignature.setError("个性签名不能为空");
                                }
                            } else {
                                registerRepeatPassword.setError("密码不能为空");
                            }
                        } else {
                            registerPassword.setError("密码不能为空");
                        }
                    } else {
                        registerPhone.setError("手机号不能为空");
                    }
                } else {
                    registerUsername.setError("用户昵称不能为空");
                }
                break;
        }

    }

    private void findUser(UserInfo userInfo) {
        AppObservable.bindActivity(RegisterAcitivty.this,App.getfMealNet().FindRegister(userInfo.getUsername())).subscribe(
                success -> {
                    if (!success.booleanValue()) {
                        registerUser(userInfo);
                    } else {
                        ShowToast.showToast(this,"该手机号已经被注册过了");
                    }
                },
                e -> {

                }
        );

    }

    private void registerUser(UserInfo user){
        AppObservable.bindActivity(RegisterAcitivty.this,App.getfMealNet().register(user)).subscribe(
                success ->{
                    if (success!=null){
                        EventBus.getDefault().post(new EventBusSelect(Config.REGISTER, GsonUtil.toJson(success)));
                        finish();
                    }
                },
                e ->{
                    ShowToast.showToast(this,"网络连接异常");
                }
        );
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
