package cn.renyuzhuo.rgithub.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.renyuzhuo.rgithub.R;
import cn.renyuzhuo.rgithub.adapter.OtherUsersAdapter;
import cn.renyuzhuo.rgithub.adapter.ReposAdapter;
import cn.renyuzhuo.rgithub.utils.GitHubData;
import cn.renyuzhuo.rgithubandroidsdk.Dialog.LoadingDialog;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.event.Repo;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.repo.RepoBean;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.user.OtherUserInfoBean;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.user.UserInfoBean;
import cn.renyuzhuo.rgithubandroidsdk.net.repo.RepoClient;
import cn.renyuzhuo.rgithubandroidsdk.net.user.UserInfoClient;
import cn.renyuzhuo.rlog.rlog;

public class RepoOtherUsersActivity extends BaseListViewActivity {

    Intent intent;
    String username;
    String reponame;
    String type;
    TextView titleText;

    Context context;
    String repoFullName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        context = this;

        titleText = (TextView) findViewById(R.id.title_text);
        listView = (ListView) findViewById(R.id.listview);

        intent = getIntent();
        username = intent.getStringExtra("username");
        reponame = intent.getStringExtra("reponame");
        type = intent.getStringExtra("type");
        List<OtherUserInfoBean> tempOtherUserInfoBean;
        repoFullName = username + "/" + reponame;
        if (type.equals(getString(R.string.stargazers))) {
            rlog.d(getString(R.string.stargazers));
            titleText.setText(getString(R.string.stargazers));
            tempOtherUserInfoBean = GitHubData.getStarOtherUserInfoBeansMap().get(repoFullName);
            if (tempOtherUserInfoBean != null) {
                pageHelper = new PageHelper(tempOtherUserInfoBean.size());
                initListUser(tempOtherUserInfoBean);
                return;
            }
            pageHelper = new PageHelper();
            UserInfoClient.getRepoFollows(this, username, reponame, "stargazers", pageHelper.nextPage());
        } else if (type.equals(getString(R.string.forks))) {
            rlog.d(getString(R.string.forks));
            titleText.setText(getString(R.string.forks));
            if (GitHubData.getForkRepoBeansMap().get(repoFullName) != null) {
                pageHelper = new PageHelper(GitHubData.getForkRepoBeansMap().get(repoFullName).size());
                initListRepo(GitHubData.getForkRepoBeansMap().get(repoFullName));
                return;
            }
            pageHelper = new PageHelper();
            RepoClient.getRepoForks(this, username, reponame, "forks", pageHelper.nextPage());
        } else if (type.equals(getString(R.string.watchers))) {
            rlog.d(getString(R.string.watchers));
            titleText.setText(getString(R.string.watchers));
            if (GitHubData.getWatchOtherUserInfoBeansMap().get(repoFullName) != null) {
                pageHelper = new PageHelper(GitHubData.getWatchOtherUserInfoBeansMap().get(repoFullName).size());
                initListUser(GitHubData.getWatchOtherUserInfoBeansMap().get(repoFullName));
                return;
            }
            pageHelper = new PageHelper();
            UserInfoClient.getRepoWatchers(this, username, reponame, "subscribers", pageHelper.nextPage());
        }
        LoadingDialog.openLoadingDialogLoading(this);
    }

    private void initListRepo(List<RepoBean> repoBeanList) {
        adapter = new ReposAdapter(context, repoBeanList, true);
        initListView();
    }

    private void initListUser(List<OtherUserInfoBean> tempOtherUserInfoBean) {
        adapter = new OtherUsersAdapter(context, tempOtherUserInfoBean);
        initListView();
    }

    @Override
    public void onGetUserList(List<OtherUserInfoBean> otherUserInfoBeenList, String type) {
        LoadingDialog.closeDialog();
        pageHelper.hasMoreOrNot(otherUserInfoBeenList.size());

        if (adapter != null) {
            adapter.addLists(otherUserInfoBeenList);
            return;
        }

        adapter = new OtherUsersAdapter(context, otherUserInfoBeenList);
        initListView();

        if (type.equals(getString(R.string.stargazers))) {
            GitHubData.getStarOtherUserInfoBeansMap().put(repoFullName, otherUserInfoBeenList);
        } else if (type.equals(getString(R.string.stargazers))) {
            GitHubData.getWatchOtherUserInfoBeansMap().put(repoFullName, otherUserInfoBeenList);
        }
    }

    @Override
    public void onGetRepoList(List<RepoBean> repoBeanList) {
        LoadingDialog.closeDialog();
        pageHelper.hasMoreOrNot(repoBeanList.size());

        if (adapter != null) {
            adapter.addLists(repoBeanList);
            return;
        }

        adapter = new ReposAdapter(context, repoBeanList, true);
        initListView();

        if (type.equals(getString(R.string.forks))) {
            GitHubData.getForkRepoBeansMap().put(repoFullName, repoBeanList);
        }
    }

    public void loadMore() {
        if (!pageHelper.hasMore()) {
            if (pageHelper.showToast()) {
                Toast.makeText(context, getString(R.string.has_no_more), Toast.LENGTH_SHORT).show();
            }
            return;
        }
        LoadingDialog.openLoadingDialogLoadingMore(this);
        if (type.equals(getString(R.string.stargazers))) {
            UserInfoClient.getRepoFollows(this, username, reponame, "stargazers", pageHelper.nextPage());
        } else if (type.equals(getString(R.string.forks))) {
            RepoClient.getRepoForks(this, username, reponame, "forks", pageHelper.nextPage());
        } else if (type.equals(getString(R.string.watchers))) {
            UserInfoClient.getRepoFollows(this, username, reponame, "subscribers", pageHelper.nextPage());
        }
    }

    @Override
    public void afterInitListView() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (type.equals(getString(R.string.stargazers))) {
                    OtherUserInfoActivity.startOtherUserInfoActivity(context, ((OtherUsersAdapter.ViewHolder) view.getTag()).getName());
                } else if (type.equals(getString(R.string.forks))) {
                    RepoDetailActivity.startRepoDetailActivity(context, ((ReposAdapter) adapter).getItem(position).getFull_name());
                } else if (type.equals(getString(R.string.watchers))) {
                    OtherUserInfoActivity.startOtherUserInfoActivity(context, ((OtherUsersAdapter.ViewHolder) view.getTag()).getName());
                }
            }
        });
    }

    public static void startRepoOtherUsersActivity(Context context, String login, String name, String string) {
        Intent intent = new Intent(context, RepoOtherUsersActivity.class);
        intent.putExtra("username", login);
        intent.putExtra("reponame", name);
        intent.putExtra("type", string);
        context.startActivity(intent);
    }
}
