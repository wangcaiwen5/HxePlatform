package com.hxe.hxeplatform.ui.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.allenliu.versionchecklib.core.AllenChecker;
import com.allenliu.versionchecklib.core.VersionParams;
import com.bwie.uploadpicture.clearcache.ClearCacheUtils;
import com.google.gson.Gson;
import com.hxe.hxeplatform.R;
import com.hxe.hxeplatform.base.BaseActivity;
import com.hxe.hxeplatform.base.BaseApplication;
import com.hxe.hxeplatform.entity.UpdateEntity;
import com.hxe.hxeplatform.mvp.presenter.UpdatePresenter;
import com.hxe.hxeplatform.mvp.view.UpdateView;
import com.hxe.hxeplatform.myview.MyToolBar;
import com.hxe.hxeplatform.rxretrofit.common.Api;
import com.hxe.hxeplatform.rxretrofit.common.BaseApi;
import com.hxe.hxeplatform.rxretrofit.http.HttpCallBack;

import com.hxe.hxeplatform.rxretrofit.http.RetrofitManager;
import com.hxe.hxeplatform.rxretrofit.http.VersionUpdateService;
import com.hxe.hxeplatform.utils.AppUtils;
import com.hxe.hxeplatform.utils.FileUtils;
import com.hxe.hxeplatform.utils.SharedPreferencesUtils;
import com.hxe.hxeplatform.utils.ToastShow;
import com.mylhyl.circledialog.CircleDialog;
import com.mylhyl.circledialog.callback.ConfigProgress;
import com.mylhyl.circledialog.params.ProgressParams;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import okhttp3.ResponseBody;

public class SettingActivity extends BaseActivity<UpdatePresenter> implements View.OnClickListener,UpdateView{

