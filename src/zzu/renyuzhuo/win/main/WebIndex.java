package zzu.renyuzhuo.win.main;

import zzu.renyuzhuo.score.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

@SuppressLint("SetJavaScriptEnabled")
public class WebIndex extends Activity {
	private String url = "http://www.zzu.edu.cn/";
	private String title = "郑州大学";
	TextView tv;
	WebView webView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web_index);

		Intent intent = getIntent();
		url = intent.getStringExtra("url");
		title = intent.getStringExtra("title");

		tv = (TextView) findViewById(R.id.title);
		webView = (WebView) findViewById(R.id.zzu_index);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setBuiltInZoomControls(true);
		webView.setWebViewClient(new WebViewClient() {
			public boolean shouldOverrideUrlLoading(WebView view, String strUrl) {
				view.loadUrl(strUrl);
				return false;
			}
		});
		tv.setText(title);
		webView.loadUrl(url);
	}

	public void back(View view) {
		if (webView.canGoBack()) {
			webView.goBack();
		} else {
			finish();
			overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (webView.canGoBack()) {
				System.out.println(webView.getUrl());
				webView.goBack();
			} else {
				finish();
				overridePendingTransition(R.anim.in_from_left,
						R.anim.out_to_right);
			}
		}
		return false;
	}
}