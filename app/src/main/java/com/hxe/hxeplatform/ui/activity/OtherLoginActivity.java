package com.hxe.hxeplatform.ui.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hxe.hxeplatform.R;
import com.hxe.hxeplatform.base.BaseActivity;
import com.hxe.hxeplatform.base.BaseApplication;
import com.hxe.hxeplatform.entity.LoginEntity;
import com.hxe.hxeplatform.mvp.presenter.LoginPresenter;
import com.hxe.hxeplatform.mvp.view.LoginView;
import com.hxe.hxeplatform.utils.SharedPreferencesUtils;
import com.hxe.hxeplatform.utils.ToastShow;

import java.io.IOException;

import butterknife.BindView;
import okhttp3.ResponseBody;

public class OtherLoginActivity extends BaseActivity<LoginPresenter> implements View.OnClickListener,LoginView{


    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.tv_login_bt)
    TextView tvLoginBt;
    @BindView(R.id.tv_update_paw)
    TextView tvUpdatePaw;
    @BindView(R.id.tv_youke)
    TextView tvYouke;
    @BindView(R.id.pb_progressbar)
    ProgressBar progressBar;
    LoginPresenter presenter ;


    @Override
    protected LoginPresenter getPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected boolean getIsWindow() {
        return true;
    }

    @Override
    protected void init() {

        initView();
    }

    private void initView() {
    tvLoginBt.setOnClickListener(this);
    tvUpdatePaw.setOnClickListener(this);
    tvYouke.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_other_login;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_login_bt:


                System.out.println("============="+etUsername.getText().toString()+"========"+etPassword.getText().toString().trim());


                mPresenter.login(etUsername.getText().toString().trim(),etPassword.getText().toString().trim());
                break;

            case R.id.tv_update_paw:
                ToastShow.getSingleton(this).showToast("暂未开通此功能");
                break;


            case R.id.tv_youke:
                ToastShow.getSingleton(this).showToast("暂未开通此功能");
                break;
        }
    }


    @Override
    public void onSuccess(ResponseBody body) {
        try {
            String string = body.string();
            Gson gson = new Gson();
            LoginEntity loginEntity = gson.fromJson(string, LoginEntity.class);
            String code = loginEntity.code;
            String msg1 = loginEntity.msg;
            if(code.equals("0")){
                LoginEntity.DataBean data = loginEntity.data;
                String token = data.token;
                int uid = data.uid;
                SharedPreferencesUtils.getInstance(OtherLoginActivity.this).putString("uid",uid+"");
                SharedPreferencesUtils.getInstance(OtherLoginActivity.this).putString("token",token);
                ToastShow.getSingleton(BaseApplication.getContext()).showToast(msg1);
                gotoActivity(MainActivity.class,true);
                SharedPreferencesUtils.getInstance(getApplicationContext()).putBoolean("isLogin",true);
            }else{
                ToastShow.getSingleton(BaseApplication.getContext()).showToast(msg1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onFail(String msg) {
        progressBar.setVisibility(View.INVISIBLE);
        ToastShow.getSingleton(BaseApplication.getContext()).showToast(msg+"");
    }




    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void ShowProgressBar() {
        System.out.println("显示");
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("=======异常"+throwable);
    }
}
