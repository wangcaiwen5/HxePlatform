package com.hxe.hxeplatform.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.onetime.platform.R;
import com.hxe.hxeplatform.base.BaseActivity;
import com.hxe.hxeplatform.base.BasePresenter;

public class UserCenterActivity extends BaseActivity {


    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected boolean getIsWindow() {
        return false;
    }

    @Override
    protected void init() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_center;
    }
}
