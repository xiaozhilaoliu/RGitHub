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
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.event_item, null, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.setData(getItem(position));
        return view;
    }

    public void addEvent(List<EventBean> tempEventBeenList) {
        eventBeenList.addAll(tempEventBeenList);
        notifyDataSetChanged();
    }

    public class ViewHolder {
        //        ImageView eventAvatar;
//        TextView eventEvent;
//
//
        public ViewHolder(View view) {
//            eventAvatar = (ImageView) view.findViewById(R.id.event_avatar);
//            eventEvent = (TextView) view.findViewById(R.id.event_event);
        }

        public void setData(EventBean eventBean) {
//            Picasso.with(context).load(eventBean.getActor().getAvatar_url()).placeholder(R.drawable.logo).into(eventAvatar);
//            String eventType = eventBean.getType();
//            String description = eventType;
//            switch (eventType) {
//                case "WatchEvent": {
//                    description = eventBean.getActor().getLogin() + " " + eventBean.getPayload().getAction() + " " + eventBean.getRepo().getName();
//                    break;
//                }
//            }
//            eventEvent.setText(description);
        }
    }
}
