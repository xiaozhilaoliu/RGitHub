package cn.renyuzhuo.rgithubandroidsdk.net.Base.CodeHub;

import cn.renyuzhuo.rgithubandroidsdk.net.GitHubSdkRetrofit;

/**
 * Created by renyuzhuo on 16-11-7.
 */
public class TrendingBase extends GitHubSdkRetrofit {
    public static TrendingBase trendingBase = null;

    public TrendingBase() {

    }

    public static TrendingBase getInstance() {
        if (trendingBase == null) {
            trendingBase = new TrendingBase();
            return trendingBase;
        } else {
            return trendingBase;
        }
    }

    @Override
    public String getBaseUrl() {
        return "http://trending.codehub-app.com/";
    }
}
