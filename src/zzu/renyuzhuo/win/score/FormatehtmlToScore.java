package zzu.renyuzhuo.win.score;

import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import zzu.renyuzhuo.score.ScoreApplication;
import zzu.renyuzhuo.win.score.Score.ClassScore;

/**
 * 将传入的页面信息格式化为成绩Score，即界面的解析
 * 
 * @author RENYUZHUO
 *
 */
public class FormatehtmlToScore {
	public FormatehtmlToScore(Document doc, Score score) {
		try {
			// 获取学期总绩点、 学期总学分、学期平均绩点等信息
			Elements ps = doc.getElementsByTag("p");
			Element p = ps.get(2);
			String temp = p.text().toString();
			String[] cj = temp.split("：");
			score.setXqzjd(cj[1].trim().substring(0, 4).toString());
			score.setXqzxf(cj[2].trim().substring(0, 4).toString());
			score.setXqpjjd(cj[3].trim());

			// 获取成绩信息
			Element link = doc.select("table").first();
			new ArrayList<String[]>();
			Elements trs = link.getElementsByTag("tr");

			// 默认忽略表头，从1考试计数
			for (int i = 1; i < trs.size(); i++) {
				Element tr = trs.get(i);
				Elements tds = tr.getElementsByTag("td");
				try {
					ClassScore classScore = score.addClassScore();
					classScore.setKc(tds.get(0).text());
					classScore.setXxlb(tds.get(1).text());
					classScore.setXf(tds.get(2).text());
					classScore.setCj(tds.get(3).text());
					classScore.setJd(tds.get(4).text());
				} catch (Exception e) {
					ScoreApplication.del();
					return;
				}
			}
		} catch (Exception e) {

		}
	}
}
