package cn.renyuzhuo.rgithub;

import android.app.Application;

import com.bumptech.glide.load.model.GlideUrl;
import com.squareup.leakcanary.LeakCanary;

import java.util.Map;

import cn.renyuzhuo.rgithubandroidsdk.GitHubSdk;
import cn.renyuzhuo.rlog.rlog;

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
        rlog.setDebugLever(rlog.DEBUG_LEVEL.debug);
        rlog.d("init GitHubSdk");
        GitHubSdk.init(this);

        rlog.d("LeakCanary init");
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }
}
