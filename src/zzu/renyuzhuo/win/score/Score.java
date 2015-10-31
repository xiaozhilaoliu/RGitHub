package zzu.renyuzhuo.win.score;

import java.util.ArrayList;
import java.util.List;

/**
 * 成绩单，教务系统每一个成绩单页面看成一个Score，每一条成绩信息看成ClassScore
 * 
 * @author RENYUZHUO
 *
 */
public class Score {
	// 第几学期
	private String xq;
	// 学期总绩点
	private String xqzjd;
	// 学期总学分
	private String xqzxf;
	// 学期平均绩点
	private String xqpjjd;
	// 成绩列表
	private List<ClassScore> list = new ArrayList<ClassScore>();

	public String getXq() {
		return xq;
	}

	public void setXq(String xq) {
		this.xq = xq;
	}

	public String getXqzjd() {
		return xqzjd;
	}

	public void setXqzjd(String xqzjd) {
		this.xqzjd = xqzjd;
	}

	public String getXqzxf() {
		return xqzxf;
	}

	public void setXqzxf(String xqzxf) {
		this.xqzxf = xqzxf;
	}

	public String getXqpjjd() {
		return xqpjjd;
	}

	public void setXqpjjd(String xqpjjd) {
		this.xqpjjd = xqpjjd;
	}

	public List<ClassScore> getList() {
		return list;
	}

	public void setList(List<ClassScore> list) {
		this.list = list;
	}

	public ClassScore addClassScore() {
		ClassScore classScore = new ClassScore();
		list.add(classScore);
		return classScore;
	}

	/**
	 * 每一条课程成绩信息
	 * 
	 * @author RENYUZHUO
	 *
	 */
	class ClassScore {
		// 课程
		private String kc;
		// 修习类别
		private String xxlb;
		// 学分
		private String xf;
		// 成绩
		private String cj;
		// 绩点
		private String jd;

		public String getKc() {
			return kc;
		}

		public void setKc(String kc) {
			this.kc = kc;
		}

		public String getXxlb() {
			return xxlb;
		}

		public void setXxlb(String xxlb) {
			this.xxlb = xxlb;
		}

		public String getXf() {
			return xf;
		}

		public void setXf(String xf) {
			this.xf = xf;
		}

		public String getCj() {
			return cj;
		}

		public void setCj(String cj) {
			this.cj = cj;
		}

		public String getJd() {
			return jd;
		}

		public void setJd(String jd) {
			this.jd = jd;
		}
	}
}
