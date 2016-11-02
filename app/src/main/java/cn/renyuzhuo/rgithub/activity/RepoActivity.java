package cn.renyuzhuo.rgithub.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.renyuzhuo.rgithub.R;
import cn.renyuzhuo.rgithub.adapter.ReposAdapter;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.repo.RepoBean;
import cn.renyuzhuo.rgithubandroidsdk.net.repo.RepoClient;
import cn.renyuzhuo.rwidget.Dialog.LoadingDialog;

public class RepoActivity extends BaseListViewActivity {

    Intent intent;
    String type;
    String username;
    private boolean isStars;

    TextView titleText;
    Context context;


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
                List<RepoBean> tempRepoBeanList = mapStars.get(username);
                pageHelper = new PageHelper(tempRepoBeanList.size());
                initList(tempRepoBeanList);
                return;
            }
            pageHelper = new PageHelper();
            RepoClient.getStarsList(username, pageHelper.nextPage());
        } else {
            isStars = false;
            titleText.setText(getString(R.string.repos));
            if (mapRepos.get(username) != null) {
                List<RepoBean> tempRepoBeanList = mapRepos.get(username);
                pageHelper = new PageHelper(tempRepoBeanList.size());
                initList(tempRepoBeanList);
                return;
            }
            pageHelper = new PageHelper();
            RepoClient.getReposList(username, pageHelper.nextPage());
        }
        LoadingDialog.openLoadingDialogLoading(this);
    }

    private void initList(List<RepoBean> tempRepoBeanList) {
        adapter = new ReposAdapter(context, tempRepoBeanList);
        initListView();
    }

    public static void startActivity(Context context, String login, String type) {
        Intent intent = new Intent(context, RepoActivity.class);
        intent.putExtra("username", login);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    @Override
    public void onGetRepoList(List<RepoBean> repoBeanList) {
        LoadingDialog.closeDialog();
        pageHelper.hasMoreOrNot(repoBeanList.size());

        if (adapter != null) {
            ((ReposAdapter) adapter).addRepo(repoBeanList);
            return;
        }

        adapter = new ReposAdapter(context, repoBeanList);
        initListView();

        if (isStars) {
            mapStars.put(username, repoBeanList);
        } else {
            mapRepos.put(username, repoBeanList);
        }
    }

    public void loadMore() {
        if (!pageHelper.hasMore()) {
            if (pageHelper.showToast()) {
                Toast.makeText(context, getString(R.string.has_no_more), Toast.LENGTH_SHORT).show();
            }
            return;
        }
        LoadingDialog.openLoadingDialogLoadingMore(context);
        if (isStars) {
            RepoClient.getStarsList(username, pageHelper.nextPage());
        } else {
            RepoClient.getReposList(username, pageHelper.nextPage());
        }
    }

}
