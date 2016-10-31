package cn.renyuzhuo.rgithubandroidsdk.net;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by renyuzhuo on 16-10-30.
 */
public class GitHubSdkRetrofit {

    public static GitHubSdkRetrofit gitHubSdkRetrofit = null;

    public GitHubSdkRetrofit() {

    }

    public static GitHubSdkRetrofit getInstance() {
        if (gitHubSdkRetrofit == null) {
            gitHubSdkRetrofit = new GitHubSdkRetrofit();
            return gitHubSdkRetrofit;
        } else {
            return gitHubSdkRetrofit;
        }
    }

    public Retrofit build() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(getBaseUrl())
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        return enrichBuilder(builder).build();
    }

    public Retrofit.Builder enrichBuilder(Retrofit.Builder builder) {
        return builder;
    }

    public OkHttpClient getOkHttpClient() {
        return GitHubSdkOkHttpClient.getInstance().build();
    }

    public String getBaseUrl() {
        return "";
    }
}
