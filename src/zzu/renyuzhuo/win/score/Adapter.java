package zzu.renyuzhuo.win.score;

import zzu.renyuzhuo.score.R;
import zzu.renyuzhuo.win.score.Score.ClassScore;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * 成绩显示适配器，将成绩信息一条一条显示出来 不使用缓存，防止成绩信息显示顺序错乱或者是其他错误
 * 
 * @author RENYUZHUO
 *
 */
public class Adapter extends BaseAdapter {

	Score score;
	private Context context;
	private LayoutInflater mInflater;

	public Adapter(Score score, Context ctx) {
		super();
		this.score = score;
		this.context = ctx;
	}

	@Override
	public int getCount() {
		if (score != null)
			return score.getList().size();
		return 0;
	}

	@Override
	public ClassScore getItem(int position) {
		if (score != null)
			return score.getList().get(position);
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
		Hold hold = null;
		hold = new Hold();
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		v = mInflater.inflate(R.layout.score_one, null);
		hold.classText = (TextView) v.findViewById(R.id.classText);
		hold.typeText = (TextView) v.findViewById(R.id.typeText);
		hold.creditText = (TextView) v.findViewById(R.id.creditText);
		hold.scoreText = (TextView) v.findViewById(R.id.scoreText);
		hold.gpaText = (TextView) v.findViewById(R.id.gpaText);

		ClassScore clas = getItem(position);

		hold.classText.setText(clas.getKc());
		hold.typeText.setText(clas.getXxlb());
		hold.creditText.setText(clas.getXf());
		hold.scoreText.setText(clas.getCj());
		hold.gpaText.setText(clas.getJd());

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
