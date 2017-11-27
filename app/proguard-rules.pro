#=============================腾讯bugly========================#
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}
#=============================友盟统计========================#
-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}
-keep public class com.example.wquarter.R$*{
public static final int *;
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
#=============================短信验证登录========================#
-dontwarn com.mob.**
-keep class com.mob.**{*;}

-dontwarn cn.smssdk.**
-keep class cn.smssdk.**{*;}
