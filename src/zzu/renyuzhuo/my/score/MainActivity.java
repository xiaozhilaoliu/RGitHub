package zzu.renyuzhuo.my.score;

import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import zzu.renyuzhuo.my.main.AboutActivity;
import zzu.renyuzhuo.my.news.NewsMainActivity;
import zzu.renyuzhuo.score.R;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("HandlerLeak")
public class MainActivity extends ActionBarActivity {

	public static String html = null;
	private HashMap<String, String> terms = new HashMap<String, String>();
	private ArrayList<String[]> con;
	private Adapter adapter;
	ListView lv;
	Handler mHandler;
	private TextView pv;

	private static SharedPreferences settings;

	public static String nianji = "";
	public static String xuehao = "";
	public static String mima = "";

	private TextView fenxi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		settings = getSharedPreferences("user.cxf", 0);
		nianji = settings.getString("nianji", "");
		xuehao = settings.getString("xuehao", "");
		mima = settings.getString("mima", "");

		if (nianji == null || nianji.length() == 0) {
			Toast.makeText(this, "请先登录:)", Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(this, LoginActivity.class);
			startActivity(intent);
			finish();
			return;
		}

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
		new Login(mHandler).start();
	}

	/**
	 * 解析html
	 * 
	 * @param html
	 */
	public void analysis() {
		// 解析HTML字符串返回一个Document实现
		Document doc = Jsoup.parse(html);

		// 获取学期总绩点、 学期总学分、学期平均绩点等信息
		Elements ps = doc.getElementsByTag("p");
		Element p = ps.get(2);
		pv = (TextView) findViewById(R.id.xqzjd);
		pv.setText(p.text());

		// 对链接<a>的解析
		Elements as = doc.getElementsByTag("a");
		for (int i = 1; i < as.size() - 2; i++) {
			Element a = as.get(i);
			terms.put(a.text(), a.attr("href"));
		}

		// 获取成绩信息
		Element link = doc.select("table").first();
		con = new ArrayList<String[]>();
		Elements trs = link.getElementsByTag("tr");

		// 默认忽略表头，从1考试计数
		for (int i = 1; i < trs.size(); i++) {
			Element tr = trs.get(i);
			Elements tds = tr.getElementsByTag("td");
			try {
				String[] co = { tds.get(0).text(), tds.get(1).text(),
						tds.get(2).text(), tds.get(3).text(),
						tds.get(4).text(), };
				con.add(co);
			} catch (Exception e) {
				System.out.println("RRR::用户信息错误::年级::" + nianji + "学号::"
						+ xuehao + "密码::" + mima);
				Toast.makeText(this, "用户信息错误，请重试！", Toast.LENGTH_SHORT).show();
				logout();
				Intent intent = new Intent(this, LoginActivity.class);
				startActivity(intent);
				finish();
				return;
			}
		}

		// 获取学业分析报告
		String fenxibaogao = doc.getElementsByTag("blockquote").get(6)
				.getElementsByTag("font").get(0).text();
		fenxi = (TextView) findViewById(R.id.fenxibaogao);
		fenxi.setText(fenxibaogao);

		adapter = new Adapter(con, this);
		adapter.notifyDataSetChanged();
		lv = (ListView) findViewById(R.id.lv);
		lv.setAdapter(adapter);
	}

	private void scoreGet(String url) {
		if (url == null) {
			Toast.makeText(this, "操作错误，请重试!", Toast.LENGTH_SHORT).show();
			return;
		}
		new GetScore(url, mHandler).start();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id)
			{
			case R.id.action_about: {
				Intent intent = new Intent(this, AboutActivity.class);
				startActivity(intent);
				break;
			}
			case R.id.zzu_term_1: {
				scoreGet(terms.get("第1学期"));
				break;
			}
			case R.id.zzu_term_2: {
				scoreGet(terms.get("第2学期"));
				break;
			}
			case R.id.zzu_term_3: {
				scoreGet(terms.get("第3学期"));
				break;
			}
			case R.id.zzu_term_4: {
				scoreGet(terms.get("第4学期"));
				break;
			}
			case R.id.zzu_term_5: {
				scoreGet(terms.get("第5学期"));
				break;
			}
			case R.id.zzu_term_6: {
				scoreGet(terms.get("第6学期"));
				break;
			}
			case R.id.zzu_term_7: {
				scoreGet(terms.get("第7学期"));
				break;
			}
			case R.id.zzu_term_8: {
				scoreGet(terms.get("第8学期"));
				break;
			}
			case R.id.action_logout: {
				logout();
				login();
				finish();
				break;
			}
			case R.id.news: {
				Intent intent = new Intent(MainActivity.this,
						NewsMainActivity.class);
				startActivity(intent);
				MainActivity.this.finish();
				break;
			}
			default:
				break;
			}
		return super.onOptionsItemSelected(item);
	}

	private void login() {
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
		finish();
	}

	private void logout() {
		SharedPreferences.Editor editor = settings.edit();
		editor.putString("nianji", "");
		editor.putString("xuehao", "");
		editor.putString("mima", "");
		editor.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
