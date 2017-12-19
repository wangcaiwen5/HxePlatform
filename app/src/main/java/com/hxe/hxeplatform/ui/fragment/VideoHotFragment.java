package com.hxe.hxeplatform.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.onetime.platform.R;
import com.hxe.hxeplatform.adapter.MyHotVideoAdapter;
import com.hxe.hxeplatform.base.BaseFragment;
import com.hxe.hxeplatform.entity.HotVideoEntity;
import com.hxe.hxeplatform.mvp.presenter.HotVideoPresenter;
import com.hxe.hxeplatform.mvp.view.HotVideoView;
import com.hxe.hxeplatform.ui.activity.VideoContentActivity;
import com.hxe.hxeplatform.utils.SpaceItem;
import com.hxe.hxeplatform.utils.ToastShow;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.mingle.widget.LoadingView;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.ResponseBody;

/**
 * Author:wangcaiwen
 * Time:2017/12/7.
 * Description:
 */

public class VideoHotFragment extends BaseFragment<HotVideoPresenter> implements HotVideoView,XRecyclerView.LoadingListener {
    @BindView(R.id.xlist_hotVideo)
    XRecyclerView xlistHotVideo;
    @BindView(R.id.hotVideo_loading)
    LoadingView hotVideoLoading;
    private  int page = 1;
    private MyHotVideoAdapter adapter;
    private List<HotVideoEntity.DataBean> listdata = new ArrayList<>();

    @Override
    protected void init() {
        initView();
        initData();
        initListener();
    }

    

    private void initListener() {

    }

    private void initData() {
        mPresenter.getVideoHotList(""+page);
    }
    @Override
    protected void initView() {
        xlistHotVideo.setLoadingMoreEnabled(true);
        xlistHotVideo.setPullRefreshEnabled(true);
        xlistHotVideo.setLoadingListener(this);
        xlistHotVideo.addItemDecoration(new SpaceItem(8));
        xlistHotVideo.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
    }
    @Override
    protected HotVideoPresenter getPresenter() {
        return new HotVideoPresenter(this);
    }
    @Override
    protected int getLayoutid() {
        return R.layout.fragment_video_hot_layout;
    }
    @Override
    public void onFail(String msg) {}
    @Override
    public void onSuccess(ResponseBody json) {
        try {
            final String s = json.string();
            System.out.println("======="+s);
            Gson gson = new Gson();
            HotVideoEntity hotVideoEntity = gson.fromJson(s, HotVideoEntity.class);
            String code = hotVideoEntity.code;
            String msg = hotVideoEntity.msg;

            if(code.equals("0")){
                final List<HotVideoEntity.DataBean> data = hotVideoEntity.data;
                ToastShow.getSingleton(getContext()).showToast(msg);
                listdata.addAll(data);
                if(adapter==null){
                    adapter = new MyHotVideoAdapter(getActivity(),listdata);
                    xlistHotVideo.setAdapter(adapter);
                }else{
                    adapter.notifyDataSetChanged();
                }
                adapter.setOnItemClickListener(new MyHotVideoAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Bundle bundel = new Bundle();
                        System.out.println("=============="+s);
                        bundel.putString("videos",s);
                        bundel.putInt("position",position-1);
                        gotoActivity(VideoContentActivity.class,false,bundel);
                    }
                });

            }else{
                ToastShow.getSingleton(getContext()).showToast(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void hideProgressBar() {
        hotVideoLoading.setVisibility(View.GONE);
    }

    @Override
    public void ShowProgressBar() {
        hotVideoLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("Throwable"+throwable);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }
}
