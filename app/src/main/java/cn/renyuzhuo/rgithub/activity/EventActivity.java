package cn.renyuzhuo.rgithub.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.InputMethodSubtype;
import android.widget.ListView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.renyuzhuo.rgithub.R;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.event.EventBean;
import cn.renyuzhuo.rgithubandroidsdk.net.Event.EventClient;

public class EventActivity extends BaseListViewActivity {

    Intent intent;
    String username, pname, type;
    private static Map<String, List<EventBean>> mapRepoEvent = new HashMap<>();
    private static Map<String, List<EventBean>> mapuserEvent = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        listView = (ListView) findViewById(R.id.listview);
        intent = getIntent();

        username = intent.getStringExtra("username");
        pname = intent.getStringExtra("pname");
        type = intent.getStringExtra("type");

        if (type.equals(getString(R.string.project_event))) {
            EventClient.getProjectEvent(username, pname);
        } else {
            EventClient.getProjectEvent(username);
        }
    }

    public static void startEventActivity(Context context, String username, String projectName) {
        Intent intent = new Intent(context, EventActivity.class);
        intent.putExtra("username", username);
        intent.putExtra("pname", projectName);
        intent.putExtra("type", context.getString(R.string.project_event));
        context.startActivity(intent);
    }
}
