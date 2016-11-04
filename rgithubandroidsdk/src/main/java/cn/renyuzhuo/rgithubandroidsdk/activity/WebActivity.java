package cn.renyuzhuo.rgithubandroidsdk.activity;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import cn.renyuzhuo.rgithubandroidsdk.Browser.MyWebView;
import cn.renyuzhuo.rgithubandroidsdk.Browser.MyWebViewClient;
import cn.renyuzhuo.rgithubandroidsdk.R;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.OAuthBean;
import cn.renyuzhuo.rgithubandroidsdk.net.login.LoginClient;
import cn.renyuzhuo.rgithubandroidsdk.net.result.MySubscriber;
import cn.renyuzhuo.rlog.rlog;
import okhttp3.HttpUrl;

public class WebActivity extends Activity {

    private static String OAUTH_HOST = "www.github.com";
    private static String initialScope = "user,public_repo,repo,notifications";

    MyWebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        MySubscriber.init(getApplicationContext());

        webView = (MyWebView) findViewById(R.id.webview);
        webView.setWebViewClient(new MyWebViewClient(this));
        HttpUrl.Builder url = new HttpUrl.Builder()
                .scheme("https")
                .host(OAUTH_HOST)
                .addPathSegment("login")
                .addPathSegment("oauth")
                .addPathSegment("authorize")
                .addQueryParameter("client_id", OAuthBean.getInstance().getClient_id())
                .addQueryParameter("scope", initialScope);
        webView.loadUrl(url.toString());
        rlog.d(url.toString());
    }

    public void onUserLoggedIn(Uri uri) {
        if (uri != null && uri.getScheme().equals("http")) {
            String code = uri.getQueryParameter("code");
            LoginClient.token(code);
            finish();
        }
    }

}
