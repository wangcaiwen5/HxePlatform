package com.hxe.hxeplatform.entity;

import java.util.List;

/**
 * Author:wangcaiwen
 * Time:2017/12/14.
 * Description:
 */

public class VideoContentEntity {

    /**
     * commentNum : 0
     * comments : []
     * cover : https://www.zhaoapi.cn/images/quarter/151246011160020171205154820.jpg
     * createTime : 2017-12-05T15:48:31
     * favoriteNum : 0
     * latitude : 40.040365
     * longitude : 116.30002
     * praiseNum : 0
     * uid : 188
     * user : {"fans":"null","follow":"false","icon":"https://www.zhaoapi.cn/images/188.jpg","nickname":"muzi","praiseNum":"null"}
     * videoUrl : https://www.zhaoapi.cn/images/quarter/1512460111600video_20171205_154754.mp4
     * wid : 33
     * workDesc :
     */

    public int commentNum;
    public String cover;
    public String createTime;
    public int favoriteNum;
    public String latitude;
    public String longitude;
    public int praiseNum;
    public int uid;
    public UserBean user;
    public String videoUrl;
    public int wid;
    public String workDesc;
    public List<CommentsBean> comments;

    public static class UserBean {
        /**
         * fans : null
         * follow : false
         * icon : https://www.zhaoapi.cn/images/188.jpg
         * nickname : muzi
         * praiseNum : null
         */

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
