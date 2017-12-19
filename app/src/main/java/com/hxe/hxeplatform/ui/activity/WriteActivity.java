package com.hxe.hxeplatform.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.onetime.platform.R;
import com.hxe.hxeplatform.base.BaseActivity;
import com.hxe.hxeplatform.base.BasePresenter;
import com.hxe.hxeplatform.myview.MyToolBar;
import com.hxe.hxeplatform.utils.ToastShow;

import butterknife.BindView;

public class WriteActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.mwrite_toolbar)
    MyToolBar mToolbar;
    @BindView(R.id.bt_mycreate_video)
    RadioButton mVideo;
    @BindView(R.id.bt_mycreate_jokes)
    RadioButton mWrite;

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

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
            case R.id.bt_mycreate_video:
                gotoActivity(UpLoadVideosActivity.class);
                break;

            case R.id.bt_mycreate_jokes:
                gotoActivity(PublishActivity.class);
                break;
        }
    }
}
