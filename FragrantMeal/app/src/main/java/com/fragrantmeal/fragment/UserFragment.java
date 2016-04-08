package com.fragrantmeal.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fragrantmeal.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by CaoBin on 2016/3/20.
 */
public class UserFragment extends Fragment {

    @InjectView(R.id.tv_nickname)
    TextView tvNickname;
    @InjectView(R.id.tv_signature)
    TextView tvSignature;
    @InjectView(R.id.tv_attention)
    TextView tvAttention;
    @InjectView(R.id.tv_followed)
    TextView tvFollowed;
    @InjectView(R.id.personal_information)
    CircleImageView personalInformation;
    private View view;

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
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.my_focus, R.id.coupon, R.id.wallet, R.id.set_up, R.id.customer_service, R.id.release, R.id.personal_information})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_focus:
                /**
                 * 我的关注
                 */

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
                 * 设置
                 */

                break;
            case R.id.customer_service:
                /**
                 * 联系客服
                 */

                break;
            case R.id.release:
                /**
                 * 发布
                 */

                break;
            case R.id.personal_information:
                /**
                 * 个人信息
                 */

                break;

        }

    }
}
