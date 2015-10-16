package zzu.renyuzhuo.my.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class Util {
	public static final boolean DEBUG = false;
	public static final String URL_ZZU_JW_SEARCH_SCORE = "http://jw.zzu.edu.cn/scripts/qscore.dll/search";

	private static List<String> lists = new ArrayList<String>();
	private static Intent intent;

	/**
	 * 对字符串与类的键值对，进行显示并在点击时跳转到目标类中
	 * 
	 * @param context
	 *            上下文,一般为this
	 * @param listView
	 * @param map
	 */
	public static void showLists(final Context context, ListView listView,
			final Map<String, String> map) {

		lists = new ArrayList<String>();

		// 获取表项的各个值
		for (String s : map.keySet()) {
			lists.add(s);
		}

		listView.setAdapter(new ArrayAdapter<String>(context,
				android.R.layout.simple_expandable_list_item_1, lists));

		listView.setOnItemClickListener(new MyItemClickListener(context, map,
				lists));
	}

	/**
	 * 对选项的点击做出应答动作
	 * 
	 * @author renyuzhuo
	 *
	 */
	private static class MyItemClickListener implements OnItemClickListener {

		List<String> lists = new ArrayList<String>();
		Map<String, String> map;
		Context context;

		public MyItemClickListener(Context context, Map<String, String> map,
				List<String> lists) {
			this.context = context;
			this.lists = lists;
			this.map = map;
		}

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			try {
				// 跳转界面
				intent = new Intent(context, Class.forName(map.get(lists
						.get(position))));
				context.startActivity(intent);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

	}
}
