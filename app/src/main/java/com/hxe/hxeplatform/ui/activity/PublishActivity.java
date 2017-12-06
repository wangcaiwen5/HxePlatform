package com.hxe.hxeplatform.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import com.google.gson.Gson;
import com.hxe.hxeplatform.R;
import com.hxe.hxeplatform.adapter.FullyGridLayoutManager;
import com.hxe.hxeplatform.adapter.GridImageAdapter;
import com.hxe.hxeplatform.base.BaseActivity;
import com.hxe.hxeplatform.base.BasePresenter;
import com.hxe.hxeplatform.mvp.presenter.UploadPresenter;
import com.hxe.hxeplatform.mvp.view.UploadFileView;
import com.hxe.hxeplatform.myview.MyToolBar;
import com.hxe.hxeplatform.utils.SharedPreferencesUtils;
import com.hxe.hxeplatform.utils.ToastShow;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.permissions.RxPermissions;
import com.luck.picture.lib.tools.PictureFileUtils;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

import static com.igexin.sdk.GTServiceManager.context;

public class PublishActivity extends BaseActivity<UploadPresenter> implements View.OnClickListener,UploadFileView{

    @BindView(R.id.m_publishToolbar)
    MyToolBar mToolBar;
    @BindView(R.id.et_text)
    EditText mText;
    @BindView(R.id.img_recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.pb_uploadProgressBar)
    ProgressBar mProgressBar;

    private GridImageAdapter adapter;
    private List<LocalMedia> selectList = new ArrayList<>();
    private int maxSelectNum = 9;
    private List<File> files= new ArrayList<>();


    @Override
    protected UploadPresenter getPresenter() {
        return new UploadPresenter(this);
    }

    @Override
    protected boolean getIsWindow() {
        return false;
    }

    @Override
    protected void init() {
    initView();
    initData();
    }

    private void initData() {
        FullyGridLayoutManager manager = new FullyGridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);

        adapter = new GridImageAdapter(this, onAddPicClickListener);
        adapter.setList(selectList);
        adapter.setSelectMax(maxSelectNum);
        mRecyclerView.setAdapter(adapter);


