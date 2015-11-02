package zzu.renyuzhuo.win.job;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.Handler;

@SuppressWarnings("deprecation")
public class GetJobs extends Thread {
	private String url;
	Handler mHandler = new Handler();

	public GetJobs(String url, Handler mHandler) {
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
				JobMainActivity.html = htmlText;
				mHandler.sendEmptyMessage(8);
			}
		} catch (Exception e) {
		}

		super.run();
	}
}
