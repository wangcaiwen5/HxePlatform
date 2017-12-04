package com.hxe.hxeplatform.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hxe.hxeplatform.R;
import com.hxe.hxeplatform.adapter.MyHotAdapter;
import com.hxe.hxeplatform.base.BaseFragment;
import com.hxe.hxeplatform.base.BasePresenter;
import com.hxe.hxeplatform.utils.ToastShow;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Author:wangcaiwen
 * Time:2017/11/27.
 * Description:
 */

public class AttentionFragment extends BaseFragment {

    @BindView(R.id.rv_recyclerview)
    XRecyclerView recyclerView;
    private XBanner xbanner;

    @Override
    protected int getLayoutid() {
        return R.layout.fragment_attention_layout;
    }



    @Override
    protected void init() {
        initList();
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    private void initList() {
        View view = View.inflate(getActivity(), R.layout.xbanner_layout, null);
        xbanner = view.findViewById(R.id.xb_banner);
        initBannerData();
        recyclerView.addHeaderView(view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //recyclerView.setAdapter(new MyHotAdapter(getActivity()));

    }


    private void initBannerData() {
        final List<String> list = new ArrayList<>();
        list.add("https://v2.modao.cc/uploads3/images/1103/11038871/raw_1500002643.png");
        list.add("https://v2.modao.cc/uploads3/images/1108/11085770/raw_1500258881.png");

        xbanner.setData(list,null);
        xbanner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(getActivity()).load(list.get(position)).into((ImageView) view);
            }
        });

        xbanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, int position) {
                ToastShow.getSingleton(getActivity()).showToast("点击广告"+position);
            }
        });




    }
}
