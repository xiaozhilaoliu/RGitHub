package cn.renyuzhuo.rgithub.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.renyuzhuo.rgithub.R;
import cn.renyuzhuo.rgithub.adapter.EventAdapter;
import cn.renyuzhuo.rgithub.adapter.RepoEventAdapter;
import cn.renyuzhuo.rgithub.adapter.UserEventAdapter;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.event.EventBean;
import cn.renyuzhuo.rgithubandroidsdk.net.Event.EventClient;
import cn.renyuzhuo.rwidget.Dialog.LoadingDialog;

public class EventActivity extends BaseListViewActivity {

    Intent intent;
    Context context;
    String username, pname, type;
    private static Map<String, List<EventBean>> mapRepoEvent = new HashMap<>();
    private static Map<String, List<EventBean>> mapUserEvent = new HashMap<>();

    private boolean isRepoEvent;
    String mapName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        context = this;

        listView = (ListView) findViewById(R.id.listview);
        intent = getIntent();

        username = intent.getStringExtra("username");
        pname = intent.getStringExtra("pname");
        type = intent.getStringExtra("type");

        EventClient.setEventClientListener(this);
        if (type.equals(getString(R.string.project_event))) {
            isRepoEvent = true;
            mapName = username + "/" + pname;
            if (mapRepoEvent.get(mapName) != null) {
                List<EventBean> tempEventBeen = mapRepoEvent.get(mapName);
                pageHelper = new PageHelper(tempEventBeen.size());
                initList(tempEventBeen);
                return;
            }
            pageHelper = new PageHelper();
            EventClient.getRepoEvent(username, pname, pageHelper.nextPage());
        } else if (type.equals(getString(R.string.person_event))) {
            isRepoEvent = false;
            mapName = username;
            if (mapUserEvent.get(mapName) != null) {
                List<EventBean> tempEventBean = mapUserEvent.get(mapName);
                pageHelper = new PageHelper(tempEventBean.size());
                initList(tempEventBean);
                return;
            }
            EventClient.getUserEvent(username, pageHelper.nextPage());
        }
        LoadingDialog.openLoadingDialogLoading(this);
    }

    private void initList(List<EventBean> tempEventBean) {
        if (isRepoEvent) {
            adapter = new RepoEventAdapter(context, tempEventBean, pname);
        } else {
            adapter = new UserEventAdapter(context, tempEventBean);
        }
        initListView();
    }

    @Override
    public void onGetRepoEvent(List<EventBean> eventBeen) {
        LoadingDialog.closeDialog();
        pageHelper.hasMoreOrNot(eventBeen.size());

        if (adapter != null) {
            if (adapter instanceof RepoEventAdapter) {
                ((RepoEventAdapter) adapter).addEvent(eventBeen);
            } else if (adapter instanceof UserEventAdapter) {
                ((UserEventAdapter) adapter).addEvent(eventBeen);
            }
            return;
        }
        if (isRepoEvent) {
            adapter = new RepoEventAdapter(context, eventBeen, pname);
        } else {
            adapter = new UserEventAdapter(context, eventBeen);
        }
        initListView();

        if (isRepoEvent) {
            mapRepoEvent.put(mapName, eventBeen);
        } else {
            mapUserEvent.put(mapName, eventBeen);
        }
    }

    @Override
    public void afterInitListView() {

    }

    public static void startEventActivity(Context context, String username, String projectName) {
        Intent intent = new Intent(context, EventActivity.class);
        intent.putExtra("username", username);
        intent.putExtra("pname", projectName);
        intent.putExtra("type", context.getString(R.string.project_event));
        context.startActivity(intent);
    }

    @Override
    public void loadMore() {
        if (!pageHelper.hasMore()) {
            if (pageHelper.showToast()) {
                Toast.makeText(context, getString(R.string.has_no_more), Toast.LENGTH_SHORT).show();
            }
            return;
        }
        LoadingDialog.openLoadingDialogLoadingMore(context);
        if (isRepoEvent) {
            EventClient.getRepoEvent(username, pname, pageHelper.nextPage());
        } else {
            EventClient.getUserEvent(username, pageHelper.nextPage());
        }
    }
}
