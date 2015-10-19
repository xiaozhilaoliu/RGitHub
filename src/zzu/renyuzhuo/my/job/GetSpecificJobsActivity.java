package zzu.renyuzhuo.my.job;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import zzu.renyuzhuo.my.news.GetSpecificNews;
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
public class GetSpecificJobsActivity extends ActionBarActivity {
	Handler mHandler;
	public static String html = null;
	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_specific_jobs);

		Bundle bundle = this.getIntent().getExtras();
		String url = "http://job.zzu.edu.cn/" + bundle.getString("url");
		System.out.println(url);
		tv = (TextView) findViewById(R.id.jobsspeciufic);
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
		String title = doc.select("h1").get(1).text();
		String h2 = doc.select("h2").get(0).text()
				+ doc.select("h2").get(1).text();
		String cons = doc.select("div").get(15).text()
				+ doc.select("div").get(16).text();
		;

		tv.setText(title + "\n\n" + h2 + "\n\n" + cons);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.get_specific_jobs, menu);
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
