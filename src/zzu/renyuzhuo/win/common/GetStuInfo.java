package zzu.renyuzhuo.win.common;

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

import zzu.renyuzhuo.score.ScoreApplication;
import android.os.Handler;

@SuppressWarnings("deprecation")
public class GetStuInfo extends Thread {
	public static final String URL_ZZU_JW_STU_INFO = "http://jw.zzu.edu.cn/scripts/stuinfo.dll/check";
	Handler handler;
	private HttpResponse mHttpResponse = null;
	public static String htmlText = "";

	public GetStuInfo(Handler handler) {
		this.handler = handler;
	}

	@Override
	public void run() {
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(URL_ZZU_JW_STU_INFO);
		List<NameValuePair> paramList = new ArrayList<NameValuePair>();

		// 设置登陆信息
		BasicNameValuePair ninajiP = new BasicNameValuePair("nianji",
				ScoreApplication.getGrade());
		BasicNameValuePair xuehaoP = new BasicNameValuePair("xuehao",
				ScoreApplication.getStudentId());
		BasicNameValuePair mimaP = new BasicNameValuePair("mima",
				ScoreApplication.getPassword());
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
				MyInformationActivity.html = htmlText;
				handler.sendEmptyMessage(5);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.run();
	}
}
