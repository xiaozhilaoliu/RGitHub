package cn.renyuzhuo.rgithub.adapter;

import android.content.Context;
import android.widget.BaseAdapter;

import java.util.List;

import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.event.EventBean;

/**
 * Created by renyuzhuo on 16-11-2.
 */
public abstract class EventAdapter extends ListBaseAdapter {

    public final Context context;
    public final List<EventBean> eventBeenList;

    public EventAdapter(Context context, List<EventBean> eventBeen) {
        super(context, eventBeen);
        this.context = context;
        this.eventBeenList = eventBeen;
    }

    @Override
    public EventBean getItem(int position) {
        if (eventBeenList != null) {
            return eventBeenList.get(position);
        }
        return null;
    }

}
