package cn.renyuzhuo.rgithub;

import android.app.Application;

import cn.renyuzhuo.rgithubandroidsdk.GitHubSdk;

/**
 * Created by renyuzhuo on 16-10-30.
 */
public class RGitHubApplication extends Application {

    public static boolean isLogin = false;
    public static String clientId = "c5c00b034fb934c6ae4b";
    public static String clientSecret = "8c0f9748134af3497331f4761fd9da7356ccaa4e";
    public static String redirectUri = "http://renyuzhuo.cn";

    @Override
    public void onCreate() {
        super.onCreate();
        GitHubSdk.init(this);
    }
}
