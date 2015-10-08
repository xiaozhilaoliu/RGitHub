package zzu.renyuzhuo.score;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class LoginActivity extends ActionBarActivity {

	private static SharedPreferences settings;

	Button b;
	EditText xuehaoE;
	EditText mimaE;

	private static final String[] m = { "2016", "2015", "2014", "2013", "2012",
			"2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004",
			"2003", "2002", "2001", "2000", "2009", "2008", "2007", "2006",
			"2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998",
			"1997" };
	private Spinner spinner;
	private ArrayAdapter<String> adapter;
	String nianji;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);

		spinner = (Spinner) findViewById(R.id.nianji_text);
		// 将可选内容与ArrayAdapter连接起来
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, m);

		// 设置下拉列表的风格
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// 将adapter 添加到spinner中
		spinner.setAdapter(adapter);

		// 添加事件Spinner事件监听
		spinner.setOnItemSelectedListener(new SpinnerSelectedListener());

		// 设置默认值
		spinner.setVisibility(View.VISIBLE);

		settings = getSharedPreferences("user.cxf", 0);
		xuehaoE = (EditText) findViewById(R.id.xuehao_text);
		mimaE = (EditText) findViewById(R.id.mima_text);

		b = (Button) findViewById(R.id.submit);

		b.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String xuehao = xuehaoE.getText().toString();
				String mima = mimaE.getText().toString();
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

	// 使用数组形式操作
	class SpinnerSelectedListener implements OnItemSelectedListener {
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			nianji = m[arg2];
		}

		public void onNothingSelected(AdapterView<?> arg0) {
		}
	}
}
