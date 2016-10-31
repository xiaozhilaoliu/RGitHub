package cn.renyuzhuo.rgithubandroidsdk.net.login;

import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.AccessTokenBean;
import cn.renyuzhuo.rgithubandroidsdk.net.BaseListener;

/**
 * Created by renyuzhuo on 16-10-31.
 */
public interface LoginClientListener extends BaseListener {
    void onLoginSuccess(AccessTokenBean accessToken);
}
