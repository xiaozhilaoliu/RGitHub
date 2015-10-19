package zzu.renyuzhuo.my.news;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import zzu.renyuzhuo.my.job.JobMainActivity;
import zzu.renyuzhuo.my.main.AboutActivity;
import zzu.renyuzhuo.my.score.MainActivity;
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
import android.widget.Toast;

@SuppressLint("HandlerLeak")
public class NewsMainActivity extends ActionBarActivity {

	public static String html = null;
	private ArrayList<String> news, links;
	private static final String url = "http://www.renyuzhuo.win/";
	Handler mHandler;

	ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news_main);

		lv = (ListView) findViewById(R.id.news_listview);

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
		new GetNews(url, mHandler).start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.news_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		Intent intent;
		switch (id)
			{
			case R.id.zzu_score:
				intent = new Intent(NewsMainActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
				return true;
			case R.id.action_about: {
				intent = new Intent(NewsMainActivity.this, AboutActivity.class);
				startActivity(intent);
				// finish();
				return true;
			}
			case R.id.news_shuoming: {
				Toast.makeText(this, "新闻来自郑大新闻网首页,版权归郑大所有!", Toast.LENGTH_LONG).show();
				return true;
			}
			case R.id.job_main: {
				intent = new Intent(NewsMainActivity.this, JobMainActivity.class);
				startActivity(intent);
				return true;
			}
			default:
				break;
			}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * 解析html
	 * 
	 * @param html
	 */
	public void analysis() {
		// 解析HTML字符串返回一个Document实现
		Document doc = Jsoup.parse(html);

		Elements divs = doc.select("[class=ttt_5c2]");
		news = new ArrayList<String>();
		links = new ArrayList<String>();
		Elements as = divs.get(0).getElementsByTag("a");
		for (int i = 0; i < as.size(); i++) {
			news.add(as.get(i).text());
			links.add(as.get(i).attr("href"));
		}
		lv.setAdapter(new ArrayAdapter<String>(this, R.layout.news_list, news));
		lv.setOnItemClickListener(new MyItemClickListener(this, links));
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
			Intent intent = new Intent(context, GetSpecificNewsActivity.class);
			intent.putExtra("url", href);
			context.startActivity(intent);
		}

	}
}
