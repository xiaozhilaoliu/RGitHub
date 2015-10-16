package zzu.renyuzhuo.my.news;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import zzu.renyuzhuo.score.R;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

@SuppressLint("HandlerLeak")
public class GetSpecificNewsActivity extends ActionBarActivity {
	Handler mHandler;
	public static String html = null;
	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_specific_news);

		Bundle bundle = this.getIntent().getExtras();
		String url = bundle.getString("url");
		tv = (TextView) findViewById(R.id.newsspeciufic);
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
		new GetSpecificNews(url, mHandler).start();
	}

	public void analysis() {
		Document doc = Jsoup.parse(html);
		/*
		 * Elements divs = doc.select("[class=ttt_5c2]"); news = new
		 * ArrayList<String>(); links = new ArrayList<String>(); Elements as =
		 * divs.get(0).getElementsByTag("a"); for (int i = 0; i < as.size();
		 * i++) { news.add(as.get(i).text()); links.add(as.get(i).attr("href"));
		 * } lv.setAdapter(new ArrayAdapter<String>(this,
		 * android.R.layout.simple_expandable_list_item_1, news));
		 * lv.setOnItemClickListener(new MyItemClickListener(this, links));
		 */
		Elements con = doc.select("[class=zzj_5]");
		tv.setText(con.text());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
//		getMenuInflater().inflate(R.menu.get_specific_news, menu);
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
