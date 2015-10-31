package zzu.renyuzhuo.win.zzu;

import zzu.renyuzhuo.score.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

@SuppressLint("SetJavaScriptEnabled")
public class ZzuMapActivity extends Activity {
	public static final String MAPURL = "http://www.zzu.edu.cn/zzumap.htm";
	// private ZoomImageView zoomImg;
	WebView wv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_zzu_map);
		wv = (WebView) findViewById(R.id.web_zzu_map);
		wv.getSettings().setJavaScriptEnabled(true);
		wv.getSettings().setBuiltInZoomControls(true);
		wv.setWebViewClient(new WebViewClient() {
			public boolean shouldOverrideUrlLoading(WebView view, String strUrl) {
				view.loadUrl(strUrl);
				return false;
			}
		});
		wv.loadUrl(MAPURL);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
			overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
		}
		return super.onKeyDown(keyCode, event);
	}

	public void showMenu(View view) {
		Intent intent = new Intent(ZzuMapActivity.this, MapTopRightDialog.class);
		startActivityForResult(intent, 1);
	}

	public void back(View view) {
		finish();
		overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
	}
}
