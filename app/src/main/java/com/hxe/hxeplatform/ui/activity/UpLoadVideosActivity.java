package com.hxe.hxeplatform.ui.activity;


import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.baidu.location.Address;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.hxe.hxeplatform.R;
import com.hxe.hxeplatform.adapter.FullyGridLayoutManager;
import com.hxe.hxeplatform.adapter.GridImageAdapter;
import com.hxe.hxeplatform.adapter.GridVideoAdapter;
import com.hxe.hxeplatform.base.BaseActivity;
import com.hxe.hxeplatform.base.BasePresenter;
import com.hxe.hxeplatform.location.MyLocationListener;
import com.hxe.hxeplatform.mvp.presenter.UpLoadVideoPresenter;
import com.hxe.hxeplatform.mvp.view.UpLoadVideoView;
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

public class UpLoadVideosActivity extends BaseActivity<UpLoadVideoPresenter> implements UpLoadVideoView {

    @BindView(R.id.m_VideoToolbar)
    MyToolBar myToolBar;
    @BindView(R.id.rv_VideoList)
    RecyclerView mRecyclerView;
    @BindView(R.id.et_text)
    EditText editText;
    @BindView(R.id.pb_videoLoading)
    ProgressBar videoLoading;

    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();

    private GridVideoAdapter adapter;
    private List<LocalMedia> selectList = new ArrayList<>();
    private int maxSelectNum = 1;


    @Override
    protected UpLoadVideoPresenter getPresenter() {
        return new UpLoadVideoPresenter(this);
    }

    @Override
    protected boolean getIsWindow() {
        return false;
    }

    @Override
    protected void init() {
        initLocation();
        initData();
    }

    private void initLocation() {
        //声明LocationClient类
        mLocationClient = new LocationClient(getApplicationContext());

        //注册监听函数
        mLocationClient.registerLocationListener(myListener);


        LocationClientOption option = new LocationClientOption();

        option.setLocationMode(LocationClientOption.LocationMode.Battery_Saving);
//可选，设置定位模式，默认高精度
//LocationMode.Hight_Accuracy：高精度；
//LocationMode. Battery_Saving：低功耗；
//LocationMode. Device_Sensors：仅使用设备；

        option.setCoorType("bd09ll");
//可选，设置返回经纬度坐标类型，默认gcj02
//gcj02：国测局坐标；
//bd09ll：百度经纬度坐标；
//bd09：百度墨卡托坐标；
//海外地区定位，无需设置坐标类型，统一返回wgs84类型坐标

        option.setScanSpan(0);
//可选，设置发起定位请求的间隔，int类型，单位ms
//如果设置为0，则代表单次定位，即仅定位一次，默认为0
//如果设置非0，需设置1000ms以上才有效

        option.setOpenGps(true);
//可选，设置是否使用gps，默认false
//使用高精度和仅用设备两种定位模式的，参数必须设置为true

        option.setLocationNotify(true);
//可选，设置是否当GPS有效时按照1S/1次频率输出GPS结果，默认false

        option.setIgnoreKillProcess(true);
//可选，定位SDK内部是一个service，并放到了独立进程。
//设置是否在stop的时候杀死这个进程，默认（建议）不杀死，即setIgnoreKillProcess(true)

        option.SetIgnoreCacheException(false);
//可选，设置是否收集Crash信息，默认收集，即参数为false

      /*  option.setWifiCacheTimeOut();
        option.setWifiValidTime(5*60*1000);*/
//可选，7.2版本新增能力
//如果设置了该接口，首次启动定位时，会先判断当前WiFi是否超出有效期，若超出有效期，会先重新扫描WiFi，然后定位

        option.setEnableSimulateGps(false);
//可选，设置是否需要过滤GPS仿真结果，默认需要，即参数为false

        mLocationClient.setLocOption(option);
//mLocationClient为第二步初始化过的LocationClient对象
//需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
//更多LocationClientOption的配置，请参照类参考中LocationClientOption类的详细说明
        mLocationClient.start();
       // mLocationClient.requestLocation();

    }

