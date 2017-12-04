package com.hxe.hxeplatform.ui.activity;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hxe.hxeplatform.R;
import com.hxe.hxeplatform.base.BaseActivity;
import com.hxe.hxeplatform.utils.SharedPreferencesUtils;
import com.squareup.haha.perflib.Main;

public class StartActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Boolean isLogin = SharedPreferencesUtils.getInstance(getApplicationContext()).getBoolean("isLogin");
        if(isLogin){
            Intent intent = new Intent(StartActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }else{
            Intent intent = new Intent(StartActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

}
