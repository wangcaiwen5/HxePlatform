package com.hxe.hxeplatform.entity;

/**
 * Author:wangcaiwen
 * Time:2017/12/1.
 * Description:
 */

public class UserInfoEntity {

    /**
     * msg : 获取用户信息成功
     * code : 0
     * data : {"age":null,"appkey":null,"appsecret":null,"createtime":"2017-12-01T12:49:52","email":null,"fans":0,"follow":0,"gender":0,"icon":"https://www.zhaoapi.cn/images/1512103739787cropped_1512103731527.jpg","latitude":null,"longitude":null,"mobile":"18813146920","money":0,"nickname":"王才文","password":"123456","praiseNum":null,"token":"F7CC2E9C31E04C2862A635FF2182D819","uid":169,"userId":null,"username":"18813146920"}
     */

    public String msg;
    public String code;
    public DataBean data;

    public static class DataBean {
        /**
         * age : null
         * appkey : null
         * appsecret : null
         * createtime : 2017-12-01T12:49:52
         * email : null
         * fans : 0
         * follow : 0
         * gender : 0
         * icon : https://www.zhaoapi.cn/images/1512103739787cropped_1512103731527.jpg
         * latitude : null
         * longitude : null
         * mobile : 18813146920
         * money : 0
         * nickname : 王才文
         * password : 123456
         * praiseNum : null
         * token : F7CC2E9C31E04C2862A635FF2182D819
         * uid : 169
         * userId : null
         * username : 18813146920
         */

        public String age;
        public String appkey;
        public String appsecret;
        public String createtime;
        public String email;
        public int fans;
        public int follow;
        public int gender;
        public String icon;
        public String latitude;
        public String longitude;
        public String mobile;
        public int money;
        public String nickname;
        public String password;
        public String praiseNum;
        public String token;
        public int uid;
        public String userId;
        public String username;
    }
}
