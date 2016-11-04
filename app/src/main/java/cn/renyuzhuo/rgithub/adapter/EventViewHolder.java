package cn.renyuzhuo.rgithub.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import cn.renyuzhuo.rgithub.R;
import cn.renyuzhuo.rgithub.activity.OtherUserInfoActivity;
import cn.renyuzhuo.rgithub.activity.RepoDetailActivity;
import cn.renyuzhuo.rgithub.utils.DateUtil;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.event.EventBean;

class EventViewHolder {
    private Context context;
    private final View view;
    private String pname;
    private ImageView eventAvatar;
    private TextView eventEvent;
    private ImageView eventIcon;
    private TextView eventTime;
    private boolean isRepo;
    private EventBean eventBean;
    LinearLayout goToAction;

    EventViewHolder(Context context, View view, String pname) {
        this.context = context;
        this.view = view;
        this.pname = pname;
        isRepo = true;

        initView(view);
    }

    EventViewHolder(Context context, View view) {
        this.context = context;
        this.view = view;

        isRepo = false;
        initView(view);
    }

    private void initView(View view) {
        eventAvatar = (ImageView) view.findViewById(R.id.event_avatar);
        eventEvent = (TextView) view.findViewById(R.id.event_event);
        eventIcon = (ImageView) view.findViewById(R.id.event_icon);
        eventTime = (TextView) view.findViewById(R.id.event_time);
        goToAction = (LinearLayout) view.findViewById(R.id.item_container);
    }

    public void setData(EventBean tempEventBean) {
        eventBean = tempEventBean;
        dealEventType();
    }

    private void setBackGroundColor(int sourceColor) {
        view.setBackgroundColor(context.getResources().getColor(sourceColor));
    }

    private void dealEventType() {
        Picasso.with(context).load(eventBean.getActor().getAvatar_url()).placeholder(R.drawable.logo).into(eventAvatar);
        String eventType = eventBean.getType();
        String titleString = eventType;
        switch (eventType) {
            case "WatchEvent": {
                if (isRepo) {
                    titleString = eventBean.getActor().getLogin() + " " + eventBean.getPayload().getAction() + " " + pname;
                } else {
                    titleString = eventBean.getActor().getLogin() + " " + eventBean.getPayload().getAction() + " " + eventBean.getRepo().getName();
                }
                eventIcon.setImageResource(R.drawable.ic_star);
                setBackGroundColor(R.color.event_watch);
                break;
            }
            case "IssuesEvent": {
                titleString = eventBean.getActor().getLogin() + " " + eventBean.getPayload().getAction() + " issue#" + eventBean.getPayload().getIssue().getTitle();
                eventIcon.setImageResource(R.drawable.warn);
                setBackGroundColor(R.color.event_issue);
                break;
            }
            case "IssueCommentEvent": {
                titleString = eventBean.getActor().getLogin() + " " + eventBean.getPayload().getAction() + " comments on issue#" + eventBean.getPayload().getIssue().getNumber();
                eventIcon.setImageResource(R.drawable.warn);
                setBackGroundColor(R.color.event_comment);
                break;
            }
            case "PushEvent": {
                titleString = eventBean.getActor().getLogin() + " push to " + eventBean.getPayload().getRef();
                eventIcon.setImageResource(R.drawable.commit);
                setBackGroundColor(R.color.event_push);
                break;
            }
            case "CreateEvent": {
                titleString = eventBean.getActor().getLogin() + " create " + eventBean.getPayload().getRef_type();
                if (eventBean.getPayload().getRef_type().equals("repository")) {
                    titleString += " " + eventBean.getRepo().getName();
                } else {
                    titleString += " " + eventBean.getPayload().getRef();
                }
                eventIcon.setImageResource(R.drawable.tool);
                setBackGroundColor(R.color.event_create);
                break;
            }
            case "ForkEvent": {
                titleString = eventBean.getActor().getLogin() + " fork to " + eventBean.getPayload().getForkee().getFull_name();
                eventIcon.setImageResource(R.drawable.branch);
                setBackGroundColor(R.color.event_fork);
                break;
            }
            case "DeleteEvent": {
                titleString = eventBean.getActor().getLogin() + " delete " + eventBean.getPayload().getRef_type() + " " + eventBean.getPayload().getRef();
                eventIcon.setImageResource(R.drawable.warn);
                setBackGroundColor(R.color.event_del);
                break;
            }
        }
        eventEvent.setText(titleString);
        eventTime.setText(DateUtil.formate(eventBean.getCreated_at()));

        eventAvatarClickListener();
        setActionClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RepoDetailActivity.startRepoDetailActivity(context, eventBean.getRepo().getName());
            }
        });
    }

    private void eventAvatarClickListener() {
        eventAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OtherUserInfoActivity.startOtherUserInfoActivity(context, eventBean.getActor().getLogin());
                return;
            }
        });
    }

    private void setActionClickListener(View.OnClickListener onClickListener) {
        goToAction.setOnClickListener(onClickListener);
    }

}

