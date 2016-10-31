package cn.renyuzhuo.rgithubandroidsdk.net.Base.GitHub;

import cn.renyuzhuo.rgithubandroidsdk.net.GitHubSdkRetrofit;

/**
 * Created by renyuzhuo on 16-10-31.
 */
public class GitHubBase extends GitHubSdkRetrofit {

    public static GitHubBase gitHubBase = null;

    public GitHubBase() {

    }

    public static GitHubSdkRetrofit getInstance() {
        if (gitHubBase == null) {
            gitHubBase = new GitHubBase();
            return gitHubBase;
        } else {
            return gitHubBase;
        }
    }

    @Override
    public String getBaseUrl() {
        return "https://github.com/";
    }
}
