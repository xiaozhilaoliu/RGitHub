package zzu.renyuzhuo.win.library;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.Handler;

@SuppressWarnings("deprecation")
public class SearchBooks extends Thread {
	Handler handler;
	public static final String URL_LIBRARY_SEARCH = "http://202.197.191.171:8991/F?func=find-b&request=";
	private HttpResponse mHttpResponse = null;
	public static String htmlText = "";
	String name;

	public SearchBooks(Handler handler, String name) {
		this.handler = handler;
		this.name = name;
	}

	@Override
	public void run() {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(URL_LIBRARY_SEARCH + name
				+ "&pds_handle=GUEST");
		try {
			mHttpResponse = httpClient.execute(httpGet);

			if (mHttpResponse.getStatusLine().getStatusCode() == 200) {
				htmlText = EntityUtils.toString(mHttpResponse.getEntity(),
						"UTF-8");
				SearchBooksActivity.html = htmlText;
				handler.sendEmptyMessage(9);
			}
		} catch (Exception e1) {
		}

		super.run();
	}
}
