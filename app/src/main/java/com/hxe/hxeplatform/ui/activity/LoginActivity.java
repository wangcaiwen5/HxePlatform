package com.hxe.hxeplatform.ui.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hxe.hxeplatform.R;
import com.hxe.hxeplatform.base.BaseActivity;
import com.hxe.hxeplatform.utils.ToastShow;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.bt_wx_login)
    TextView btWxLogin;
    @BindView(R.id.bt_qq_login)
    TextView btQqLogin;
    @BindView(R.id.tv_other_login)
    TextView tvOtherLogin;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_wx_login:
                ToastShow.getSingleton(this).showToast("打开微信");
                break;

            case R.id.bt_qq_login:
                ToastShow.getSingleton(this).showToast("QQ登录");
                gotoActivity(MainActivity.class,true);
                break;

            case R.id.tv_other_login:
                gotoActivity(OtherLoginActivity.class);
                break;
        }
    }


    @Override
    protected boolean getIsWindow() {
        return true;
    }

    @Override
    protected void init() {

        initView();
    }

    private void initView() {
        btQqLogin.setOnClickListener(this);
        btWxLogin.setOnClickListener(this);
        tvOtherLogin.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }



}

