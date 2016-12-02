package zzu.renyuzhuo.win.zzu;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import zzu.renyuzhuo.score.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("HandlerLeak")
public class DivideIntoClassActivity extends Activity {
	private static final String[] year = { "2017", "2016", "2015", "2014", "2013", "2012",
			"2018" };
	private String nj, sfzh;
	Handler mHandler;
	public static String html;
	private ArrayAdapter<String> adapter;
	Spinner spinner;
	EditText editText;
	TextView con2, con4, con6, con8, con10, con12, con14, con16;
	RelativeLayout rl;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.divide_into_class);

		spinner = (Spinner) findViewById(R.id.nj);
		editText = (EditText) findViewById(R.id.sfzh);
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, year);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				nj = year[position];
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				switch (msg.what)
					{
					case 3:
						analysis();
						break;
					default:
						break;
					}
			}
		};
		spinner.setVisibility(View.VISIBLE);

		rl = (RelativeLayout) findViewById(R.id.student_class);
		rl.setVisibility(View.INVISIBLE);
		con2 = (TextView) findViewById(R.id.con2);
		con4 = (TextView) findViewById(R.id.con4);
		con6 = (TextView) findViewById(R.id.con6);
		con8 = (TextView) findViewById(R.id.con8);
		con10 = (TextView) findViewById(R.id.con10);
		con12 = (TextView) findViewById(R.id.con12);
		con14 = (TextView) findViewById(R.id.con14);
		con16 = (TextView) findViewById(R.id.con16);

	}

	public void analysis() {
		Document doc = Jsoup.parse(html);
		if (doc.text().contains("对不起,没有检索到您需要的信息,请确定您输入的信息是否有误")) {
			Toast.makeText(this, "输入信息不对,再检查一下吧:)", Toast.LENGTH_SHORT).show();
			rl.setVisibility(View.INVISIBLE);
			closeSoft();
		} else {
			Elements tds = doc.select("td");
			con2.setText(tds.get(10).text());
			con4.setText(tds.get(11).text());
			con6.setText(tds.get(12).text());
			con8.setText(tds.get(13).text());
			con10.setText(tds.get(14).text());
			con12.setText(tds.get(15).text());
			con14.setText(tds.get(16).text());
			con16.setText(tds.get(17).text());
			rl.setVisibility(View.VISIBLE);
			closeSoft();
		}
	}

	private void closeSoft() {
		View view = getWindow().peekDecorView();
		if (view != null) {
			InputMethodManager inputmanger = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
		}
	}

	public void back(View view) {
		finish();
		overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
			overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
		}
		return super.onKeyDown(keyCode, event);
	}

	public void submit(View view) {
		sfzh = editText.getText().toString();
		new DivideIntoClass(mHandler, nj, sfzh).start();
	}
}
