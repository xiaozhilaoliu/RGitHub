package cn.renyuzhuo.rgithubandroidsdk.net.Base.GitHubContent;

import cn.renyuzhuo.rgithubandroidsdk.net.GitHubSdkRetrofit;

/**
 * Created by renyuzhuo on 16-10-31.
 */
public class GitHubContentBase extends GitHubSdkRetrofit {

    public static GitHubContentBase gitHubContentBase = null;

    private GitHubContentBase() {

    }

    public static GitHubContentBase getInstance() {
        if (gitHubContentBase == null) {
            gitHubContentBase = new GitHubContentBase();
            return gitHubContentBase;
        } else {
            return gitHubContentBase;
        }
    }

    @Override
    public String getBaseUrl() {
        return "https://raw.githubusercontent.com/";
    }

}
