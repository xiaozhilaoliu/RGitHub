package cn.renyuzhuo.rgithub.adapter;

import android.content.Context;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by renyuzhuo on 16-11-15.
 */
public abstract class ListBaseAdapter extends BaseAdapter {

    Context context;
    List<? extends Object> lists;

    ListBaseAdapter(Context context, List lists) {
        this.context = context;
        this.lists = lists;
    }

    @Override
    public int getCount() {
        if (lists != null) {
            return lists.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (lists != null) {
            return lists.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        if (lists != null) {
            return lists.get(position).hashCode();
        }
        return 0;
    }

    public void addLists(List lists) {
        this.lists.addAll(lists);
        notifyDataSetChanged();
    }

}
