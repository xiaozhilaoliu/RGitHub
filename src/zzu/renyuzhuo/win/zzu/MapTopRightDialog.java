package zzu.renyuzhuo.win.zzu;

import zzu.renyuzhuo.score.R;
import zzu.renyuzhuo.win.main.BaiduMapActivity;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MapTopRightDialog extends Activity {
	TextView open_with_browser;
	TextView open_baidu_map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_top_right_dialog);

		open_with_browser = (TextView) findViewById(R.id.open_with_browser);
		open_baidu_map = (TextView) findViewById(R.id.open_baidu_map);
		open_with_browser.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Uri uri = Uri.parse(ZzuMapActivity.MAPURL);
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
			}
		});
		open_baidu_map.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MapTopRightDialog.this,
						BaiduMapActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		finish();
		return true;
	}
}
