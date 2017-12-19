package com.hxe.hxeplatform.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hxe.hxeplatform.base.BaseFragment;
import com.hxe.hxeplatform.base.BasePresenter;
import com.onetime.platform.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author:wangcaiwen
 * Time:2017/12/19.
 * Description:
 */

public class FindFragment extends BaseFragment implements View.OnClickListener{
    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.tv_new_user)
    TextView tvNewUser;

    @Override
    protected void initView() {
        tvNewUser.setOnClickListener(this);
    }

    @Override
    protected void init() {

    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayoutid() {
        return R.layout.fragment_find_layout;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_new_user:

                break;
        }
    }
}
