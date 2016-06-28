package com.fragrantmeal.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fragrantmeal.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by CaoBin on 2016/4/20.
 */
public class VersionInfor extends Activity {

    @InjectView(R.id.btn_back)
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_version);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.btn_back})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