    public static SettingActivity activity;
    @BindView(R.id.mSettingToolbar)
    MyToolBar mSettingToolbar;
    @BindView(R.id.rl_query_update)
    RelativeLayout mQueryUpdate;
    @BindView(R.id.rl_clear_cache)
    RelativeLayout mClearCache;
    @BindView(R.id.bt_current_version)
    Button mCurrentVersion;
    @BindView(R.id.bt_current_cache)
    Button mCurrentCache;
    @BindView(R.id.pb_query_update)
    ProgressBar progressBar;
    private ClearCacheUtils clearCacheUtils;

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            /**
             *  b.putLong("current",current);
             b.putLong("total",total);
             */
            Bundle data = msg.getData();
            final long current = data.getLong("current");
            final long total = data.getLong("total");
            System.out.println("总进度=="+total+"当前进度=="+current);
            CircleDialog.Builder builder = new CircleDialog.Builder(SettingActivity.this);
            builder.configProgress(new ConfigProgress() {
                @Override
                public void onConfig(ProgressParams params) {
                    params.style=ProgressParams.STYLE_HORIZONTAL;
                    params.text="已经下载"+(current/total)*100+"%";
                    params.max= (int) total;
                    params.progress= (int) current;

                }
            }).setProgressText("下载中").setTextColor(Color.BLUE).setNegative("取消", new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            }).show();

            if(current==total){
                builder.show().dismiss();
            }

        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        String versionName = AppUtils.getVersionName(this);
        mCurrentVersion.setText(versionName);
        //使用方法
        clearCacheUtils = new ClearCacheUtils();
 try {
     //获取缓存大小
 String totalCacheSize = clearCacheUtils.getTotalCacheSize(this);
 mCurrentCache.setText(totalCacheSize);
 } catch (Exception e) {
 e.printStackTrace();
 }
    }

    @Override
    protected UpdatePresenter getPresenter() {
        return new UpdatePresenter(this);
    }

    @Override
    protected boolean getIsWindow() {
        return false;
    }

    @Override
    protected void init() {

        initView();
        initToolBar();
    }

    private void initView() {
        mQueryUpdate.setOnClickListener(this);
        mClearCache.setOnClickListener(this);
    }

    private void initToolBar() {
    mSettingToolbar.setLeftTitleText("返回");
    mSettingToolbar.setLeftTitleClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    });

    mSettingToolbar.setMainTitle("设置");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_clear_cache:

                final AlertDialog.Builder normalDialog =
                        new AlertDialog.Builder(this);
                normalDialog.setIcon(R.mipmap.clear_cache);
                normalDialog.setTitle("清除缓存");
                normalDialog.setMessage("你确定清除缓存吗?");
                normalDialog.setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //清除缓存功能
                                clearCacheUtils.clearAllCache(SettingActivity.this);
                                mCurrentCache.setText("0");
                            }
                        });
                normalDialog.setNegativeButton("不了",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                // 显示
                normalDialog.show();


                break;

            case R.id.rl_query_update:
               //mPresenter.updateVersion();
                ToastShow.getSingleton(this).showToast("检查更新");
                VersionParams.Builder builder = new VersionParams.Builder()
                        .setRequestUrl(BaseApi.BASE_URL+Api.UPDATE_VERSION_UPDATE)
                        .setCustomDownloadActivityClass(CustomDialogActivity.class)
                        .setService(VersionUpdateService.class);
                AllenChecker.startVersionCheck(this, builder.build());
                AllenChecker.init(true);
                activity=this;
                break;

            case R.id.bt_exit_login:
                SharedPreferencesUtils.getInstance(getApplicationContext()).putBoolean("isLogin",false);
                gotoActivity(LoginActivity.class,true);
                break;
        }
    }

    @Override
    public void onFail(String msg) {

    }

    @Override
    public void onSuccess(ResponseBody body) {
        //获取当前app版本号
        String appVersionCode = AppUtils.getAppVersionCode(BaseApplication.getContext());
        int currentAppVersionCode = Integer.parseInt(appVersionCode);
        try {
            String string = body.string();
            Gson gson = new Gson();
            UpdateEntity updateEntity = gson.fromJson(string, UpdateEntity.class);
            String code = updateEntity.code;
            if(code.equals("0")){
                UpdateEntity.DataBean data = updateEntity.data;
                String apkUrl = data.apkUrl;//下载链接
                String versionCode = data.versionCode;//101
                int serverVersionCode = Integer.parseInt(versionCode);
                String versionName = data.versionName;//1.0.1
                if(currentAppVersionCode< serverVersionCode){
                    final AlertDialog.Builder normalDialog =
                            new AlertDialog.Builder(this);
                    normalDialog.setIcon(R.mipmap.clear_cache);
                    normalDialog.setTitle("是否升级到"+versionName+"版本?");
                    normalDialog.setMessage("修复了未知bug!");
                    normalDialog.setPositiveButton("升级",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                        RetrofitManager.getInstance(SettingActivity.this).createBaseApi().downloadFile("version/101.apk", new RetrofitManager.MyShowCallBack() {
                                            @Override
                                            public void onError(Throwable e) {

                                            }

                                            @Override
                                            public void onSuccess(final ResponseBody value) {
                                             new Thread(new Runnable() {
                                                 @Override
                                                 public void run() {
                                                     File file = FileUtils.createFile(SettingActivity.this);
                                                     FileUtils.writeFile2Disk(value, file, new HttpCallBack() {
                                                         /**
                                                          *
                                                          * @param current 当前进度
                                                          * @param total 总长度
                                                          */
                                                         @Override
                                                         public void onLoading(long current, long total) {
                                                             Message obtain = Message.obtain();
                                                             Bundle b = new Bundle();
                                                             b.putLong("current",current);
                                                             b.putLong("total",total);
                                                             obtain.setData(b);
                                                             mHandler.sendMessage(obtain);
                                                         }
                                                     });
                                                 }
                                             }).start();
                                            }
                                        });
                                }
                            });
                    normalDialog.setNegativeButton("取消",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                    // 显示
                    normalDialog.show();


                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void ShowProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("版本更新"+throwable);
    }


}
