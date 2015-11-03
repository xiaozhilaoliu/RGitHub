package zzu.renyuzhuo.win.library;

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
 * 
 * @author RENYUZHUO
 *
 */
public class BooksAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater mInflater;
	private List<Book> books;

	public BooksAdapter(Context context, List<Book> books) {
		super();
		this.books = books;
		this.context = context;
	}

	@Override
	public int getCount() {
		if (books != null)
			return books.size();
		return 0;
	}

	@Override
	public Book getItem(int position) {
		if (books != null)
			return books.get(position);
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
		v = mInflater.inflate(R.layout.show_book_list, null);
		Book book = books.get(position);
		Holder holder = new Holder();
		holder.name = (TextView) v.findViewById(R.id.book_name);
		holder.type = (TextView) v.findViewById(R.id.book_type);
		holder.year = (TextView) v.findViewById(R.id.book_year);
		holder.cbs = (TextView) v.findViewById(R.id.book_cbs);
		holder.writer = (TextView) v.findViewById(R.id.book_writer);

		holder.name.setText(book.getName());
		holder.type.setText(book.getType());
		holder.year.setText(book.getYear());
		holder.cbs.setText(book.getCbs());
		holder.writer.setText(book.getWriter());

		return v;
	}

	class Holder {
		TextView name;
		TextView type;
		TextView year;
		TextView cbs;
		TextView writer;

	}

}
