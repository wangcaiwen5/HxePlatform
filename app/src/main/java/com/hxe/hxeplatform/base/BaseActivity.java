package com.hxe.hxeplatform.base;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.hxe.hxeplatform.R;
import com.hxe.hxeplatform.service.IntentService;
import com.hxe.hxeplatform.service.PushService;
import com.hxe.hxeplatform.utils.ToastShow;
import com.igexin.sdk.PushManager;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;


/**
 * Author:wangcaiwen
 * Time:2017/11/14.
 * Description:
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {

    protected P mPresenter  ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(getLayoutId());
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setIsWindow(getIsWindow());
      mPresenter =  getPresenter();



        // com.getui.demo.DemoPushService 为第三方自定义推送服务
        PushManager.getInstance().initialize(this.getApplicationContext(), PushService.class);
        // com.getui.demo.DemoIntentService 为第三方自定义的推送服务事件接收类
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), IntentService.class);
        init();



    }

    protected abstract P getPresenter();

    private void setIsWindow(boolean isWindow) {
        System.out.println("isWindow====="+isWindow);
        if(isWindow){
            if (Build.VERSION.SDK_INT >= 19) {
                View decorView = getWindow().getDecorView();
                decorView.setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
        }else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
        }
    }

    protected abstract boolean getIsWindow();




    /* @Override
     public void onWindowFocusChanged(boolean hasFocus) {
         super.onWindowFocusChanged(hasFocus);

     }*/
    protected abstract void init();


    protected abstract int getLayoutId();
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
        Intent intent = new Intent(this, clz);
        if (bundle != null)
            intent.putExtras(bundle);
        startActivity(intent);
        if (isCloseCurrentActivity) {
            finish();
        }


    }




    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter != null && mPresenter.isViewBind()){
            mPresenter.detachView();
            mPresenter=null;
        }
    }




}
