package zzu.renyuzhuo.my.score;

import zzu.renyuzhuo.my.main.AboutActivity;
import zzu.renyuzhuo.score.R;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends ActionBarActivity {

	private static SharedPreferences settings;

	Button b;
	EditText xuehaoE;
	EditText mimaE;

	String nianji;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);

		settings = getSharedPreferences("user.cxf", 0);
		xuehaoE = (EditText) findViewById(R.id.xuehao_text);
		mimaE = (EditText) findViewById(R.id.mima_text);

		b = (Button) findViewById(R.id.submit);

		b.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String xuehao = xuehaoE.getText().toString();
				String mima = mimaE.getText().toString();
				nianji = xuehao.substring(0, 4);
				System.out.println(nianji);
				SharedPreferences.Editor editor = settings.edit();
				editor.putString("nianji", nianji);
				editor.putString("xuehao", xuehao);
				editor.putString("mima", mima);
				editor.commit();

				Intent intent = new Intent(LoginActivity.this,
						MainActivity.class);
				startActivity(intent);
				finish();
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.setting, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_about) {
			Intent intent = new Intent(this, AboutActivity.class);
			startActivity(intent);

		}
		return super.onOptionsItemSelected(item);
	}

}
