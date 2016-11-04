package cn.renyuzhuo.rgithubandroidsdk.net;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.concurrent.TimeUnit;

import cn.renyuzhuo.rgithubandroidsdk.GitHubSdk;
import cn.renyuzhuo.rgithubandroidsdk.bean.sdkBean.DebugLevelBean;
import okhttp3.Cache;
import okhttp3.logging.HttpLoggingInterceptor;

public class GitHubSdkOkHttpClient {

    private static GitHubSdkOkHttpClient gitHubSdkOkHttpClient = null;

    private GitHubSdkOkHttpClient() {

    }

    public static GitHubSdkOkHttpClient getInstance() {
        if (gitHubSdkOkHttpClient == null) {
            gitHubSdkOkHttpClient = new GitHubSdkOkHttpClient();
            return gitHubSdkOkHttpClient;
        } else {
            return gitHubSdkOkHttpClient;
        }
    }

    public okhttp3.OkHttpClient build() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(DebugLevelBean.getHttpLogLevel());
        okhttp3.OkHttpClient.Builder builder = new okhttp3.OkHttpClient.Builder();
        builder.addNetworkInterceptor(new StethoInterceptor())
                .addInterceptor(loggingInterceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(3 * 60, TimeUnit.SECONDS)
                .cache(new Cache(GitHubSdk.getContext().getCacheDir(), 10 * 1024 * 1024))
                .build();
        return enrichBuilder(builder).build();
    }

    protected okhttp3.OkHttpClient.Builder enrichBuilder(okhttp3.OkHttpClient.Builder builder) {
        return builder;
    }
}