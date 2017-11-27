package com.hxe.hxeplatform.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hxe.hxeplatform.R;
import com.hxe.hxeplatform.base.BaseActivity;
import com.hxe.hxeplatform.utils.ToastShow;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OtherLoginActivity extends BaseActivity implements View.OnClickListener{


    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.tv_login_bt)
    TextView tvLoginBt;
    @BindView(R.id.tv_update_paw)
    TextView tvUpdatePaw;
    @BindView(R.id.tv_youke)
    TextView tvYouke;


    @Override
    protected boolean getIsWindow() {
        return true;
    }

    @Override
    protected void init() {

        initView();
    }

    private void initView() {
    tvLoginBt.setOnClickListener(this);
    tvUpdatePaw.setOnClickListener(this);
    tvYouke.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_other_login;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_login_bt:
                gotoActivity(MainActivity.class,true);
                break;

            case R.id.tv_update_paw:
                ToastShow.getSingleton(this).showToast("暂未开通此功能");
                break;


            case R.id.tv_youke:
                ToastShow.getSingleton(this).showToast("暂未开通此功能");
                break;
        }
    }
}
