package zzu.renyuzhuo.my.score;

import java.util.ArrayList;

import zzu.renyuzhuo.score.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Adapter extends BaseAdapter {

	ArrayList<String[]> con;
	private Context context;
	private LayoutInflater mInflater;

	public Adapter(ArrayList<String[]> con, Context ctx) {
		super();
		this.con = con;
		this.context = ctx;
	}

	@Override
	public int getCount() {
		if (con != null)
			return con.size();
		return 0;
	}

	@Override
	public String[] getItem(int position) {
		if (con != null)
			return con.get(position);
		return null;
	}

	@Override
	public long getItemId(int position) {
		if (con != null)
			return con.get(position).hashCode();
		return 0;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		Hold hold = null;
		if (v == null) {
			hold = new Hold();
			mInflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = mInflater.inflate(R.layout.zzu_score, null);
			hold.classText = (TextView) v.findViewById(R.id.classText);
			hold.typeText = (TextView) v.findViewById(R.id.typeText);
			hold.creditText = (TextView) v.findViewById(R.id.creditText);
			hold.scoreText = (TextView) v.findViewById(R.id.scoreText);
			hold.gpaText = (TextView) v.findViewById(R.id.gpaText);

			String[] clas = getItem(position);

			hold.classText.setText(clas[0]);
			hold.typeText.setText(clas[1]);
			hold.creditText.setText(clas[2]);
			hold.scoreText.setText(clas[3]);
			hold.gpaText.setText(clas[4]);

			v.setTag(hold);
		} else {
			hold = (Hold) convertView.getTag();
		}

		return v;
	}

	public final class Hold {
		public TextView classText;
		public TextView typeText;
		public TextView creditText;
		public TextView scoreText;
		public TextView gpaText;
	}
}
