package zzu.renyuzhuo.my.job;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import zzu.renyuzhuo.score.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

@SuppressLint("HandlerLeak")
public class JobMainActivity extends ActionBarActivity {

	String JOB_URL = "http://job.zzu.edu.cn/MoreJobFairs.aspx?q=&page=";
	String urlTemp = "";
	ListView lv;
	Handler mHandler;
	public static int page = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_job_main);
		lv = (ListView) findViewById(R.id.jobs_listview);
		urlTemp = JOB_URL + page;
		page++;
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
		new GetJobs(urlTemp, mHandler).start();

	}

	public static String html = null;
	private ArrayList<String> jobs = new ArrayList<String>(),
			links = new ArrayList<String>();
	SimpleDateFormat formatter;
	Date curDate;
	String str;

	boolean flag = false;

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
			ArrayList<String> jobs_1 = new ArrayList<String>();
			ArrayList<String> links_1 = new ArrayList<String>();
			for (int i = jobs.size() - 1; i >= 0; i--) {
				jobs_1.add(jobs.get(i));
				links_1.add(links.get(i));

			}

			lv.setAdapter(new ArrayAdapter<String>(this, R.layout.news_list,
					jobs_1));
			lv.setOnItemClickListener(new MyItemClickListener(this, links_1));
		} else {
			urlTemp = JOB_URL + page;
			page++;
			System.out.println(page);
			new GetJobs(urlTemp, mHandler).start();
		}
	}

	/**
	 * 对选项的点击做出应答动作
	 * 
	 * @author renyuzhuo
	 *
	 */
	private static class MyItemClickListener implements OnItemClickListener {
		private ArrayList<String> links;
		Context context;

		public MyItemClickListener(Context context, ArrayList<String> links) {
			this.context = context;
			this.links = links;
		}

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			String href = links.get(position);
			Intent intent = new Intent(context, GetSpecificJobsActivity.class);
			intent.putExtra("url", href);
			context.startActivity(intent);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.job_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
