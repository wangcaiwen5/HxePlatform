package com.hxe.hxeplatform.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.hxe.hxeplatform.ui.activity.MainActivity;
import com.hxe.hxeplatform.utils.ShowToast;
import com.onetime.platform.R;
import com.hxe.hxeplatform.adapter.MyHotAdapter;
import com.hxe.hxeplatform.base.BaseFragment;
import com.hxe.hxeplatform.base.BasePresenter;
import com.hxe.hxeplatform.entity.GetVediosListEntity;
import com.hxe.hxeplatform.mvp.presenter.GetVideosListPresenter;
import com.hxe.hxeplatform.mvp.view.GetVideosListView;
import com.hxe.hxeplatform.ui.activity.LoginActivity;
import com.hxe.hxeplatform.ui.activity.VideoContentActivity;
import com.hxe.hxeplatform.utils.NetUtils;
import com.hxe.hxeplatform.utils.SharedPreferencesUtils;
import com.hxe.hxeplatform.utils.ToastShow;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.mingle.widget.LoadingView;
import com.stx.xhb.xbanner.XBanner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import hxe.com.dialogutils.BottomDialog;
import hxe.com.dialogutils.UpDialog;
import okhttp3.ResponseBody;

/**
 * Author:wangcaiwen
 * Time:2017/11/27.
 * Description:
 */

public class HotFragment extends BaseFragment<GetVideosListPresenter> implements GetVideosListView,XRecyclerView.LoadingListener{
    @BindView(R.id.rv_list)
    XRecyclerView recyclerView;
    @BindView(R.id.pb_hotProgress)
    LoadingView hotProgress;
    private XBanner xbanner;
    private  int page=1;
    private MyHotAdapter adapter;
    private List<GetVediosListEntity.DataBean> data;
    private List<GetVediosListEntity.DataBean> newdata = new ArrayList<>();
    private LinearLayoutManager layoutManager;

    private EditText mEditText;
    private TextView mButton;

    @Override
    protected int getLayoutid() {
        return R.layout.fragment_hot_layout;
    }

    @Override
    protected void initView() {
        recyclerView.setPullRefreshEnabled(true);
        recyclerView.setLoadingMoreEnabled(true);
        recyclerView.setLoadingListener(this);
    }

    @Override
    protected void init() {
        initView();
        initList();
        initData();

    }
    private void initData() {
        String uid = SharedPreferencesUtils.getInstance(getActivity()).getString("uid");
        mPresenter.getVideosList("0",uid,page+"");
    }

    @Override
    protected GetVideosListPresenter getPresenter() {
        return new GetVideosListPresenter(this);
    }
    private void initList() {
        View view = View.inflate(getActivity(), R.layout.xbanner_layout, null);
        xbanner = view.findViewById(R.id.xb_banner);
        initBannerData();
        recyclerView.addHeaderView(view);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

    }



    private void initBannerData() {
    final List<String> list = new ArrayList<>();
    list.add("https://v2.modao.cc/uploads3/images/1108/11085724/raw_1500258840.png");
    list.add("https://v2.modao.cc/uploads3/images/1108/11085770/raw_1500258881.png");
    list.add("https://v2.modao.cc/uploads3/images/1108/11085781/raw_1500258901.png");
    list.add("https://v2.modao.cc/uploads3/images/1108/11085861/raw_1500259026.png");

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
    @Override
    public void onFail(String msg) {
    }

    @Override
    public void onSuccess(ResponseBody json) {
        Gson gson = new Gson();
        try {
            final String string = json.string();
            System.out.println("获取视频list==="+string);
            GetVediosListEntity getVediosListEntity = gson.fromJson(string, GetVediosListEntity.class);
            String code = getVediosListEntity.code;
            String msg = getVediosListEntity.msg;
            if(code.equals("0")){
                ToastShow.getSingleton(getActivity()).showToast(msg);
                data = getVediosListEntity.data;
                newdata.addAll(data);
                if(data.size()>0)
                    if(adapter==null){
                        adapter = new MyHotAdapter(getActivity(), newdata);
                        recyclerView.setAdapter(adapter);
                    }else{
                        adapter.notifyDataSetChanged();
                    }


            }else if(code.equals("2")){
                ToastShow.getSingleton(getActivity()).showToast(msg+",请重新登录");
                gotoActivity(LoginActivity.class,true);
            }else{
                ToastShow.getSingleton(getActivity()).showToast(msg);
            }

            if(adapter!=null){
                /**
                 * 视频推荐列表的点击事件
                 */
                adapter.setOnItemClickListener(new MyHotAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Bundle bundel = new Bundle();
                        System.out.println("=============="+string);
                        bundel.putString("videos",string);
                        bundel.putInt("position",position-2);
                        gotoActivity(VideoContentActivity.class,false,bundel);
                    }
                });

                adapter.setContentClick(new MyHotAdapter.OnContentClickListener() {
                    @Override
                    public void onContentClick(View view, int position) {
                        showCommentDialog();
                    }
                });

            }
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    /**
     * 弹出评论dialog
     */
    private void showCommentDialog() {
        BottomDialog.create(getFragmentManager())
                .setLayoutRes(R.layout.dialog_comment_view)
                .setViewListener(new BottomDialog.ViewListener() {
                    @Override
                    public void bindView(View v) {
                        initView(v);
                    }
                })
                .setDimAmount(0.6f)
                .setCancelOutside(false)
                .setTag("comment")
                .show();
    }

    private void initView(View v) {
        mEditText =  v.findViewById(R.id.edit_text);
        mEditText.setHintTextColor(getActivity().getResources().getColor(R.color.textColor_999));
        mButton =  v.findViewById(R.id.comment_btn);
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s.toString())) {
                    mButton.setBackgroundResource(R.drawable.dialog_send_btn);
                    mButton.setEnabled(false);
                } else {
                    mButton.setBackgroundResource(R.drawable.dialog_send_btn_pressed);
                    mButton.setEnabled(true);
                }
            }
        });
        mEditText.post(new Runnable() {
            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(mEditText, 0);
            }
        });
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowToast.showToast("评论:"+mEditText.getText().toString());

            }
        });

    }
    @Override
    public void hideProgressBar() {
        hotProgress.setVisibility(View.GONE);
    }

    @Override
    public void ShowProgressBar() {
        hotProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("获取热门异常=="+throwable);
    }


    //刷新
    @Override
    public void onRefresh() {
        if(!NetUtils.isInternetConnection(getActivity())){
            ToastShow.getSingleton(getActivity()).showToast("网络异常,请检查设置!");
            recyclerView.refreshComplete();
        }else{
            newdata.clear();
            page=1;
            initData();
            data.clear();
            adapter.notifyDataSetChanged();
            recyclerView.refreshComplete();
        }

    }
    //加载更多
    @Override
    public void onLoadMore() {
        if(!NetUtils.isInternetConnection(getActivity())){
            ToastShow.getSingleton(getActivity()).showToast("网络异常,请检查设置!");
            recyclerView.loadMoreComplete();
        }else{
            page++;
            initData();
            recyclerView.loadMoreComplete();
        }
    }
}
