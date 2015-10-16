package zzu.renyuzhuo.my.score;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import zzu.renyuzhuo.my.main.Util;
import android.os.Handler;
import android.widget.ListView;

@SuppressWarnings("deprecation")
public class GetScore extends Thread {
	private String url;
	private HttpResponse mHttpResponse = null;

	String htmlText = "";
	int i = 0;
	ListView lv;
	Handler mHandler = new Handler();

	public GetScore(String url, Handler mHandler) {
		this.url = url;
		this.mHandler = mHandler;
	}

	@Override
	public void run() {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);

		try {
			mHttpResponse = httpClient.execute(httpGet);

			if (mHttpResponse.getStatusLine().getStatusCode() == 200) {
				htmlText = EntityUtils.toString(mHttpResponse.getEntity(),
						"gb2312");
				MainActivity.html = htmlText;
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
