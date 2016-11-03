package cn.renyuzhuo.rgithub.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import cn.renyuzhuo.rgithub.R;
import cn.renyuzhuo.rgithub.utils.DateUtil;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.event.EventBean;

public class EventViewHolder {
    Context context;
    private final View view;
    String pname;
    ImageView eventAvatar;
    TextView eventEvent;
    ImageView eventIcon;
    TextView eventTime;

    public EventViewHolder(Context context, View view, String pname) {
        this.context = context;
        this.view = view;
        this.pname = pname;

        eventAvatar = (ImageView) view.findViewById(R.id.event_avatar);
        eventEvent = (TextView) view.findViewById(R.id.event_event);
        eventIcon = (ImageView) view.findViewById(R.id.event_icon);
        eventTime = (TextView) view.findViewById(R.id.event_time);
    }

    public void setData(EventBean eventBean) {
        Picasso.with(context).load(eventBean.getActor().getAvatar_url()).placeholder(R.drawable.logo).into(eventAvatar);
        String eventType = eventBean.getType();
        String titleString = eventType;
        switch (eventType) {
            case "WatchEvent": {
                titleString = eventBean.getActor().getLogin() + " " + eventBean.getPayload().getAction() + " " + pname;
                eventIcon.setImageResource(R.drawable.ic_star);
                view.setBackgroundColor(context.getResources().getColor(R.color.event_watch));
                break;
            }
            case "IssuesEvent": {
                titleString = eventBean.getActor().getLogin() + " " + eventBean.getPayload().getAction() + " issue#" + eventBean.getPayload().getIssue().getTitle();
                eventIcon.setImageResource(R.drawable.warn);
                view.setBackgroundColor(context.getResources().getColor(R.color.event_issue));
                break;
            }
            case "IssueCommentEvent": {
                titleString = eventBean.getActor().getLogin() + " " + eventBean.getPayload().getAction() + " comments on issue#" + eventBean.getPayload().getIssue().getNumber();
                eventIcon.setImageResource(R.drawable.warn);
                view.setBackgroundColor(context.getResources().getColor(R.color.event_comment));
                break;
            }
            case "PushEvent": {
                titleString = eventBean.getActor().getLogin() + " push to " + eventBean.getPayload().getRef();
                eventIcon.setImageResource(R.drawable.commit);
                view.setBackgroundColor(context.getResources().getColor(R.color.event_push));
                break;
            }
            case "CreateEvent": {
                titleString = eventBean.getActor().getLogin() + " create " + eventBean.getPayload().getRef_type();
                if (!eventBean.getPayload().getRef_type().equals("repository")) {
                    titleString += " " + eventBean.getPayload().getRef();
                }
                eventIcon.setImageResource(R.drawable.tool);
                view.setBackgroundColor(context.getResources().getColor(R.color.event_create));
                break;
            }
            case "ForkEvent": {
                titleString = eventBean.getActor().getLogin() + " fork to " + eventBean.getPayload().getForkee().getFull_name();
                eventIcon.setImageResource(R.drawable.branch);
                view.setBackgroundColor(context.getResources().getColor(R.color.event_fork));
                break;
            }
            case "DeleteEvent": {
                titleString = eventBean.getActor().getLogin() + " delete " + eventBean.getPayload().getRef_type() + " " + eventBean.getPayload().getRef();
                eventIcon.setImageResource(R.drawable.warn);
                view.setBackgroundColor(context.getResources().getColor(R.color.event_del));
                break;
            }
        }
        eventEvent.setText(titleString);
        eventTime.setText(DateUtil.formate(eventBean.getCreated_at()));
    }
}

