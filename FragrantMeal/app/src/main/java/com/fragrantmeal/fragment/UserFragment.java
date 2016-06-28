package com.fragrantmeal.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fragrantmeal.App;
import com.fragrantmeal.EventBus.EventBusSelect;
import com.fragrantmeal.R;
import com.fragrantmeal.activity.AddressActivity;
import com.fragrantmeal.activity.CollectonActivity;
import com.fragrantmeal.activity.SetUserInforActivity;
import com.fragrantmeal.activity.VersionInfor;
import com.fragrantmeal.entity.UserInfo;
import com.fragrantmeal.util.Config;
import com.fragrantmeal.util.GsonUtil;
import com.mj.core.util.IntentUtils;
import com.mj.core.util.L;
import com.mj.core.util.SharedPrefsUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by CaoBin on 2016/3/20.
 */
public class UserFragment extends Fragment {

    @InjectView(R.id.tv_nickname)
    TextView tvNickname;
    @InjectView(R.id.personal_information)
    CircleImageView personalInformation;
    @InjectView(R.id.tv_signature)
    TextView tvSignature;
    private View view;
    private Intent intent;
    private UserInfo user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_user, container, false);
        }
        ButterKnife.inject(this, view);
        EventBus.getDefault().register(this);
        init();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.my_focus, R.id.coupon, R.id.wallet, R.id.set_up, R.id.customer_service, R.id.release, R.id.personal_information})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_focus:
                /**
                 * 我的关注
                 */
                IntentUtils.forward(getActivity(),CollectonActivity.class);
                break;
            case R.id.coupon:
                /**
                 * 优惠券
                 */

                break;
            case R.id.wallet:
                /**
                 * 钱包
                 */
                break;
            case R.id.set_up:
                /**
                 * 我的地址
                 */
                IntentUtils.forward(getActivity(),AddressActivity.class);
                break;
            case R.id.customer_service:
                /**
                 * 联系客服
                 */
                intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
                        + Config.PHONE));
                startActivity(intent);
                break;
            case R.id.release:
                /**
                 * 软件版本
                 */
                IntentUtils.forward(getActivity(), VersionInfor.class);
                break;
            case R.id.personal_information:
                /**
                 * 个人信息
                 */
                intent=new Intent(getActivity(),SetUserInforActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
                break;
        }

    }

    public void init() {
        user = (UserInfo) GsonUtil.fromJson(SharedPrefsUtil.getString(getActivity(), "LoginUser", "user"), UserInfo.class);
        App.getImage(getActivity(), personalInformation, user.getUser_icon());
        tvNickname.setText(user.getUser_alias());
        tvSignature.setText(user.getUser_signature());
    }
    public void onEventMainThread(EventBusSelect event) {
        L.e(""+event.getMessage());
        if (event.getActionType().equals(Config.USERINFOR)){
            L.e(""+event.getMessage());
            user= (UserInfo) GsonUtil.fromJson(event.getMessage(),UserInfo.class);
            App.getImage(getActivity(), personalInformation, user.getUser_icon());
            tvNickname.setText(user.getUser_alias());
            tvSignature.setText(user.getUser_signature());
            SharedPrefsUtil.setValue(getActivity(), "LoginUser", "user", GsonUtil.toJson(user));
            SharedPrefsUtil.setValue(getActivity(), "userInfo", "user", GsonUtil.toJson(user));

        }

    }
}
