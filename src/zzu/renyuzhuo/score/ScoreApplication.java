package zzu.renyuzhuo.score;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class ScoreApplication extends Application {
	private static final String PREFS_NAME = "Score";
	private static SharedPreferences settings;
	private static Editor editor;
	private static String studentId = "";
	private static String password = "";
	private static String grade = "";

	@Override
	public void onCreate() {
		super.onCreate();
		settings = getSharedPreferences(PREFS_NAME, 0);
		editor = settings.edit();
		grade = settings.getString("Grade", "");
		studentId = settings.getString("StudentId", "");
		password = settings.getString("Password", "");
		dealStudentIdToGrade();
	}

	private static void dealStudentIdToGrade() {
		if (studentId != null && studentId.length() != 0) {
			grade = studentId.substring(0, 4);
		}
	}

	public static String getStudentId() {
		return studentId;
	}

	public static void setStudentId(String studentId) {
		ScoreApplication.studentId = studentId;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		ScoreApplication.password = password;
	}

	public static String getGrade() {
		return grade;
	}

	public static void setGrade(String grade) {
		ScoreApplication.grade = grade;
	}

	public static boolean save(String studentId, String password) {
		ScoreApplication.studentId = studentId;
		ScoreApplication.password = password;
		dealStudentIdToGrade();

		editor.putString("Grade", grade);
		editor.putString("StudentId", studentId);
		editor.putString("Password", password);
		return editor.commit();
	}

	public static boolean del() {
		ScoreApplication.grade = "";
		ScoreApplication.studentId = "";
		ScoreApplication.password = "";

		editor.putString("Grade", grade);
		editor.putString("StudentId", studentId);
		editor.putString("Password", password);
		return editor.commit();
	}

	/**
	 * 用户是否已经登陆
	 * @return 登陆返回true，未登录返回false
	 */
	public static boolean in() {
		if (ScoreApplication.studentId == null
				|| ScoreApplication.studentId.length() == 0) {
			return false;
		}
		return true;
	}
}
