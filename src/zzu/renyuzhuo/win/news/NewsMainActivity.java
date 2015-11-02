package zzu.renyuzhuo.win.news;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import zzu.renyuzhuo.score.R;
import zzu.renyuzhuo.win.main.WebIndex;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

@SuppressLint({ "SetJavaScriptEnabled", "HandlerLeak" })
public class NewsMainActivity extends Activity {
	public static String htmlText;
	private String url;
	private String title;
	TextView tv;
	WebView webView;
	Handler mHandler;
	ListView news_list;

	List<String> news;
	List<String> links;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);

		Intent intent = getIntent();
		url = intent.getStringExtra("url");
		title = intent.getStringExtra("title");

		tv = (TextView) findViewById(R.id.title);
		news_list = (ListView) findViewById(R.id.show_list);
		tv.setText(title);
		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				switch (msg.what)
					{
					case 7:
						analysis();
						break;
					default:
						break;
					}
			}

		};
		new GetNewsList(mHandler, url).start();
	}

	private void analysis() {
		news = new ArrayList<String>();
		links = new ArrayList<String>();

		Document doc = Jsoup.parse(htmlText);

		Elements as = doc.getElementsByTag("a");

		for (int i = 3; i < as.size() - 2; i++) {
			/*
			 * String s = ; s.substring(0, s.length() > 20 ? 20 : s.length())
			 */
			news.add(as.get(i).text());
			links.add(as.get(i).attr("href"));
		}
		news_list.setAdapter(new Adapter(this, news));
		news_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String url = links.get(position);
				Intent intent = new Intent(NewsMainActivity.this,
						WebIndex.class);
				intent.putExtra("title", "详细信息");
				intent.putExtra("url", url);
				startActivity(intent);
				overridePendingTransition(R.anim.in_from_right,
						R.anim.out_to_left);
			}
		});
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