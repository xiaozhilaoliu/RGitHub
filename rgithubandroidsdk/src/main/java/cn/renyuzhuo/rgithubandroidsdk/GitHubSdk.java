package cn.renyuzhuo.rgithubandroidsdk;

import android.content.Context;

import cn.renyuzhuo.rgithubandroidsdk.bean.sdkBean.DebugLevelBean;

/**
 * Created by renyuzhuo on 16-10-30.
 */
public class GitHubSdk {
    private static Context context;

    public static void init(Context context) {
        GitHubSdk.context = context;
        DebugLevelBean.setLogLevel(DebugLevelBean.DEBUG_LEVEL.DEBUG);
    }

    public static void setDebugLevel(DebugLevelBean.DEBUG_LEVEL debugLevel) {
        DebugLevelBean.setLogLevel(debugLevel);
    }

    public static Context getContext() {
        return context;
    }
}
