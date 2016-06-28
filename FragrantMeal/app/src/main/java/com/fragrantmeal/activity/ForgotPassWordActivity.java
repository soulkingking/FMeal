package com.fragrantmeal.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import de.greenrobot.event.EventBus;
import rx.android.app.AppObservable;

/**
 * Created by CaoBin on 2016/5/1.
 */
public class ForgotPassWordActivity extends Activity {

    public final static String APPKEY = "124bf7f4656cc";
    public final static String APPSECRET = "9bf70b0b393d3e2009b5087495755eda";

    @InjectView(R.id.user_phone)
    EditText userPhone;
    @InjectView(R.id.message)
    EditText message;
    @InjectView(R.id.new_password)
    EditText newPassword;
    @InjectView(R.id.new_repeat_password)
    EditText newRepeatPassword;
    @InjectView(R.id.btn_send_message)
    Button btnSendMessage;

    private EventHandler eh = new EventHandler() {
        @Override
        public void afterEvent(int event, int result, Object data) {
            switch (event) {
                case SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE:
                    if (result == SMSSDK.RESULT_COMPLETE) {
                        EventBus.getDefault().post(new EventBusSelect(Config.SSM, "验证成功"));
                    } else {
                        EventBus.getDefault().post(new EventBusSelect(Config.SSM, "验证码错误"));
                    }
                    break;
                case SMSSDK.EVENT_GET_VERIFICATION_CODE:
                    if (result == SMSSDK.RESULT_COMPLETE) {
                        EventBus.getDefault().post(new EventBusSelect(Config.SSM, "验证码已发送成功"));
                    } else {
                        EventBus.getDefault().post(new EventBusSelect(Config.SSM, "验证码发送失败"));
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        ButterKnife.inject(this);
        EventBus.getDefault().register(this);
        init();
    }

    private void init() {
        SMSSDK.initSDK(this, APPKEY, APPSECRET);
        SMSSDK.registerEventHandler(eh); //注册短信回调
    }

    @OnClick({R.id.btn_back,R.id.btn_send_message,R.id.go_update})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_send_message:
                if (!userPhone.getText().toString().trim().equals("")) {
                    SMSSDK.getVerificationCode("86", userPhone.getText().toString().trim());
                    new Thread(() -> {
                        for (int i = 30; i > 0; i--) {
                            EventBus.getDefault().post(new EventBusSelect(Config.TIME,i+""));
                            if (i <= 0) {
                                break;
                            }
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        EventBus.getDefault().post(new EventBusSelect(Config.TIME,"重新发送"));
                    }).start();
                } else {
                    userPhone.setError("手机号码不能为空");
                }
                break;
            case R.id.go_update:
                if (!userPhone.getText().toString().trim().equals("")){
                    if (!message.getText().toString().trim().equals("")){
                        SMSSDK.submitVerificationCode("86", userPhone.getText().toString().trim(), message
                                .getText().toString());
                    }else {
                        message.setError("验证码不能为空");
                    }
                }else {
                    userPhone.setError("手机号码不能为空");
                }
                break;
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eh);
        EventBus.getDefault().unregister(this);
    }

    public void onEventMainThread(EventBusSelect event) {
        switch (event.getActionType()){
            case Config.TIME:
                if (event.getMessage().equals("重新发送")){
                    btnSendMessage.setClickable(true);
                }else {
                    btnSendMessage.setClickable(false);
                }
                btnSendMessage.setText(event.getMessage()+"秒");
                break;
            case Config.SSM:
                switch (event.getMessage()){
                    case "验证码错误":
                        message.setText("");
                        break;
                    case "验证成功":
                        updatePassword();
                        break;
                }
                ShowToast.showToast(this,event.getMessage());
                break;
        }
    }

    public void updatePassword(){
        if (!userPhone.getText().toString().trim().equals("")){
            if (!newPassword.getText().toString().trim().equals("")){
                if (!newRepeatPassword.getText().toString().trim().equals("")){
                    UserInfo userInfo=new UserInfo();
                    userInfo.setUsername(userPhone.getText().toString().trim());
                    userInfo.setPassword(newPassword.getText().toString().trim());
                    AppObservable.bindActivity(ForgotPassWordActivity.this,App.getfMealNet().updatePassword(userInfo)).subscribe(
                            success ->{
                                if (success!=null){
                                    EventBus.getDefault().post(new EventBusSelect(Config.REGISTER, GsonUtil.toJson(success)));
                                    finish();
                                }
                            },
                            e ->{

                            }
                    );

                }else {
                    newRepeatPassword.setError("密码不能为空");
                }
            }else {
                newPassword.setError("密码不能为空");
            }
        }else {
            userPhone.setError("手机号码不能为空");
        }

    }


}
