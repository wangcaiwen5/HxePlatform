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

public class JokesFragment extends BaseFragment implements XRecyclerView.LoadingListener{
    @BindView(R.id.rv_jokesList)
    XRecyclerView recyclerView;
    @BindView(R.id.pb_loading)
    ProgressBar progressBar;
    List<JokesEntity.DataBean> list = new ArrayList<>();
    private MyJokesAdapter adapter;
    private int page=1;

    @Override
    protected int getLayoutid() {
        return R.layout.fragment_jokes_layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
       initData();

    }

    private void initView() {
        recyclerView.setLoadingListener(this);
        recyclerView.setPullRefreshEnabled(true);
    recyclerView.setLoadingMoreEnabled(true);


    }

    private void initData() {
        JokesListPresenter presenter = new JokesListPresenter(new JokesListView() {
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
                    if(code.equals("0")){
                        List<JokesEntity.DataBean> data = jokesEntity.data;
                        if(data == null && data.size()<=0){
                            System.out.println("刷新===");
                            return;
                        }
                        list.addAll(data);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                        if(adapter==null){
                            adapter = new MyJokesAdapter(getActivity(),list);
                            recyclerView.setAdapter(adapter);
                        }else{
                            System.out.println("刷新更多===");
                            adapter.notifyDataSetChanged();
                        }

                    }else{
                        ToastShow.getSingleton(getActivity()).showToast(msg1);
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


        });
        presenter.getJokesList(page+"");
    }

    @Override
    public void onRefresh() {
        list.clear();
        if(adapter!=null){
            adapter=null;
        }
        page++;
        initData();
       recyclerView.refreshComplete();
    }

    @Override
    public void onLoadMore() {
            page++;
            initData();
            recyclerView.loadMoreComplete();
    }
}
