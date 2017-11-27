package com.hxe.hxeplatform.base;

/**
 * Author:wangcaiwen
 * Time:2017/11/14.
 * Description:
 */

public interface Presenter<V> {

    //绑定
    void attacheView(V mvpView);
    //分离
    void detachView();

}
