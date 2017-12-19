package com.hxe.hxeplatform.utils;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hxe.hxeplatform.base.BaseApplication;
import com.onetime.platform.R;

/**
 * Time:2017/10/2 22:21
 * Author:王才文
 * Description:
 */

public class ShowToast {
    protected static Toast toast   = null;
    private static String oldMsg;
    private static long oneTime = 0;
    private static long twoTime = 0;
    private static TextView tv;



    public static   void showToast(String s){

        if(toast==null){
            View toastRoot = View.inflate(BaseApplication.getContext(),R.layout.toast_layout,null);
            toast=new Toast(BaseApplication.getContext());
            toast.setView(toastRoot);
            tv = (TextView)toastRoot.findViewById(R.id.TextViewInfo);
            tv.setText(s);
            toast.show();

            oneTime=System.currentTimeMillis();
        }else{
            twoTime=System.currentTimeMillis();
            if(s.equals(oldMsg)){
                if(twoTime-oneTime>Toast.LENGTH_SHORT){
                    toast.show();
                }
            }else{
                oldMsg = s;
                tv.setText(s);
                toast.show();
            }
        }
        oneTime=twoTime;
    }


}
