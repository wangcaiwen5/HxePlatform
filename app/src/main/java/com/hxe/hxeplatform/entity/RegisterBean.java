package com.hxe.hxeplatform.entity;

/**
 * Author:wangcaiwen
 * Time:2017/11/18.
 * Description:
 */

public class RegisterBean {

    /**
     * h2y_app_id : string
     * pd : {"account":"string","h2y_app_id":"string","password":"string","ref_one_id":0,"sms_code":"string"}
     * token : string
     */

    public String h2y_app_id;
    public PdBean pd;
    public String token;

    public static class PdBean {
        /**
         * account : string
         * h2y_app_id : string
         * password : string
         * ref_one_id : 0
         * sms_code : string
         */

        public String account;
        public String h2y_app_id;
        public String password;
        public int ref_one_id;
        public String sms_code;
    }
}
