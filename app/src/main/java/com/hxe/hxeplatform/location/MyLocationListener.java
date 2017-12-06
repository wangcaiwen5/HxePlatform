package com.hxe.hxeplatform.location;

import com.baidu.location.Address;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.hxe.hxeplatform.base.BaseApplication;
import com.hxe.hxeplatform.utils.SharedPreferencesUtils;

/**
 * Author:wangcaiwen
 * Time:2017/12/4.
 * Description:
 */

public class MyLocationListener extends BDAbstractLocationListener {
    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
        //以下只列举部分获取经纬度相关（常用）的结果信息
        //更多结果信息获取说明，请参照类参考中BDLocation类中的说明

        double latitude = bdLocation.getLatitude();    //获取纬度信息
        double longitude = bdLocation.getLongitude();    //获取经度信息
        float radius = bdLocation.getRadius();    //获取定位精度，默认值为0.0f
        Address address = bdLocation.getAddress();
        String coorType = bdLocation.getCoorType();
        String city = address.city;
        String addrStr = bdLocation.getAddrStr();
        String country = address.country;
        //获取经纬度坐标类型，以LocationClientOption中设置过的坐标类型为准

        int errorCode = bdLocation.getLocType();
        //获取定位类型、定位错误返回码，具体信息可参照类参考中BDLocation类中的说明
        SharedPreferencesUtils.getInstance(BaseApplication.getContext()).putString("latitude",latitude+"");
        SharedPreferencesUtils.getInstance(BaseApplication.getContext()).putString("longitude",longitude+"");
       System.out.println("纬度:"+latitude+"==经度:"+longitude+"地址:"+city+"返回码:"+errorCode);
    }
}
