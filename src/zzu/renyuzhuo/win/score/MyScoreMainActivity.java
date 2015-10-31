package zzu.renyuzhuo.win.score;

import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import zzu.renyuzhuo.score.R;
import zzu.renyuzhuo.score.ScoreApplication;
import zzu.renyuzhuo.win.main.LoginActivity;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 我的成绩，通过一次教务系统的登陆，获取本学期的成绩信息，显示成绩
 * 并且同时在用户查看本页成绩时，后台通过url获取其他学期成绩，则在查看其他学期成绩时不需要再次从服务器获取信息
 * 在不影响用户体验时可以提高相应速度，问题是如果网速不好，可能会报出bug，未测试
 * 如果用户不查看其他学期成绩时，但是其他学期成绩已经被下载了，则可能多花流量
 * 
 * @author RENYUZHUO
 *
 */
@SuppressLint({ "HandlerLeak", "UseSparseArrays" })
public class MyScoreMainActivity extends Activity {
	public static String html;
	Handler mHandler;
	private HashMap<Integer, String> terms = new HashMap<Integer, String>();
	private HashMap<Integer, Score> scores = new HashMap<Integer, Score>();
	Adapter adapter;
	ListView lv;
	Button grade_left_btn;
	Button grade_right_btn;
	int grade;
	int showGread;
	// 学业分析报告
	String fenxibaogao;
	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myscore_main);
		if (!ScoreApplication.in()) {
			Intent intent = new Intent(MyScoreMainActivity.this,
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
						case 4:
							analysis();
							break;
						default:
							break;
						}
				}
			};
			new GetThisTermScore(mHandler).start();
			grade_left_btn = (Button) findViewById(R.id.grade_left_btn);
			grade_right_btn = (Button) findViewById(R.id.grade_right_btn);

		}

	}

	/**
	 * 解析html
	 * 
	 * @param html
	 */
	public void analysis() {
		// 解析HTML字符串返回一个Document实现
		Document doc = Jsoup.parse(html);
		Score score = new Score();
		new FormatehtmlToScore(doc, score);

		// 对链接<a>的解析
		Elements as = doc.getElementsByTag("a");
		for (int i = 1; i < as.size() - 2; i++) {
			Element a = as.get(i);
			terms.put(i - 1, a.attr("href"));
		}
		showGread = terms.size() + 1;
		grade = showGread;
		score.setXq("第" + grade + "学期");

		try {
			// 获取学业分析报告
			fenxibaogao = doc.getElementsByTag("blockquote").get(6)
					.getElementsByTag("font").get(0).text();
		} catch (Exception e) {
			// 如果没有学业分析报告则忽略
		}
		showScore(score);
		scores.put(grade, score);
		getAllGrade();

	}

	/**
	 * 后台获取其他学期成绩，缓存起来
	 */
	private void getAllGrade() {
		for (int i = 0; i < grade - 1; i++) {
			new GetScore(i + 1, terms.get(i), scores).start();
		}
	}

	/**
	 * 控制上一学期下一学期按钮是否显示
	 */
	private void dealButton() {
		if (showGread == 1) {
			grade_right_btn.setVisibility(View.VISIBLE);
			grade_left_btn.setVisibility(View.INVISIBLE);
		} else if (showGread == grade) {
			grade_right_btn.setVisibility(View.INVISIBLE);
			grade_left_btn.setVisibility(View.VISIBLE);
		} else {
			grade_right_btn.setVisibility(View.VISIBLE);
			grade_left_btn.setVisibility(View.VISIBLE);
		}
	}

	public void showScore(Score s) {
		adapter = new Adapter(s, this);
		adapter.notifyDataSetChanged();
		lv = (ListView) findViewById(R.id.lv);
		lv.setAdapter(adapter);
		tv = (TextView) findViewById(R.id.info);
		String text = s.getXq() + " 总绩点:" + s.getXqzjd() + " 总学分:"
				+ s.getXqzxf() + " 平均绩点:" + s.getXqpjjd();
		tv.setText(text);
		dealButton();
	}

	/**
	 * 下一学期成绩
	 * 
	 * @param view
	 */
	public void next(View view) {
		showGread++;
		showScore(scores.get(showGread));
	}

	/**
	 * 上一学期成绩
	 * 
	 * @param view
	 */
	public void previous(View view) {
		showGread--;
		showScore(scores.get(showGread));
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
