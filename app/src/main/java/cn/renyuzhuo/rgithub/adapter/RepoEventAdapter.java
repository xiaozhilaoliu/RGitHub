package cn.renyuzhuo.rgithub.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cn.renyuzhuo.rgithub.R;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.event.EventBean;

/**
 * Created by renyuzhuo on 16-11-3.
 */
public class RepoEventAdapter extends EventAdapter {
    String pname;

    public RepoEventAdapter(Context context, List<EventBean> eventBeen, String pname) {
        super(context, eventBeen);
        this.pname = pname;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        EventViewHolder eventViewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.event_item, null, false);
            eventViewHolder = new EventViewHolder(context, view, pname);
            view.setTag(eventViewHolder);
        } else {
            eventViewHolder = (EventViewHolder) view.getTag();
        }
        eventViewHolder.setData(getItem(position));
        return view;
    }

    public void addEvent(List<EventBean> tempEventBeenList) {
        eventBeenList.addAll(tempEventBeenList);
        notifyDataSetChanged();
    }

}
