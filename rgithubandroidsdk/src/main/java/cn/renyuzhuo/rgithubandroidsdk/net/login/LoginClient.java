package cn.renyuzhuo.rgithubandroidsdk.net.login;

import android.content.Context;
import android.content.Intent;

import cn.renyuzhuo.rgithubandroidsdk.activity.WebActivity;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.AccessTokenBean;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.OAuthBean;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.Token;
import cn.renyuzhuo.rgithubandroidsdk.net.Base.GitHub.GitHubBase;
import cn.renyuzhuo.rgithubandroidsdk.net.result.MySubscriber;
import cn.renyuzhuo.rgithubandroidsdk.service.login.LoginService;
import cn.renyuzhuo.rlog.rlog;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by renyuzhuo on 16-10-31.
 */
public class LoginClient {
    public static OAuthBean oAuthBean = OAuthBean.getInstance();
    static LoginClientListener loginClientListener;

    public static void login(Context context, String clientId, String clientSecret, String redirectUri) {
        OAuthBean.getInstance().setClient_id(clientId);
        OAuthBean.getInstance().setClient_secret(clientSecret);
        OAuthBean.getInstance().setRedirect_uri(redirectUri);
        Intent intent = new Intent(context, WebActivity.class);
        context.startActivity(intent);
    }

    public static void token(String code) {
        rlog.d("getToken");
        OAuthBean.getInstance().setCode(code);
        LoginService loginService = GitHubBase.getInstance().build().create(LoginService.class);
        loginService.getToken(oAuthBean)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber<AccessTokenBean>() {

                    @Override
                    public void onNext(AccessTokenBean accessTokenBean) {
                        Token.setAuthorization(accessTokenBean.getAccess_token());
                        if (loginClientListener != null) {
                            loginClientListener.onLoginSuccess(accessTokenBean);
                        }
                    }
                });
    }

    public static void setLoginSuccessListener(LoginClientListener loginSuccessListener) {
        LoginClient.loginClientListener = loginSuccessListener;
    }
}
