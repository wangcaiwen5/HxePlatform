package com.hxe.hxeplatform.entity;

/**
 * Author:wangcaiwen
 * Time:2017/11/28.
 * Description:
 */

public class LoginEntity {


    /**
     * msg : 登录成功
     * code : 0
     * data : {"age":null,"appkey":null,"appsecret":null,"createtime":"2017-11-29T08:35:25","email":null,"fans":null,"follow":null,"gender":0,"icon":"https://www.zhaoapi.cn/images/169.jpg","latitude":null,"longitude":null,"mobile":"18813146920","money":0,"nickname":"王才文","password":"123456","praiseNum":null,"token":"F7CC2E9C31E04C2862A635FF2182D819","uid":169,"userId":null,"username":"18813146920"}
     */

    public String msg;
    public String code;
    public DataBean data;

    public static class DataBean {
        /**
         * age : null
         * appkey : null
         * appsecret : null
         * createtime : 2017-11-29T08:35:25
         * email : null
         * fans : null
         * follow : null
         * gender : 0
         * icon : https://www.zhaoapi.cn/images/169.jpg
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

        public Object age;
        public Object appkey;
        public Object appsecret;
        public String createtime;
        public Object email;
        public Object fans;
        public Object follow;
        public int gender;
        public String icon;
        public Object latitude;
        public Object longitude;
        public String mobile;
        public int money;
        public String nickname;
        public String password;
        public Object praiseNum;
        public String token;
        public int uid;
        public Object userId;
        public String username;
    }
}
