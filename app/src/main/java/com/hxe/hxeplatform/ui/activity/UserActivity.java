package com.hxe.hxeplatform.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bwie.uploadpicture.view.CircleImageView;
import com.google.gson.Gson;
import com.onetime.platform.R;
import com.hxe.hxeplatform.base.BaseActivity;
import com.hxe.hxeplatform.base.BasePresenter;
import com.hxe.hxeplatform.entity.UserInfoEntity;
import com.hxe.hxeplatform.myview.MyToolBar;
import com.hxe.hxeplatform.rxretrofit.common.Api;
import com.hxe.hxeplatform.rxretrofit.http.RetrofitManager;
import com.hxe.hxeplatform.utils.SharedPreferencesUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;

public class UserActivity extends BaseActivity {


    @BindView(R.id.mToolbar)
    MyToolBar mToolbar;
    @BindView(R.id.clv_MyHeadImg)
    CircleImageView clvMyHeadImg;
    @BindView(R.id.tv_user_nikename)
    TextView tvUserNikename;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_mymessage)
    TextView tvMymessage;
    @BindView(R.id.bt_current_version)
    Button btCurrentVersion;
    @BindView(R.id.rl_query_update)
    RelativeLayout rlQueryUpdate;
    @BindView(R.id.bt_current_cache)
    Button btCurrentCache;
    @BindView(R.id.rl_clear_cache)
    RelativeLayout rlClearCache;
    @BindView(R.id.bt_exit_login)
    Button btExitLogin;
    @BindView(R.id.pb_query_update)
    ProgressBar pbQueryUpdate;

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
        btExitLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferencesUtils.getInstance(getApplicationContext()).putBoolean("isLogin",false);
                gotoActivity(LoginActivity.class,true);
            }
        });
        mToolbar.setLeftTitleText("返回");
        mToolbar.setMainTitle("个人中心");
        mToolbar.setLeftTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        Map<String, String> body = new HashMap<>();
        body.put("uid",169+"");
        RetrofitManager.getInstance(this).login(Api.GET_USER_INFO, body, new RetrofitManager.MyShowCallBack() {
            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onSuccess(ResponseBody value) {
                try {
                    String string = value.string();
                    System.out.println("用户信息"+string);
                    Gson gson = new Gson();
                    UserInfoEntity userInfoEntity = gson.fromJson(string, UserInfoEntity.class);
                    String code = userInfoEntity.code;
                    if(code.equals("0")){
                        UserInfoEntity.DataBean data = userInfoEntity.data;
                        tvUserNikename.setText(data.username);
                        RequestOptions option = new RequestOptions().placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher);
                        Glide.with(UserActivity.this).load(data.icon).apply(option).into(clvMyHeadImg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user;
    }


}
