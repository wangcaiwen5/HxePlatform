package com.hxe.hxeplatform.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hxe.hxeplatform.base.BaseActivity;
import com.hxe.hxeplatform.base.BasePresenter;
import com.onetime.platform.R;

public class RegisterActivity extends BaseActivity {


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
        return R.layout.activity_register;
    }
}
