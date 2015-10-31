package zzu.renyuzhuo.win.common;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import zzu.renyuzhuo.score.R;
import zzu.renyuzhuo.score.ScoreApplication;
import zzu.renyuzhuo.win.main.LoginActivity;
import zzu.renyuzhuo.win.score.Adapter;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

@SuppressLint("HandlerLeak")
public class MyInformationActivity extends Activity {
	public static String html;
	Handler mHandler, pichandler;
	Adapter adapter;
	ListView lv;
	TextView tv;
	ImageView imgageView;
	Bitmap bitmap = null;
	TextView studentId;
	TextView studentName;
	TextView studentSex;
	TextView studentMation;
	TextView studentEngName;
	TextView studentBirthday;
	TextView studentHealth;
	TextView studentForeighEnglish;
	TextView studentClass;
	TextView studentFrom;
	TextView studentSenior;
	TextView studentOrigin;
	TextView studentPlace;
	TextView studentZipCode;
	TextView studentHomePhone;
	TextView studentIdCardNumber;
	TextView studentType;
	TextView studentTimeIn;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stuinfo);

		if (!ScoreApplication.in()) {
			Intent intent = new Intent(MyInformationActivity.this,
					LoginActivity.class);
			startActivity(intent);
			finish();
			overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
		} else {
			mHandler = new Handler() {
				@Override
				public void handleMessage(Message msg) {
					super.handleMessage(msg);
					switch (msg.what)
						{
						case 5:
							analysis();
							break;
						default:
							break;
						}
				}

			};
			pichandler = new Handler() {
				@Override
				public void handleMessage(Message msg) {
					super.handleMessage(msg);
					switch (msg.what)
						{
						case 6:
							imgageView.setImageBitmap(bitmap);
							break;
						default:
							break;
						}
				}

			};
			new GetStuInfo(mHandler).start();

		}
	}

	private void analysis() {
		// 解析HTML字符串返回一个Document实现
		Document doc = Jsoup.parse(html);
		Elements as = doc.getElementsByTag("img");
		String imgsrc = as.get(0).attr("src");
		// 对链接<a>的解析
		Elements tds = doc.getElementsByTag("td");
		imgageView = (ImageView) findViewById(R.id.photo);
		new GetPhoto(imgsrc, pichandler).start();

		studentId = (TextView) findViewById(R.id.studentId);
		studentName = (TextView) findViewById(R.id.studentName);
		studentSex = (TextView) findViewById(R.id.studentSex);
		studentMation = (TextView) findViewById(R.id.studentMation);
		studentEngName = (TextView) findViewById(R.id.studentEngName);
		studentBirthday = (TextView) findViewById(R.id.studentBirthday);
		studentHealth = (TextView) findViewById(R.id.studentHealth);
		studentForeighEnglish = (TextView) findViewById(R.id.studentForeighEnglish);
		studentClass = (TextView) findViewById(R.id.studentClass);
		studentFrom = (TextView) findViewById(R.id.studentFrom);
		studentSenior = (TextView) findViewById(R.id.studentSenior);
		studentOrigin = (TextView) findViewById(R.id.studentOrigin);
		studentPlace = (TextView) findViewById(R.id.studentPlace);
		studentZipCode = (TextView) findViewById(R.id.studentZipCode);
		studentHomePhone = (TextView) findViewById(R.id.studentHomePhone);
		studentIdCardNumber = (TextView) findViewById(R.id.studentIdCardNumber);
		studentType = (TextView) findViewById(R.id.studentType);
		studentTimeIn = (TextView) findViewById(R.id.studentTimeIn);

		studentId.setText(tds.get(1).text().toString());
		studentName.setText(tds.get(3).text().toString());
		studentSex.setText(tds.get(7).text().toString());
		studentMation.setText(tds.get(11).text().toString());
		studentEngName.setText(tds.get(5).text().toString());
		studentBirthday.setText(tds.get(9).text().toString());
		studentHealth.setText(tds.get(13).text().toString());
		studentForeighEnglish.setText(tds.get(15).text().toString());
		studentClass.setText(tds.get(17).text().toString());
		studentFrom.setText(tds.get(2).text().toString());
		studentSenior.setText(tds.get(4).text().toString());
		studentOrigin.setText(tds.get(6).text().toString());
		studentPlace.setText(tds.get(8).text().toString());
		studentZipCode.setText(tds.get(10).text().toString());
		studentHomePhone.setText(tds.get(12).text().toString());
		studentIdCardNumber.setText(tds.get(14).text().toString());
		studentType.setText(tds.get(16).text().toString());
		studentTimeIn.setText(tds.get(18).text().toString());
	}

	class GetPhoto extends Thread {
		String url;
		Handler handler;
		URL myFileUrl = null;

		public GetPhoto(String url, Handler handler) {
			this.handler = handler;
			this.url = url;
		}

		@Override
		public void run() {

			try {
				myFileUrl = new URL(url);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			try {
				HttpURLConnection conn = (HttpURLConnection) myFileUrl
						.openConnection();
				conn.setDoInput(true);
				conn.connect();
				InputStream is = conn.getInputStream();
				bitmap = BitmapFactory.decodeStream(is);
				is.close();
				handler.sendEmptyMessage(6);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
			overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
		}
		return super.onKeyDown(keyCode, event);
	}

	public void back(View view) {
		finish();
		overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
	}
}
