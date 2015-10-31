package zzu.renyuzhuo.win.main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import zzu.renyuzhuo.score.R;
import zzu.renyuzhuo.score.ScoreApplication;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

@SuppressLint("HandlerLeak")
public class LoginActivity extends Activity {
	private EditText studentID;
	private EditText password;
	private String xuehao, mima;
	Handler mHandler;
	public static String html;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		String sid = ScoreApplication.getStudentId();
		if (sid != null && sid.length() > 0) {
			Toast.makeText(this, "已经登陆不需要重复登陆:)", Toast.LENGTH_SHORT).show();
			finish();
			overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
			return;
		}

		studentID = (EditText) findViewById(R.id.login_user_edit);
		password = (EditText) findViewById(R.id.login_passwd_edit);
	}

	public void login(View view) {
		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				switch (msg.what)
					{
					case 0:
						analysis();
						break;
					default:
						break;
					}
			}
		};

		xuehao = studentID.getText().toString();
		mima = password.getText().toString();

		new Login(mHandler, xuehao, mima).start();
	}

	public void analysis() {
		Document doc = Jsoup.parse(html);
		if (doc.text().contains("郑州大学学生信息")) {
			ScoreApplication.save(xuehao, mima);
			finish();
			overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
		} else {
			Toast.makeText(this, "用户名或密码错误:(", Toast.LENGTH_SHORT).show();
			View view = getWindow().peekDecorView();
			if (view != null) {
				InputMethodManager inputmanger = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
			}
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
}
