package com.hxe.hxeplatform.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hxe.hxeplatform.R;
import com.hxe.hxeplatform.base.BaseFragment;
import com.hxe.hxeplatform.base.BasePresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.mingle.widget.LoadingView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author:wangcaiwen
 * Time:2017/12/7.
 * Description:
 */

public class VideoNearbyFragment extends BaseFragment {
    @BindView(R.id.xlist_fjVideo)
    XRecyclerView xlistFjVideo;
    @BindView(R.id.fjVideo_loading)
    LoadingView fjVideoLoading;


    @Override
    protected void initView() {

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
        return R.layout.fragment_video_nearby_layout;
    }


}
