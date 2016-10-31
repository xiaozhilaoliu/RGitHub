package cn.renyuzhuo.rgithubandroidsdk.net.Base.ApiBase;

import cn.renyuzhuo.rgithubandroidsdk.net.GitHubSdkRetrofit;

/**
 * Created by renyuzhuo on 16-10-31.
 */
public class ApiBase extends GitHubSdkRetrofit {

    public static ApiBase apiBase = null;

    public ApiBase() {

    }

    public static ApiBase getInstance() {
        if (apiBase == null) {
            apiBase = new ApiBase();
            return apiBase;
        } else {
            return apiBase;
        }
    }

    @Override
    public String getBaseUrl() {
        return "https://api.github.com/";
    }
}
