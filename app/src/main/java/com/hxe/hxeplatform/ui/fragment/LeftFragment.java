package com.hxe.hxeplatform.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.hxe.hxeplatform.R;
import com.hxe.hxeplatform.adapter.MyLeftAdapter;
import com.hxe.hxeplatform.base.BaseFragment;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Author:wangcaiwen
 * Time:2017/11/25.
 * Description:
 */

public class LeftFragment extends BaseFragment {
    @BindView(R.id.tv_nikename)
    TextView tvNikename;
    @BindView(R.id.iv_gender)
    ImageView ivGender;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    Unbinder unbinder;

    @Override
    protected int getLayoutid() {
        return R.layout.fragment_left_layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvList.setAdapter(new MyLeftAdapter(getActivity()));

    }




}
