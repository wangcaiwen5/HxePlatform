package com.hxe.hxeplatform.utils;

import android.content.Context;
import android.view.WindowManager;

/**
 * Author:wangcaiwen
 * Time:2017/11/30.
 * Description:
 */

public class PhoneWindowSizeUtils {

    public static int getPhoneWindowWidth(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        int width = manager.getDefaultDisplay().getWidth();
        return width;
    }

}
