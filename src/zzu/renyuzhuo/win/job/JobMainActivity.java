package zzu.renyuzhuo.win.job;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import zzu.renyuzhuo.score.R;
import zzu.renyuzhuo.win.main.WebIndex;
import zzu.renyuzhuo.win.news.Adapter;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

@SuppressLint("HandlerLeak")
public class JobMainActivity extends Activity {

	String JOB_URL = "http://job.zzu.edu.cn/MoreJobFairs.aspx?q=&page=";
	String urlTemp = "";
	ListView lv;
	Handler mHandler;
	public static int page = 1;
	TextView tv;
	private String title;
	ArrayList<String> jobs_1;
	ArrayList<String> links_1;

	public static String html = null;
	private ArrayList<String> jobs;
	private ArrayList<String> links;
	SimpleDateFormat formatter;
	Date curDate;
	String str;

	boolean flag = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);

		Intent intent = getIntent();
		title = intent.getStringExtra("title");
		tv = (TextView) findViewById(R.id.title);
		lv = (ListView) findViewById(R.id.show_list);

		tv.setText(title);
		page = 1;
		urlTemp = JOB_URL + page;
		page++;
		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				switch (msg.what)
					{
					case 8:
						analysis();
						break;
					default:
						break;
					}
			}
		};
		html = null;
		jobs = new ArrayList<String>();
		links = new ArrayList<String>();
		new GetJobs(urlTemp, mHandler).start();

	}

	/**
	 * 解析html
	 * 
	 * @param html
	 */
	@SuppressLint("SimpleDateFormat")
	public void analysis() {
		// 解析HTML字符串返回一个Document实现
		Document doc = Jsoup.parse(html);

		Elements uls = doc.select("[class=jobfairlist]");

		Elements fonts = uls.get(0).getElementsByTag("font");
		Elements as = uls.get(0).getElementsByTag("a");
		Elements spans = uls.get(0).getElementsByTag("span");
		for (int i = 0; i < as.size(); i++) {
			String beginTime = spans.get(i).text();
			formatter = new SimpleDateFormat("[yyyy-MM-dd]");
			curDate = new Date(System.currentTimeMillis());// 获取当前时间
			str = formatter.format(curDate);
			if (str.compareTo(beginTime) > 0) {
				// 现在的时间大，招聘会已经完成
				flag = true;
			} else {
				jobs.add(fonts.get(i).text() + as.get(i).text() + beginTime);
				links.add(as.get(i).attr("href"));
			}
		}
		if (flag) {
			jobs_1 = new ArrayList<String>();
			links_1 = new ArrayList<String>();
			for (int i = jobs.size() - 1; i >= 0; i--) {
				jobs_1.add(jobs.get(i));
				links_1.add(links.get(i));
			}

			lv.setAdapter(new Adapter(this, jobs_1));
			lv.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					String url = links_1.get(position);
					Intent intent = new Intent(JobMainActivity.this,
							WebIndex.class);
					System.out.println(url);
					intent.putExtra("title", "详细信息");
					intent.putExtra("url", "http://job.zzu.edu.cn/" + url);
					startActivity(intent);
					overridePendingTransition(R.anim.in_from_right,
							R.anim.out_to_left);
				}
			});
		} else {
			urlTemp = JOB_URL + page;
			page++;
			System.out.println(page);
			new GetJobs(urlTemp, mHandler).start();
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
		return false;
	}
}
