package com.hxe.hxeplatform.entity;

import java.util.List;

/**
 * Author:wangcaiwen
 * Time:2017/11/28.
 * Description:
 */

public class JokesEntity {


    /**
     * msg : 获取段子列表成功
     * code : 0
     * data : [{"commentNum":null,"content":"   祝又忠","createTime":"2017-11-29T09:22:19","imgUrls":null,"jid":132,"praiseNum":null,"shareNum":null,"uid":99,"user":{"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/99.jpg","nickname":"Zhu","praiseNum":"null"}},{"commentNum":null,"content":"李灿灿1","createTime":"2017-11-29T09:20:46","imgUrls":null,"jid":131,"praiseNum":null,"shareNum":null,"uid":148,"user":{"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/148.jpg","nickname":"李灿灿","praiseNum":"null"}},{"commentNum":null,"content":"李灿灿","createTime":"2017-11-29T09:20:10","imgUrls":null,"jid":130,"praiseNum":null,"shareNum":null,"uid":148,"user":{"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/148.jpg","nickname":"李灿灿","praiseNum":"null"}},{"commentNum":null,"content":"%E6%9D%8E%E7%81%BF%E7%81%BF","createTime":"2017-11-29T09:16:16","imgUrls":null,"jid":129,"praiseNum":null,"shareNum":null,"uid":148,"user":{"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/148.jpg","nickname":"李灿灿","praiseNum":"null"}},{"commentNum":null,"content":"%E5%93%88%E5%93%88","createTime":"2017-11-29T09:08:15","imgUrls":null,"jid":128,"praiseNum":null,"shareNum":null,"uid":92,"user":{"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/92.jpg","nickname":"金鳞","praiseNum":"null"}},{"commentNum":null,"content":"%25E7%2581%25BF%25E7%2581%25BF%2B%2B%25E7%2581%25BF%25E7%2581%25BF","createTime":"2017-11-29T09:00:38","imgUrls":null,"jid":127,"praiseNum":null,"shareNum":null,"uid":148,"user":{"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/148.jpg","nickname":"李灿灿","praiseNum":"null"}},{"commentNum":null,"content":"许愿贝兹.......","createTime":"2017-11-29T09:00:29","imgUrls":null,"jid":126,"praiseNum":null,"shareNum":null,"uid":100,"user":{"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/100.jpg","nickname":"节能君","praiseNum":"null"}},{"commentNum":null,"content":"安居客大富科技爱好的空间哈罚款和地方卡的","createTime":"2017-11-29T08:52:03","imgUrls":null,"jid":125,"praiseNum":null,"shareNum":null,"uid":551,"user":{"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/551.jpg","nickname":"_zhang","praiseNum":"null"}},{"commentNum":null,"content":"%25E6%259D%258E%25E7%2581%25BF%25E7%2581%25BF","createTime":"2017-11-29T08:51:13","imgUrls":null,"jid":124,"praiseNum":null,"shareNum":null,"uid":148,"user":{"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/148.jpg","nickname":"李灿灿","praiseNum":"null"}},{"commentNum":null,"content":"123","createTime":"2017-11-29T08:45:37","imgUrls":null,"jid":123,"praiseNum":null,"shareNum":null,"uid":170,"user":{"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/170.jpg","nickname":"wonderful","praiseNum":"null"}}]
     */

    public String msg;
    public String code;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * commentNum : null
         * content :    祝又忠
         * createTime : 2017-11-29T09:22:19
         * imgUrls : null
         * jid : 132
         * praiseNum : null
         * shareNum : null
         * uid : 99
         * user : {"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/99.jpg","nickname":"Zhu","praiseNum":"null"}
         */

        public Object commentNum;
        public String content;
        public String createTime;
        public Object imgUrls;
        public int jid;
        public Object praiseNum;
        public Object shareNum;
        public int uid;
        public UserBean user;

        public static class UserBean {
            /**
             * age : null
             * fans : null
             * follow : null
             * icon : https://www.zhaoapi.cn/images/99.jpg
             * nickname : Zhu
             * praiseNum : null
             */

            public Object age;
            public String fans;
            public String follow;
            public String icon;
            public String nickname;
            public String praiseNum;
        }
    }
}
