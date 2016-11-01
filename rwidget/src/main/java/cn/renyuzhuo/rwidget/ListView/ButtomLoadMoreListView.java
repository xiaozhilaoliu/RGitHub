package cn.renyuzhuo.rwidget.ListView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by renyuzhuo on 16-11-1.
 */
public class ButtomLoadMoreListView extends ListView implements LoadMore {

    LoadMore loadMore;

    public ButtomLoadMoreListView(Context context) {
        super(context);
    }

    public ButtomLoadMoreListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ButtomLoadMoreListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
    }

    public void setLoadMore(LoadMore loadMore) {
        this.loadMore = loadMore;
    }

    @Override
    public void loadMore() {
        loadMore.loadMore();
    }
}