package com.hxe.hxeplatform.mvp.presenter;

import com.hxe.hxeplatform.base.BasePresenter;
import com.hxe.hxeplatform.entity.JokesEntity;
import com.hxe.hxeplatform.mvp.model.JokesListModel;
import com.hxe.hxeplatform.mvp.view.JokesListView;

import java.util.List;

import okhttp3.ResponseBody;

/**
 * Author:wangcaiwen
 * Time:2017/11/28.
 * Description:
 */

public class JokesListPresenter extends BasePresenter<JokesListView> implements JokesListModel.MjokesList{

    private JokesListView jokesListView;
    private JokesListModel jokesListModel;;

    public JokesListPresenter(JokesListView jokesListView) {
        this.jokesListView = jokesListView;
        jokesListModel = new JokesListModel();
        jokesListModel.setmLogin(this);
    }

    public void getJokesList(String page){
        jokesListView.ShowProgressBar();

        jokesListModel.getJokesList(page);
    }


    @Override
    public void mjokesListSuccess(ResponseBody json) {
        jokesListView.onSuccess(json);
        jokesListView.hideProgressBar();
    }
    @Override
    public void mjokesListError(Throwable e) {
        jokesListView.onError(e);
        jokesListView.hideProgressBar();
    }

}
