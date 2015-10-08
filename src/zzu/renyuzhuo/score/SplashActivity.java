package zzu.renyuzhuo.score;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.splash);

		new Handler().postDelayed(new Runnable() {

			public void run() {
				Intent intent = new Intent(SplashActivity.this,
						MainActivity.class);
				startActivity(intent);
				SplashActivity.this.finish();
			}
		}, 2500);
	}
}
