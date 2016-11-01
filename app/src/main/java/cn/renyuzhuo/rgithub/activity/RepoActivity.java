package cn.renyuzhuo.rgithub.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.renyuzhuo.rgithub.R;
import cn.renyuzhuo.rgithub.adapter.ReposAdapter;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.repo.RepoBean;
import cn.renyuzhuo.rgithubandroidsdk.net.repo.RepoClient;
import cn.renyuzhuo.rgithubandroidsdk.net.repo.RepoClientListener;
import cn.renyuzhuo.rlog.rlog;
import cn.renyuzhuo.rwidget.Dialog.LoadingDialog;
import okhttp3.HttpUrl;

public class RepoActivity extends Activity implements RepoClientListener {

    Intent intent;
    String type;
    String username;
    private boolean isStars;

    TextView titleText;
    Context context;
    ListView listView;

    private boolean hasMore = false;

    private static Map<String, List<RepoBean>> mapStars = new HashMap<>();
    private static Map<String, List<RepoBean>> mapRepos = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        context = this;

        titleText = (TextView) findViewById(R.id.title_text);
        listView = (ListView) findViewById(R.id.listview);

        intent = getIntent();
        username = intent.getStringExtra("username");
        type = intent.getStringExtra("type");

        RepoClient.setRepoClientListener(this);
        if (type.equals(getString(R.string.stars))) {
            isStars = true;
            titleText.setText(getString(R.string.stars));
            if (mapStars.get(username) != null) {
                onGetRepoList(mapStars.get(username));
                return;
            }
            RepoClient.getStarsList(username);
        } else {
            isStars = false;
            titleText.setText(getString(R.string.repos));
            if (mapRepos.get(username) != null) {
                onGetRepoList(mapRepos.get(username));
                return;
            }
            RepoClient.getReposList(username);
        }
        LoadingDialog.openLoadingDialogLoading(this);
    }

    public static void startActivity(Context context, String login, String type) {
        Intent intent = new Intent(context, RepoActivity.class);
        intent.putExtra("username", login);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    @Override
    public void onGetRepoList(List<RepoBean> repoBeanList) {
        rlog.d();
        LoadingDialog.closeDialog();
        ReposAdapter reposAdapter = new ReposAdapter(context, repoBeanList);
        listView.setAdapter(reposAdapter);

    }
}
