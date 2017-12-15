package com.hxe.hxeplatform.utils;


import android.util.Log;

public class LogUtils {

	private static boolean debug =true;
	private static String TAG= "vivi";
	private LogUtils() {
		super();
	}

	
	public static void d(String tag,String msg){
		if(debug){
			Log.d(tag,msg);
		}
	}
	
	public static void d(String msg){
		if(debug){
			Log.d(TAG,msg);
		}
	}
}
