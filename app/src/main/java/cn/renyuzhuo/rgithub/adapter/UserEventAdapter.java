package cn.renyuzhuo.rgithub.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import cn.renyuzhuo.rgithub.R;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.event.EventBean;

/**
 * Created by renyuzhuo on 16-11-3.
 */
public class UserEventAdapter extends EventAdapter {
    public UserEventAdapter(Context context, List<EventBean> eventBeen) {
        super(context, eventBeen);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        EventViewHolder eventViewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.event_item, null, false);
            eventViewHolder = new EventViewHolder(context, view);
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
