package zzu.renyuzhuo.win.library;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import zzu.renyuzhuo.score.R;
import zzu.renyuzhuo.win.main.WebIndex;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;

@SuppressLint("HandlerLeak")
public class SearchBooksActivity extends Activity {

	public static String html;
	EditText bookNameEdit;
	String bookName;
	Handler handler;
	List<Book> books;
	List<String> links;
	ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.library_search_books);
		bookNameEdit = (EditText) findViewById(R.id.bookname_name_edit);
		lv = (ListView) findViewById(R.id.book_list);
	}

	public void search(View view) {
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				switch (msg.what)
					{
					case 9:
						analysis();
						break;
					default:
						break;
					}
			}

		};
		bookName = bookNameEdit.getText() + "";
		if (bookName == null || bookName.length() == 0) {
			bookNameEdit.setHint("你要找什么?告诉我好吗:)");
		} else {
			new SearchBooks(handler, bookName).start();
		}
	}

	private void analysis() {

		Document doc = Jsoup.parse(html);
		System.out.println(html);
		if (true) {

		}
		books = new ArrayList<Book>();
		links = new ArrayList<String>();
		Elements tables = doc.getElementsByTag("table");
		for (int i = 2; i < tables.size(); i += 2) {
			Element table = tables.get(i);
			Element tablein = table.getElementsByTag("table").get(0);
			Elements tds = tablein.getElementsByTag("td");

			String link = table.getElementsByTag("a").get(1).attr("href");
			Book book = new Book();
			book.setName(table.select("[class=itemtitle]").text());
			book.setWriter(tds.get(1).text());
			book.setCbs(tds.get(5).text());
			book.setYear(tds.get(7).text());
			book.setMany(tablein.getElementsByTag("a").text());
			books.add(book);
			links.add(link);
		}

		lv.setAdapter(new BooksAdapter(this, books));
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String url = links.get(position);
				Intent intent = new Intent(SearchBooksActivity.this,
						WebIndex.class);
				intent.putExtra("title", "详细信息");
				intent.putExtra("url", url);
				startActivity(intent);
				overridePendingTransition(R.anim.in_from_right,
						R.anim.out_to_left);
			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
			overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
		}
		return super.onKeyDown(keyCode, event);
	}

	public void back(View view) {
		finish();
		overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
	}
}
