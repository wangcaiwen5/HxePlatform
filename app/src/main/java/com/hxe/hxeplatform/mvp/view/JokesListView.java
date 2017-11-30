package com.hxe.hxeplatform.mvp.view;

import com.hxe.hxeplatform.base.BaseView;
import com.hxe.hxeplatform.entity.JokesEntity;

import java.util.List;

import okhttp3.ResponseBody;

/**
 * Author:wangcaiwen
 * Time:2017/11/28.
 * Description:
 */

public interface JokesListView extends BaseView {
    @Override
    void onFail(String msg);

    @Override
    void onSuccess(ResponseBody json);

    @Override
    void hideProgressBar();

    @Override
    void ShowProgressBar();

    @Override
    void onError(Throwable throwable);
}
