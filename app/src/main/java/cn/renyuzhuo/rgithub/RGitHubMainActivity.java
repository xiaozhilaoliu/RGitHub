package cn.renyuzhuo.rgithub;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.AccessTokenBean;
import cn.renyuzhuo.rgithubandroidsdk.net.login.LoginClient;
import cn.renyuzhuo.rgithubandroidsdk.net.login.LoginClientListener;
import cn.renyuzhuo.rgithubandroidsdk.net.user.UserInfoClient;
import cn.renyuzhuo.rlog.rlog;

public class RGitHubMainActivity extends AppCompatActivity implements LoginClientListener {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (RGitHubApplication.isLogin) {

        } else {
            login();
        }
    }

    private void login() {
        LoginClient.setLoginSuccessListener(this);
        LoginClient.login(this, RGitHubApplication.clientId, RGitHubApplication.clientSecret, RGitHubApplication.redirectUri);
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFail() {

    }

    @Override
    public void onLoginSuccess(AccessTokenBean accessToken) {
        rlog.d("login success");
        UserInfoClient.getLoginUserInfo();
    }
}