        // 清空图片缓存，包括裁剪、压缩后的图片 注意:必须要在上传完成后调用 必须要获取权限
        RxPermissions permissions = new RxPermissions(this);
        permissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    PictureFileUtils.deleteCacheDirFile(PublishActivity.this);
                } else {
                    Toast.makeText(PublishActivity.this,
                            getString(R.string.picture_jurisdiction), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });


        adapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                ToastShow.getSingleton(getApplicationContext()).showToast("图片查看");
                if (selectList.size() > 0) {
                    LocalMedia media = selectList.get(position);
                    String pictureType = media.getPictureType();
                    int mediaType = PictureMimeType.pictureToVideo(pictureType);
                    switch (mediaType) {
                        case 1:

                            // 预览图片 可自定长按保存路径
                            //PictureSelector.create(MainActivity.this).externalPicturePreview(position, "/custom_file", selectList);
                            PictureSelector.create(PublishActivity.this).externalPicturePreview(position, selectList);
                            break;
                        case 2:
                            // 预览视频
                            PictureSelector.create(PublishActivity.this).externalPictureVideo(media.getPath());
                            break;
                        case 3:
                            // 预览音频
                            PictureSelector.create(PublishActivity.this).externalPictureAudio(media.getPath());
                            break;
                    }
                }
            }
        });

    }
    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {

            // 进入相册 以下是例子：用不到的api可以不写
           PictureSelector.create(PublishActivity.this)
                    .openGallery(PictureMimeType.ofAll())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                    // .theme(R.style.picture_default_style)//主题样式(不设置为默认样式) 也可参考demo values/styles下 例如：R.style.picture.white.style
                    .maxSelectNum(9)// 最大图片选择数量 int
                    .minSelectNum(1)// 最小选择数量 int
                    .imageSpanCount(3)// 每行显示个数 int
                    .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                    .previewImage(true)// 是否可预览图片 true or false
                    .previewVideo(true)// 是否可预览视频 true or false
                    .enablePreviewAudio(true) // 是否可播放音频 true or false
                    .isCamera(true)// 是否显示拍照按钮 true or false
                    .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                    .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                    .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                    // .setOutputCameraPath("/CustomPath")// 自定义拍照保存路径,可不填
                    .enableCrop(false)// 是否裁剪 true or false
                    .compress(false)// 是否压缩 true or false
                    .glideOverride(400,400)// int glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                    .withAspectRatio(3,2)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                    .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示 true or false
                    .isGif(true)// 是否显示gif图片 true or false
                    // .compressSavePath(getPath())//压缩图片保存地址
                    .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                    .circleDimmedLayer(false)// 是否圆形裁剪 true or false
                    .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
                    .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                    .openClickSound(false)// 是否开启点击声音 true or false
                     .selectionMedia(selectList)// 是否传入已选图片 List<LocalMedia> list
                    .previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
                    // .cropCompressQuality()// 裁剪压缩质量 默认90 int
                    .minimumCompressSize(100)// 小于100kb的图片不压缩
                    .synOrAsy(true)//同步true或异步false 压缩 默认同步
                    //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效 int
                    .rotateEnabled(true) // 裁剪是否可旋转图片 true or false
                    .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
                    .videoQuality(0)// 视频录制质量 0 or 1 int
                    .videoMaxSecond(15)// 显示多少秒以内的视频or音频也可适用 int
                    .videoMinSecond(10)// 显示多少秒以内的视频or音频也可适用 int
                    //.recordVideoSecond()//视频秒数录制 默认60s int
                    .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code

        }

    };
    private void initView() {
    mToolBar.setLeftTitleText("取消");
    mToolBar.setMainTitle("发表文章");
    mToolBar.setRightTitleText("发表");

    mToolBar.setLeftTitleClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            isSave();
        }
    });

    mToolBar.setRightTitleClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            for (LocalMedia localMedia : selectList) {
                String path = localMedia.getPath();
                File file = new File(path);
                files.add(file);
            }


            mPresenter.uploadFile(SharedPreferencesUtils.getInstance(PublishActivity.this).getString("uid"),mText.getText().toString(),files);
        }
    });

    }

    private void isSave() {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_popupwindow, null);
        TextView btnYes =  view.findViewById(R.id.btn_yes);
        TextView btnNo = view.findViewById(R.id.btn_no);
        TextView btnCancel =  view.findViewById(R.id.btn_cancel);
        final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
        popupWindow.setOutsideTouchable(true);
        View parent = LayoutInflater.from(this).inflate(R.layout.activity_publish, null);
        popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        //popupWindow在弹窗的时候背景半透明
        final WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 0.5f;
        getWindow().setAttributes(params);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                params.alpha = 1.0f;
                getWindow().setAttributes(params);
            }
        });

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastShow.getSingleton(getApplicationContext()).showToast("已保存");
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_publish;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);

                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，已取压缩路径为准，因为是先裁剪后压缩的
                    for (LocalMedia media : selectList) {
                        System.out.println("======为原图path"+media.getPath());
                        System.out.println("为裁剪后path"+media.getCutPath());
                        System.out.println("为压缩后path"+media.getCompressPath());
                    }
                    adapter.setList(selectList);
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    }

    @Override
    public void onFail(String msg) {
        System.out.println("");
    }

    @Override
    public void onSuccess(ResponseBody body) {
        try {
            String string = body.string();
            JSONObject jsonObject = new JSONObject(string);
            String code = jsonObject.getString("code");
            String msg = jsonObject.getString("msg");
            if(code.equals("0")){
                ToastShow.getSingleton(PublishActivity.this).showToast(msg);
                finish();
            }else if (code.equals("2")){
                gotoActivity(LoginActivity.class,true);
                ToastShow.getSingleton(PublishActivity.this).showToast(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void ShowProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("上传异常"+throwable);
    }
}
