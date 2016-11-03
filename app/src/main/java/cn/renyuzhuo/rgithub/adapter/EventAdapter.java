package cn.renyuzhuo.rgithub.adapter;

import android.content.Context;
import android.widget.BaseAdapter;

import java.util.List;

import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.event.EventBean;

/**
 * Created by renyuzhuo on 16-11-2.
 */
public abstract class EventAdapter extends BaseAdapter {

    public final Context context;
    public final List<EventBean> eventBeenList;

    public EventAdapter(Context context, List<EventBean> eventBeen) {
        this.context = context;
        this.eventBeenList = eventBeen;
    }

    @Override
    public int getCount() {
        if (eventBeenList != null) {
            return eventBeenList.size();
        }
        return 0;
    }

    @Override
    public EventBean getItem(int position) {
        if (eventBeenList != null) {
            return eventBeenList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        if (eventBeenList != null) {
            return eventBeenList.get(position).hashCode();
        }
        return 0;
    }

}