    private void initData() {
    myToolBar.setLeftTitleText("取消");
    myToolBar.setRightTitleText("发表");
    myToolBar.setMainTitle("发表视频");
    myToolBar.setLeftTitleClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    });

        /**
         * 发布视频
         */
    myToolBar.setRightTitleClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            System.out.println("=======视频上传");
            String latitude = SharedPreferencesUtils.getInstance(getApplicationContext()).getString("latitude");
            String longitude = SharedPreferencesUtils.getInstance(getApplicationContext()).getString("longitude");
            final String uid = SharedPreferencesUtils.getInstance(getApplicationContext()).getString("uid");
            System.out.println("纬度:"+latitude+"==经度:"+longitude);
            if(selectList.size()<=0){
                ToastShow.getSingleton(getApplicationContext()).showToast("请添加视频");
            }
            mPresenter.UpLoadVideoData(uid,
                    new File(selectList.get(0).getPath()),
                    new File("/storage/sdcard0/yyyy.png"),
                    editText.getText().toString(),
                    latitude,
                    longitude
            );

            MediaMetadataRetriever media = new MediaMetadataRetriever();

            media.setDataSource(selectList.get(0).getPath());
            Bitmap frameAtTime = media.getFrameAtTime();






        }
    });

        FullyGridLayoutManager manager = new FullyGridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);

        adapter = new GridVideoAdapter(this, onAddPicClickListener);
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
                    PictureFileUtils.deleteCacheDirFile(UpLoadVideosActivity.this);
                } else {
                    Toast.makeText(UpLoadVideosActivity.this,
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



        adapter.setOnItemClickListener(new GridVideoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (selectList.size() > 0) {
                    LocalMedia media = selectList.get(position);
                    String pictureType = media.getPictureType();
                    int mediaType = PictureMimeType.pictureToVideo(pictureType);
                    switch (mediaType) {
                        case 1:
                            // 预览图片 可自定长按保存路径
                            //PictureSelector.create(MainActivity.this).externalPicturePreview(position, "/custom_file", selectList);
                            PictureSelector.create(UpLoadVideosActivity.this).externalPicturePreview(position, selectList);
                            break;
                        case 2:
                            // 预览视频
                            PictureSelector.create(UpLoadVideosActivity.this).externalPictureVideo(media.getPath());
                            break;
                        case 3:
                            // 预览音频
                            PictureSelector.create(UpLoadVideosActivity.this).externalPictureAudio(media.getPath());
                            break;
                    }
                }
            }
        });

    }


    private GridVideoAdapter.onAddPicClickListener onAddPicClickListener = new GridVideoAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {

            // 进入相册 以下是例子：用不到的api可以不写
            PictureSelector.create(UpLoadVideosActivity.this)
                    .openGallery(PictureMimeType.ofVideo())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                    // .theme(R.style.picture_default_style)//主题样式(不设置为默认样式) 也可参考demo values/styles下 例如：R.style.picture.white.style
                    .maxSelectNum(1)// 最大图片选择数量 int
                    .minSelectNum(1)// 最小选择数量 int
                    .previewVideo(true)// 是否可预览视频 true or false
                    .isCamera(true)// 是否显示拍照按钮 true or false
                    .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                    // .setOutputCameraPath("/CustomPath")// 自定义拍照保存路径,可不填
                    .glideOverride(400,400)// int glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                    .withAspectRatio(3,2)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                    .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示 true or false
                    .openClickSound(false)// 是否开启点击声音 true or false
                    .selectionMedia(selectList)// 是否传入已选图片 List<LocalMedia> list
                    .videoQuality(1)// 视频录制质量 0 or 1 int
                    .videoMaxSecond(60)// 显示多少秒以内的视频or音频也可适用 int
                    .videoMinSecond(1)// 显示多少秒以内的视频or音频也可适用 int
                    .recordVideoSecond(60)//视频秒数录制 默认60s int
                    .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code

        }

    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_up_load_videos;
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

    }

    @Override
    public void onSuccess(ResponseBody body) {
        try {
            String string = body.string();
            JSONObject jsonObject = new JSONObject(string);
            String code = jsonObject.getString("code");
            String msg = jsonObject.getString("msg");
            if(code.equals("0")){
                ToastShow.getSingleton(getApplicationContext()).showToast(msg);
                finish();
            }else if(code.equals("2")){
                ToastShow.getSingleton(getApplicationContext()).showToast("登录失效,请重新登录");
                gotoActivity(LoginActivity.class,true);
            }else{
                ToastShow.getSingleton(getApplicationContext()).showToast(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.unRegisterLocationListener(myListener);
    }

    @Override
    public void hideProgressBar() {
        videoLoading.setVisibility(View.INVISIBLE);
    }

    @Override
    public void ShowProgressBar() {
        videoLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void onError(Throwable throwable) {
        ToastShow.getSingleton(getApplicationContext()).showToast("上传异常:"+throwable);
    }
}
