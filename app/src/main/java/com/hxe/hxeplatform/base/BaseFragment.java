package com.hxe.hxeplatform.base;

import android.content.Intent;
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

public abstract class BaseFragment<P extends BasePresenter> extends Fragment  {


    protected P mPresenter;
    protected View rootView;
    Unbinder unbinder;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(rootView==null){
            rootView = View.inflate(getActivity(), getLayoutid(), null);
            unbinder = ButterKnife.bind(this, rootView);
            initView();
        }
        ViewGroup viewGroup = (ViewGroup) rootView.getParent();
        if(viewGroup!=null){
            viewGroup.removeView(rootView);
        }

        return rootView;


    }

    protected abstract void initView();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter =  getPresenter();
        init();
    }

    protected abstract void init();

    protected abstract P getPresenter();

    protected abstract int getLayoutid();
    /**
     * 默认不关闭当前activity
     * @param clz
     */
    public void gotoActivity(Class<?> clz) {
        gotoActivity(clz, false, null);
    }

    /**
     * 设置是否关闭当前activity
     * @param clz
     * @param isCloseCurrentActivity
     */
    public void gotoActivity(Class<?> clz, boolean isCloseCurrentActivity) {
        gotoActivity(clz, isCloseCurrentActivity, null);
    }

    /**
     * 设置是否关闭当前activity传值
     * @param clz
     * @param isCloseCurrentActivity
     * @param bundle
     */
    public void gotoActivity(Class<?> clz, boolean isCloseCurrentActivity, Bundle bundle) {
        Intent intent = new Intent(getActivity(), clz);
        if (bundle != null)
            intent.putExtras(bundle);
        startActivity(intent);
        if (isCloseCurrentActivity) {
            getActivity().finish();
        }


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
