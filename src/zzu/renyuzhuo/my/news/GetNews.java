package zzu.renyuzhuo.my.news;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import zzu.renyuzhuo.my.main.Util;
import android.os.Handler;

@SuppressWarnings("deprecation")
public class GetNews extends Thread {
	private String url;
	Handler mHandler = new Handler();

	public GetNews(String url, Handler mHandler) {
		this.url = url;
		this.mHandler = mHandler;
	}

	private HttpResponse mHttpResponse = null;
	String htmlText = "";
	
	@Override
	public void run() {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);

		try {
			mHttpResponse = httpClient.execute(httpGet);

			if (mHttpResponse.getStatusLine().getStatusCode() == 200) {
				htmlText = EntityUtils.toString(mHttpResponse.getEntity(),
						"utf-8");
				NewsMainActivity.html = htmlText;
				mHandler.sendEmptyMessage(0);
			}
		} catch (Exception e) {
			if (Util.DEBUG) {
				e.printStackTrace();
			}
		}

		super.run();
	}
}
