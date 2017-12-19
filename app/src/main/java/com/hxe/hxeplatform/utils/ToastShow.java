package com.hxe.hxeplatform.utils;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.onetime.platform.R;

/**
 * Time:2017/10/2 22:21
 * Author:王才文
 * Description:
 */

public class ToastShow {
    protected static Toast toast   = null;
    private static String oldMsg;
    private static long oneTime = 0;
    private static long twoTime = 0;
    private Context mContext;

    private volatile static ToastShow singleton;
    private TextView tv;

    private ToastShow(Context context) {
        if(context!=null){
            this.mContext=context.getApplicationContext();
        }

    }

    public static ToastShow getSingleton(Context context) {
        if (singleton == null) {
            synchronized(ToastShow.class) {
                if (singleton == null) {
                    singleton = new ToastShow(context);
                }
            }
        }

        return singleton;
    }

    public  void showToast(String s){
        if(toast==null){
            View toastRoot = View.inflate(mContext,R.layout.toast_layout,null);
            toast=new Toast(mContext);
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
