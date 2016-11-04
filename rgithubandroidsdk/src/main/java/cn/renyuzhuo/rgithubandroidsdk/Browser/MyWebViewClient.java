package cn.renyuzhuo.rgithubandroidsdk.Browser;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.afollestad.materialdialogs.MaterialDialog;

import cn.renyuzhuo.rgithubandroidsdk.Dialog.LoadingDialog;
import cn.renyuzhuo.rgithubandroidsdk.R;
import cn.renyuzhuo.rgithubandroidsdk.activity.WebActivity;
import cn.renyuzhuo.rlog.rlog;

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
            // 善后
            isShow = false;
            webDialog.dismiss();
            Intent data = new Intent();
            data.setData(uri);
            activity.setResult(RESULT_OK, data);
            activity.onUserLoggedIn(data.getData());
            return true;
        }
        return super.shouldOverrideUrlLoading(view, url);
    }

    private static MaterialDialog webDialog;
    private boolean isShow = true;

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (webDialog != null) {
            rlog.d("webDialog dismiss");
            webDialog.dismiss();
        }
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (webDialog != null) {
            if (webDialog.isShowing()) {
                webDialog.dismiss();
            }
        }
        if (isShow) {
            rlog.d("webDialog show");
            webDialog = new MaterialDialog.Builder(activity)
                    .progress(true, 0)
                    .content(activity.getString(R.string.web_loading))
                    .show();
        }
    }

    public static void closeDialog() {
        if (webDialog != null) {
            webDialog.dismiss();
        }
    }
}