package com.hxe.hxeplatform.base;

import android.app.Application;
import android.content.Context;

import com.mob.MobApplication;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.tencent.bugly.crashreport.CrashReport;


/**
 * Author:wangcaiwen
 * Time:2017/11/14.
 * Description:app
 */

public class BaseApplication extends MobApplication {

    private static BaseApplication ourInstance = new BaseApplication();
    private static Context mContext;
    //Application为整个应用保存全局的RefWatcher
    private RefWatcher refWatcher;

    public static BaseApplication getInstance() {
        return ourInstance;
    }

    public static Context getContext() {
        return mContext;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        ourInstance = this;
        mContext = getApplicationContext();

        initBugley();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        refWatcher = LeakCanary.install(this);

    }

    private void initBugley() {
        CrashReport.initCrashReport(mContext, "dbe8539f4c", true);
    }

    public static RefWatcher getRefWatcher(Context context) {
        BaseApplication application = (BaseApplication) context.getApplicationContext();
        return application.refWatcher;
    }


}
