package zzu.renyuzhuo.win.main;

import zzu.renyuzhuo.score.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class SplashActivity extends Activity {
	LinearLayout linearLayout;
	int pid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.splash);

		linearLayout = (LinearLayout) findViewById(R.id.splash_pic);

		pid = (int) (Math.random() * 5);
		switch (pid)
			{
			case 0:
				linearLayout.setBackgroundResource(R.drawable.zzu1);
				break;
			case 1:
				linearLayout.setBackgroundResource(R.drawable.zzu2);
				break;
			case 2:
				linearLayout.setBackgroundResource(R.drawable.zzu3);
				break;
			case 3:
				linearLayout.setBackgroundResource(R.drawable.zzu4);
				break;
			case 4:
				linearLayout.setBackgroundResource(R.drawable.zzu5);
				break;
			default:
				break;
			}

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
