package com.hxe.hxeplatform.utils;

import android.content.Context;
import android.os.Environment;

import com.hxe.hxeplatform.rxretrofit.http.HttpCallBack;
import com.hxe.hxeplatform.utils.LogUtils;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * @Description: 描述
 * @AUTHOR 刘楠  Create By 2016/10/27 0027 15:56
 */
public class FileUtils {

    public static File createFile(Context context){

        File file=null;
        String state = Environment.getExternalStorageState();

        if(state.equals(Environment.MEDIA_MOUNTED)){

            file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/yikezhong.apk");
        }else {
            file = new File(context.getCacheDir().getAbsolutePath()+"/yikezhong.apk");
        }

      LogUtils.d("vivi","file "+file.getAbsolutePath());

        return file;

    }

    public static void writeFile2Disk(ResponseBody response, File file, HttpCallBack httpCallBack){

        long currentLength = 0;
        OutputStream os =null;

        InputStream is = response.byteStream();
        long totalLength =response.contentLength();

        try {

            os = new FileOutputStream(file);

            int len ;

            byte [] buff = new byte[1024];

            while((len=is.read(buff))!=-1){

                os.write(buff,0,len);
                currentLength+=len;
                LogUtils.d("vivi","当前进度:"+currentLength);
                httpCallBack.onLoading(currentLength,totalLength);
            }
            // httpCallBack.onLoading(currentLength,totalLength,true);

        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if(os!=null){
                try {
                    os.close();
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
            if(is!=null){
                try {
                    is.close();
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}


