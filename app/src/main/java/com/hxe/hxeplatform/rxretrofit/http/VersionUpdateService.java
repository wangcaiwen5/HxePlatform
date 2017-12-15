package com.hxe.hxeplatform.rxretrofit.http;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import com.allenliu.versionchecklib.core.AVersionService;
import com.google.gson.Gson;
import com.hxe.hxeplatform.base.BaseApplication;
import com.hxe.hxeplatform.entity.UpdateEntity;
import com.hxe.hxeplatform.utils.AppUtils;

import org.json.JSONObject;

/**
 * Author:wangcaiwen
 * Time:2017/12/14.
 * Description:
 */

public class VersionUpdateService extends AVersionService {

    public VersionUpdateService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onResponses(AVersionService service, String response) {
        System.out.println("版本======"+response);
        Gson gson = new Gson();
        UpdateEntity updateEntity = gson.fromJson(response, UpdateEntity.class);
        String code = updateEntity.code;
        if(code.equals("0")){
            UpdateEntity.DataBean data = updateEntity.data;
            String versionCode = data.versionCode;
            String appVersionCode = AppUtils.getAppVersionCode(BaseApplication.getContext());
            String versionName = data.versionName;
            int currentVersionCode = Integer.parseInt(appVersionCode);//当前
            int serverVersionCode = Integer.parseInt(versionCode);//服务端
            String apkUrl = data.apkUrl;
            if(currentVersionCode<serverVersionCode){
                Bundle bundle=new Bundle();
                bundle.putBoolean("isUpdate",true);
                service.showVersionDialog(apkUrl,"检测到最新版本"+versionName,"1.修复了已知bug",bundle);
            }else{
                Bundle bundle=new Bundle();
                bundle.putBoolean("isUpdate",false);
            }

        }


    }
}
