package com.hxe.hxeplatform.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.hxe.hxeplatform.R;
import com.hxe.hxeplatform.base.BaseActivity;
import com.hxe.hxeplatform.myview.MyToolBar;
import com.hxe.hxeplatform.utils.ToastShow;

import butterknife.BindView;

public class WriteActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.mwrite_toolbar)
    MyToolBar mToolbar;
    @BindView(R.id.ll_video)
    LinearLayout mVideo;
    @BindView(R.id.ll_write)
    LinearLayout mWrite;

    @Override
    protected boolean getIsWindow() {
        return false;
    }

    @Override
    protected void init() {
        initView();
    }

    private void initView() {
        mVideo.setOnClickListener(this);
        mWrite.setOnClickListener(this);
    mToolbar.setLeftTitleText("取消");
    mToolbar.setMainTitle("创作");
    mToolbar.setLeftTitleClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_write;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_video:
                ToastShow.getSingleton(getApplicationContext()).showToast("暂未开通此功能");
                break;

            case R.id.ll_write:
                gotoActivity(PublishActivity.class);
                break;
        }
    }
}
