package zzu.renyuzhuo.win.news;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.Handler;

@SuppressWarnings("deprecation")
public class GetNewsList extends Thread {
	private String url;
	private HttpResponse mHttpResponse = null;
	private Handler handler;

	public GetNewsList(Handler handler, String url) {
		this.handler = handler;
		this.url = url;
	}

	@Override
	public void run() {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);

		try {
			mHttpResponse = httpClient.execute(httpGet);

			if (mHttpResponse.getStatusLine().getStatusCode() == 200) {
				NewsMainActivity.htmlText = EntityUtils.toString(
						mHttpResponse.getEntity(), "utf-8");
				handler.sendEmptyMessage(7);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		super.run();
	}

}