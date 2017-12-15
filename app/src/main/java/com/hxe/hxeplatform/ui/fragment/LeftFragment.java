package com.hxe.hxeplatform.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bwie.uploadpicture.view.CircleImageView;
import com.hxe.hxeplatform.R;
import com.hxe.hxeplatform.adapter.MyLeftAdapter;
import com.hxe.hxeplatform.base.BaseFragment;
import com.hxe.hxeplatform.base.BasePresenter;
import com.hxe.hxeplatform.ui.activity.SettingActivity;
import com.hxe.hxeplatform.ui.activity.UserActivity;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Author:wangcaiwen
 * Time:2017/11/25.
 * Description:
 */

public class LeftFragment extends BaseFragment implements View.OnClickListener{
    @BindView(R.id.tv_nikename)
    TextView tvNikename;
    @BindView(R.id.iv_gender)
    ImageView ivGender;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.civ_imgView)
    CircleImageView mCimageView;

    Unbinder unbinder;


    @Override
    protected int getLayoutid() {
        return R.layout.fragment_left_layout;
    }



    @Override
    protected void init() {
        initView();
    }

    @Override
    protected void initView() {
        rvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvList.setAdapter(new MyLeftAdapter(getActivity()));
        mCimageView.setOnClickListener(this);
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
           /* case R.id.civ_imgView:
                gotoActivity(UserActivity.class);
                break;

            case R.id.bt_setting:
                gotoActivity(SettingActivity.class);
                break;*/
        }
    }
}
