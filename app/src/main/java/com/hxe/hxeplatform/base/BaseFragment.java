package com.hxe.hxeplatform.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.leakcanary.RefWatcher;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author:wangcaiwen
 * Time:2017/11/14.
 * Description:
 */

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView {


    protected P mPresenter;
    protected View rootView;
    Unbinder unbinder;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(rootView==null){
            rootView = View.inflate(getActivity(), getLayoutid(), null);

        }
        ViewGroup viewGroup = (ViewGroup) rootView.getParent();
        if(viewGroup!=null){
            viewGroup.removeView(rootView);
        }
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;


    }

    protected abstract int getLayoutid();

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFail() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        //mvp的优化
        if (mPresenter != null && mPresenter.isViewBind())
            mPresenter.detachView();
        /**
         * 内存泄露检测
         */
        RefWatcher refWatcher = BaseApplication.getRefWatcher(getActivity());
        refWatcher.watch(this);
    }
}
