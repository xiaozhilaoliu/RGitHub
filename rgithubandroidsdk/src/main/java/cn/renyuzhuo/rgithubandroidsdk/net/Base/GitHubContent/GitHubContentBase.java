package cn.renyuzhuo.rgithubandroidsdk.net.Base.GitHubContent;

import cn.renyuzhuo.rgithubandroidsdk.net.GitHubSdkOkHttpClient;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by renyuzhuo on 16-10-31.
 */
public class GitHubContentBase {

    public Retrofit build() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.client(getOkHttpClient())
                .baseUrl("https://raw.githubusercontent.com/")
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

    public static GitHubContentBase gitHubContentBase = null;

    public static GitHubContentBase getInstance() {
        if (gitHubContentBase == null) {
            gitHubContentBase = new GitHubContentBase();
            return gitHubContentBase;
        } else {
            return gitHubContentBase;
        }
    }

}
