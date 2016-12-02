package zzu.renyuzhuo.win.zzu;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.widget.ListView;

@SuppressWarnings("deprecation")
@SuppressLint("HandlerLeak")
public class DivideIntoClass extends Thread {

	private static final String NEWSTUURL = "http://jw.zzu.edu.cn/scripts/newstu.dll/cx";

	private HttpResponse mHttpResponse = null;
	Handler mHandler = new Handler();
	String nj;
	String sfzh;

	public static String htmlText = "";
	int i = 0;
	ListView lv;

	public DivideIntoClass(Handler mHandler, String nj, String sfzh) {
		this.mHandler = mHandler;
		this.nj = nj;
		this.sfzh = sfzh;
	}

	@Override
	public void run() {
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(NEWSTUURL);
		List<NameValuePair> paramList = new ArrayList<NameValuePair>();

		// 设置登陆信息
		BasicNameValuePair xuehaoP = new BasicNameValuePair("nj", nj);
		BasicNameValuePair mimaP = new BasicNameValuePair("sfzh", sfzh);
		// 设置发送数据
		paramList.add(xuehaoP);
		paramList.add(mimaP);
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(paramList, HTTP.UTF_8));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		try {
			mHttpResponse = httpClient.execute(httpPost);

			if (mHttpResponse.getStatusLine().getStatusCode() == 200) {
				htmlText = EntityUtils.toString(mHttpResponse.getEntity(),
						"gb2312");
				DivideIntoClassActivity.html = htmlText;
				mHandler.sendEmptyMessage(3);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.run();
	}
}
