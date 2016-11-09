package cn.renyuzhuo.rgithubandroidsdk.net.trending;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.List;

import cn.renyuzhuo.rgithubandroidsdk.Browser.MyWebViewClient;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.trending.TrendingBean;
import cn.renyuzhuo.rgithubandroidsdk.net.Base.CodeHub.TrendingBase;
import cn.renyuzhuo.rgithubandroidsdk.net.result.MySubscriber;
import cn.renyuzhuo.rgithubandroidsdk.service.trending.TrendingService;
import cn.renyuzhuo.rlog.rlog;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by renyuzhuo on 16-11-7.
 */
public class TrendingClient {
    private static TrendingService trendingService = TrendingBase.getInstance().build().create(TrendingService.class);

    public static void getTrending(final TrendingClientListener trendingClientListener, String since) {
        trendingService.getTrending(since)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber<List<TrendingBean>>() {
                    @Override
                    public void onNext(List<TrendingBean> trendingBeanList) {
                        if (trendingClientListener != null) {
                            trendingClientListener.onGetTrendingSuccess(trendingBeanList);
                        }
                    }
                });
    }

    @SuppressLint("JavascriptInterface")
    public static void getTrending(Context context, final TrendingClientListener trendingClientListener, String since, String language) {

        WebView webView = new WebView(context);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new InJavaScriptLocalObj(), "local_obj");
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Log.d("WebView", "onPageStarted");
                super.onPageStarted(view, url, favicon);
            }

            public void onPageFinished(WebView view, String url) {
                Log.d("WebView", "onPageFinished ");
                view.loadUrl("javascript:window.local_obj.showSource('<head>'+" +
                        "document.getElementsByTagName('html')[0].innerHTML+'</head>');");
                super.onPageFinished(view, url);
            }
        });
        webView.loadUrl("http://www.baidu.com");

        trendingService.getTrending(since, language)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber<List<TrendingBean>>() {
                    @Override
                    public void onNext(List<TrendingBean> trendingBeanList) {
                        if (trendingClientListener != null) {
                            trendingClientListener.onGetTrendingSuccess(trendingBeanList);
                        }
                    }
                });
    }

    static class InJavaScriptLocalObj {
        public void showSource(String html) {
            rlog.d(html);
        }
    }

}
