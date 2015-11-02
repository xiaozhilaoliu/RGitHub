package zzu.renyuzhuo.win.news;

import java.util.List;

import zzu.renyuzhuo.score.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * 新闻通知显示适配器
 * 
 * @author RENYUZHUO
 *
 */
public class Adapter extends BaseAdapter {

	private Context context;
	private LayoutInflater mInflater;
	private List<String> strs;

	public Adapter(Context context, List<String> strs) {
		super();
		this.strs = strs;
		this.context = context;
	}

	@Override
	public int getCount() {
		if (strs != null)
			return strs.size();
		return 0;
	}

	@Override
	public String getItem(int position) {
		if (strs != null)
			return strs.get(position);
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		v = mInflater.inflate(R.layout.show_list, null);
		TextView tv = (TextView) v.findViewById(R.id.news);
		tv.setText(strs.get(position));
		return v;
	}

}
