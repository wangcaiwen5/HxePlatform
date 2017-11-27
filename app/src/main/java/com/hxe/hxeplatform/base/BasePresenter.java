package com.hxe.hxeplatform.base;

/**
 * Author:wangcaiwen
 * Time:2017/11/14.
 * Description:
 */

public class BasePresenter<V extends BaseView> implements Presenter<V>{

    protected V mView;


    @Override
    public void attacheView(V mvpView) {
        this.mView=mvpView;
    }

    @Override
    public void detachView() {
        this.mView=null;
    }

    public boolean isViewBind(){
        return mView!=null;
    }

    public V getView(){
        return mView;
    }
}
