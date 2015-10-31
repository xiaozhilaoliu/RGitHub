package zzu.renyuzhuo.win.score;

import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * 通过url获取成绩信息 每次查询成绩后成绩单下方会有几个链接显示的是其他学期的成绩，通过该url可以获取成绩信息
 * 
 * @author RENYUZHUO
 *
 */
@SuppressWarnings("deprecation")
public class GetScore extends Thread {
	private String url;
	private HttpResponse mHttpResponse = null;
	String htmlText = "";
	int grade;
	HashMap<Integer, Score> scores;

	public GetScore(int grade, String url, HashMap<Integer, Score> scores) {
		this.grade = grade;
		this.url = url;
		this.scores = scores;
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
				analysis();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		super.run();
	}

	/**
	 * 页面解析处理
	 */
	public void analysis() {
		Document doc = Jsoup.parse(htmlText);
		Score score = new Score();
		new FormatehtmlToScore(doc, score);

		score.setXq("第" + grade + "学期");
		scores.put(grade, score);
	}
}
