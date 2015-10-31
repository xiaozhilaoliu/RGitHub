package zzu.renyuzhuo.win.main;

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
public class Login extends Thread {

	private static final String XSXXURL = "http://jw.zzu.edu.cn/scripts/stuinfo.dll/check";

	private HttpResponse mHttpResponse = null;
	Handler mHandler = new Handler();
	String nianji;
	String xuehao;
	String mima;

	public static String htmlText = "";
	int i = 0;
	ListView lv;

	public Login(Handler mHandler, String xuehao, String mima) {
		this.mHandler = mHandler;
		this.xuehao = xuehao;
		this.mima = mima;

		nianji = xuehao.substring(0, 4);
	}

	@Override
	public void run() {
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(XSXXURL);
		List<NameValuePair> paramList = new ArrayList<NameValuePair>();

		// 设置登陆信息
		BasicNameValuePair ninajiP = new BasicNameValuePair("nianji", nianji);
		BasicNameValuePair xuehaoP = new BasicNameValuePair("xuehao", xuehao);
		BasicNameValuePair mimaP = new BasicNameValuePair("mima", mima);
		// 设置发送数据
		paramList.add(ninajiP);
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
				LoginActivity.html = htmlText;
				mHandler.sendEmptyMessage(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.run();
	}

}
