package com.hxe.hxeplatform.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Author:wangcaiwen
 * Time:2017/11/20.
 * Description:sp
 */

public class SharedPreferencesUtils {
    private volatile static SharedPreferencesUtils spInstance;
    private static final String _SP="_DATA";
    private SharedPreferences sharedPreferences;
    private Context mContext;
    private  SharedPreferences.Editor edit;

    private SharedPreferencesUtils(Context context) {
        if(context!=null){
            this.mContext=context.getApplicationContext();
            sharedPreferences = context.getApplicationContext().getSharedPreferences("_DATA", Context.MODE_PRIVATE);
            edit = sharedPreferences.edit();
        }
    }

    public static SharedPreferencesUtils getInstance(Context mContext){

        if(spInstance==null){
            synchronized (SharedPreferencesUtils.class){
                if(spInstance==null){
                    spInstance = new SharedPreferencesUtils(mContext);
                }
            }
        }

        return spInstance;
    }


    public void putString(String key,String value){
        edit.putString(key,value).commit();
    }

    public String getString(String key){
        String string = sharedPreferences.getString(key, null);

        return string;
    }


    public void putBoolean(String key,Boolean value){
        edit.putBoolean(key,value).commit();
    }

    public boolean getBoolean(String key){
        boolean aBoolean = sharedPreferences.getBoolean(key, false);

        return aBoolean;
    }
}
