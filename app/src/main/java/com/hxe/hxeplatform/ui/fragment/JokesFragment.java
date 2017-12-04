package com.hxe.hxeplatform.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.hxe.hxeplatform.R;
import com.hxe.hxeplatform.adapter.MyHotAdapter;
import com.hxe.hxeplatform.adapter.MyJokesAdapter;
import com.hxe.hxeplatform.base.BaseFragment;
import com.hxe.hxeplatform.base.BasePresenter;
import com.hxe.hxeplatform.entity.JokesEntity;
import com.hxe.hxeplatform.mvp.presenter.JokesListPresenter;
import com.hxe.hxeplatform.mvp.view.JokesListView;
import com.hxe.hxeplatform.utils.ToastShow;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.ResponseBody;

/**
 * Author:wangcaiwen
 * Time:2017/11/25.
 * Description:
 */

public class JokesFragment extends BaseFragment<JokesListPresenter> implements XRecyclerView.LoadingListener,JokesListView{
    @BindView(R.id.rv_jokesList)
    XRecyclerView recyclerView;
    @BindView(R.id.pb_loading)
    ProgressBar progressBar;
    private MyJokesAdapter adapter;
    private int page=1;
    private List<JokesEntity.DataBean> data;
    private List<JokesEntity.DataBean> moreList= new ArrayList<>();

    @Override
    protected int getLayoutid() {
        return R.layout.fragment_jokes_layout;
    }



    @Override
    protected void init() {
        initView();
        initData();
    }

    @Override
    protected JokesListPresenter getPresenter() {
        return new JokesListPresenter(this);
    }

    private void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setPullRefreshEnabled(true);
        recyclerView.setLoadingMoreEnabled(true);
        recyclerView.setLoadingListener(this);
    }

    private void initData() {
        mPresenter.getJokesList(page+"");
    }

    @Override
    public void onRefresh() {
        moreList.clear();
        data.clear();
        page=1;
        initData();
       recyclerView.refreshComplete();
    }

    @Override
    public void onLoadMore() {
        // TODO: 2017/12/3 加载更多有bug
            page++;
            initData();
            recyclerView.loadMoreComplete();
    }

    @Override
    public void onSuccess(ResponseBody msg) {
        try {
            String string = msg.string();

            System.out.println("段子列表==="+string);
            Gson gson = new Gson();
            //解析
            JokesEntity jokesEntity = gson.fromJson(string, JokesEntity.class);
            String code = jokesEntity.code;
            String msg1 = jokesEntity.msg;
            data = jokesEntity.data;
                /*if(data == null && data.size()<=0){
                    return;
                }*/

                moreList.addAll(data);
                if(adapter==null){
                    adapter = new MyJokesAdapter(getActivity(), moreList);
                    recyclerView.setAdapter(adapter);
                }else{
                   adapter.notifyDataSetChanged();
                }



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFail(String msg) {
    }
    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void ShowProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("=======段子"+throwable);
    }
}
