package com.hxe.hxeplatform.myview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bwie.uploadpicture.view.CircleImageView;
import com.hxe.hxeplatform.R;
import com.hxe.hxeplatform.base.BaseApplication;

/**
 * Author:wangcaiwen
 * Time:2017/11/25.
 * Description:
 */

public class MyMainToolBar extends Toolbar {

    /**
     * 中间Title
     */
    private TextView mTxtMiddleTitle;
    /**
     * 右侧Title
     */
    private TextView mTxtRightTitle;
    private CircleImageView mLeftCircleImageView;

    public MyMainToolBar(Context context)
    {
        this(context,null);
    }

    public MyMainToolBar(Context context, @Nullable AttributeSet attrs)
    {
        this(context, attrs,0);
    }

    public MyMainToolBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.toolbar_main_layout, this);
        mTxtMiddleTitle = (TextView) findViewById(R.id.txt_main_title);
        mLeftCircleImageView = findViewById(R.id.clv_headImg);
        mTxtRightTitle = (TextView) findViewById(R.id.txt_right_title);
    }




    //设置中间title的内容
    public void setMainTitle(String text)
    {
        this.setTitle(" ");
        mTxtMiddleTitle.setVisibility(View.VISIBLE);
        mTxtMiddleTitle.setText(text);
    }



    //设置中间title的内容文字的颜色
    public void setMainTitleColor(int color)
    {
        mTxtMiddleTitle.setTextColor(color);
    }






    public void setLeftHeadImgDrawable(int res){
        mLeftCircleImageView.setImageResource(res);
    }

    public void setUrlLeftCircleImageView(String url){
        RequestOptions option = new RequestOptions().placeholder(R.mipmap.headimg).diskCacheStrategy(DiskCacheStrategy.NONE);
        Glide.with(BaseApplication.getContext()).load(url).apply(option).into(mLeftCircleImageView);
    }

    //设置头像的点击事件
    public void setLeftHeadImgListener(OnClickListener onClickListener){
        mLeftCircleImageView.setOnClickListener(onClickListener);
    }

    public CircleImageView getLeftHeadImgView(){
        return mLeftCircleImageView;
    }


    //设置title右边文字
    public void setRightTitleText(String text)
    {
        mTxtRightTitle.setVisibility(View.VISIBLE);
        mTxtRightTitle.setText(text);
    }

    //设置title右边文字颜色
    public void setRightTitleColor(int color)
    {
        mTxtRightTitle.setTextColor(color);
    }

    //设置title右边图标
    public void setRightTitleDrawable(int res)
    {
        Drawable dwRight = ContextCompat.getDrawable(getContext(), res);
        dwRight.setBounds(0, 0, dwRight.getMinimumWidth(), dwRight.getMinimumHeight());
        mTxtRightTitle.setCompoundDrawables(null, null, dwRight, null);
    }

    //设置title右边点击事件
    public void setRightTitleClickListener(OnClickListener onClickListener)
    {
        mTxtRightTitle.setOnClickListener(onClickListener);
    }
}
