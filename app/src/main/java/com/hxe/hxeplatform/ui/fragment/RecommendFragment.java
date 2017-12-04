package com.hxe.hxeplatform.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hxe.hxeplatform.R;
import com.hxe.hxeplatform.adapter.TopLineFragmentPageAdapter;
import com.hxe.hxeplatform.base.BaseFragment;
import com.hxe.hxeplatform.base.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author:wangcaiwen
 * Time:2017/11/25.
 * Description:
 */

public class RecommendFragment extends BaseFragment {
    @BindView(R.id.timeline_tablayout)
    TabLayout timelineTablayout;
    @BindView(R.id.timeline_viewpager)
    ViewPager timelineViewpager;
    Unbinder unbinder;

    @Override
    protected int getLayoutid() {
        return R.layout.fragment_recommend_layout;
    }



    @Override
    protected void init() {
        initView();
    }

    private void initView() {
        timelineViewpager.setAdapter(new TopLineFragmentPageAdapter(getChildFragmentManager(),getActivity()));
        timelineTablayout.setupWithViewPager(timelineViewpager);
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }


}
