package com.hxe.hxeplatform.entity;

import java.util.List;

/**
 * Author:wangcaiwen
 * Time:2017/12/1.
 * Description:
 */

public class GetVediosListEntity {


    /**
     * msg : 获取作品列表成功
     * code : 0
     * data : [{"commentNum":2,"comments":[{"cid":7,"content":"评论评论评论","createTime":"2017-11-27T23:34:37","jid":null,"mvp":null,"praiseNum":0,"uid":72,"wid":2},{"cid":8,"content":"评论评论评论","createTime":"2017-11-27T23:34:41","jid":null,"mvp":null,"praiseNum":0,"uid":71,"wid":2}],"cover":"https://www.zhaoapi.cn/images/icon.png","createTime":"2017-11-28T11:34:09","favoriteNum":0,"latitude":null,"localUri":null,"longitude":null,"playNum":null,"praiseNum":1,"uid":72,"user":{"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/72.jpg","nickname":"QQ","praiseNum":"null"},"videoUrl":"https://www.zhaoapi.cn/","wid":2,"workDesc":"我是描述2"}]
     */

    public String msg;
    public String code;
    public List<DataBean> data;

    public static class DataBean {
        public boolean isOpen=false;
        /**
         * commentNum : 2
         * comments : [{"cid":7,"content":"评论评论评论","createTime":"2017-11-27T23:34:37","jid":null,"mvp":null,"praiseNum":0,"uid":72,"wid":2},{"cid":8,"content":"评论评论评论","createTime":"2017-11-27T23:34:41","jid":null,"mvp":null,"praiseNum":0,"uid":71,"wid":2}]
         * cover : https://www.zhaoapi.cn/images/icon.png
         * createTime : 2017-11-28T11:34:09
         * favoriteNum : 0
         * latitude : null
         * localUri : null
         * longitude : null
         * playNum : null
         * praiseNum : 1
         * uid : 72
         * user : {"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/72.jpg","nickname":"QQ","praiseNum":"null"}
         * videoUrl : https://www.zhaoapi.cn/
         * wid : 2
         * workDesc : 我是描述2
         */

        public int commentNum;
        public String cover;
        public String createTime;
        public int favoriteNum;
        public Object latitude;
        public Object localUri;
        public Object longitude;
        public Object playNum;
        public int praiseNum;
        public int uid;
        public UserBean user;
        public String videoUrl;
        public int wid;
        public String workDesc;
        public List<CommentsBean> comments;

        public static class UserBean {
            /**
             * age : null
             * fans : null
             * follow : null
             * icon : https://www.zhaoapi.cn/images/72.jpg
             * nickname : QQ
             * praiseNum : null
             */

            public Object age;
            public String fans;
            public String follow;
            public String icon;
            public String nickname;
            public String praiseNum;
        }

        public static class CommentsBean {
            /**
             * cid : 7
             * content : 评论评论评论
             * createTime : 2017-11-27T23:34:37
             * jid : null
             * mvp : null
             * praiseNum : 0
             * uid : 72
             * wid : 2
             */

            public int cid;
            public String content;
            public String createTime;
            public Object jid;
            public Object mvp;
            public int praiseNum;
            public int uid;
            public int wid;
        }
    }
}
