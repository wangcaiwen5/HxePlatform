package com.hxe.hxeplatform.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.onetime.platform.R;
import com.hxe.hxeplatform.adapter.TopLineFragmentPageAdapter;
import com.hxe.hxeplatform.adapter.VideoFragmentPageAdapter;
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

public class VideoFragment extends BaseFragment {
    @BindView(R.id.video_tablayout)
    TabLayout videoTablayout;
    @BindView(R.id.timeline_viewpager)
    ViewPager videoViewpager;


    @Override
    protected int getLayoutid() {
        return R.layout.fragment_video_layout;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void init() {
        initData();
    }
    private void initData() {
        videoViewpager.setAdapter(new VideoFragmentPageAdapter(getChildFragmentManager(),getActivity()));
        videoTablayout.setupWithViewPager(videoViewpager);
    }


    @Override
    protected BasePresenter getPresenter() {
        return null;
    }


}
