package cn.renyuzhuo.rgithubandroidsdk.Browser;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import cn.renyuzhuo.rgithubandroidsdk.activity.WebActivity;

import static android.app.Activity.RESULT_OK;

/**
 * Created by renyuzhuo on 16-10-31.
 */
public class MyWebViewClient extends WebViewClient {
    private final WebActivity activity;

    public MyWebViewClient(WebActivity activity) {
        this.activity = activity;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        Uri uri = Uri.parse(url);
        if (uri.getScheme().equals("http")) {
            Intent data = new Intent();
            data.setData(uri);
            activity.setResult(RESULT_OK, data);
            activity.onUserLoggedIn(data.getData());
            return true;
        }
        return super.shouldOverrideUrlLoading(view, url);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        view.loadUrl(request.getUrl().toString());
        return true;
    }
}