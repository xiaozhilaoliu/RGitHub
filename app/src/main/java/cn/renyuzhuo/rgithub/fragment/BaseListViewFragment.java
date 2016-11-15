package cn.renyuzhuo.rgithub.fragment;

import android.view.View;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import cn.renyuzhuo.rgithub.activity.PageHelper;
import cn.renyuzhuo.rgithub.adapter.ListBaseAdapter;
import cn.renyuzhuo.rlog.rlog;

/**
 * Created by renyuzhuo on 16-11-3.
 */
public class BaseListViewFragment extends BaseFragment {
    ListBaseAdapter adapter;
    ListView listView;
    PageHelper pageHelper;

    public void initListView() {
        if (adapter != null) {
            listView.setAdapter(adapter);
            listView.setVisibility(View.VISIBLE);

            listView.setOnScrollListener(new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState) {
                    if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                        if (view.getLastVisiblePosition() == view.getCount() - 1) {
                            rlog.d("load more");
                            loadMore();
                        }
                    }
                }

                @Override
                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                }
            });
            afterInitListView();
        }
    }

    public void afterInitListView() {

    }

    public void loadMore() {

    }
}
