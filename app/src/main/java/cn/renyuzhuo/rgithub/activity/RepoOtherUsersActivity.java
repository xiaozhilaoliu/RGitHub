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
import cn.renyuzhuo.rgithubandroidsdk.Dialog.LoadingDialog;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.user.OtherUserInfoBean;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.user.UserInfoBean;
import cn.renyuzhuo.rgithubandroidsdk.net.user.UserInfoClient;

public class RepoOtherUsersActivity extends BaseListViewActivity {

    Intent intent;
    String username;
    String reponame;
    String type;
    TextView titleText;

    Context context;

    private static Map<String, List<OtherUserInfoBean>> mapStars = new HashMap<>();
    private static Map<String, List<OtherUserInfoBean>> mapFocks = new HashMap<>();
    String repoFullName;
    private boolean isStar = false;

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
        repoFullName = username + ":" + reponame;
        UserInfoClient.setUserInfoClientListener(this);
        if (type.equals(getString(R.string.stargazers))) {
            isStar = true;
            titleText.setText(getString(R.string.stargazers));
            tempOtherUserInfoBean = mapStars.get(repoFullName);
            if (tempOtherUserInfoBean != null) {
                pageHelper = new PageHelper(tempOtherUserInfoBean.size());
                initList(tempOtherUserInfoBean);
                return;
            }
            pageHelper = new PageHelper();
            UserInfoClient.getRepoFollows(username, reponame, "stargazers", pageHelper.nextPage());
        } else if (type.equals(getString(R.string.forks))) {
            isStar = false;
            titleText.setText(getString(R.string.forks));
            tempOtherUserInfoBean = mapFocks.get(repoFullName);
            if (tempOtherUserInfoBean != null) {
                pageHelper = new PageHelper(tempOtherUserInfoBean.size());
                initList(tempOtherUserInfoBean);
                return;
            }
            pageHelper = new PageHelper();
            UserInfoClient.getRepoFollows(username, reponame, "forks", pageHelper.nextPage());
        }
        LoadingDialog.openLoadingDialogLoading(this);
    }

    private void initList(List<OtherUserInfoBean> tempOtherUserInfoBean) {
        adapter = new OtherUsersAdapter(context, tempOtherUserInfoBean);
        initListView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        UserInfoClient.setUserInfoClientListener(this);
    }

    @Override
    public void onGetUserList(List<OtherUserInfoBean> otherUserInfoBeenList) {
        LoadingDialog.closeDialog();
        pageHelper.hasMoreOrNot(otherUserInfoBeenList.size());

        if (adapter != null) {
            ((OtherUsersAdapter) adapter).addUserInfo(otherUserInfoBeenList);
            return;
        }

        adapter = new OtherUsersAdapter(context, otherUserInfoBeenList);
        initListView();

        if (isStar) {
            mapStars.put(repoFullName, otherUserInfoBeenList);
        } else {
            mapFocks.put(repoFullName, otherUserInfoBeenList);
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
        if (isStar) {
            UserInfoClient.getRepoFollows(username, reponame, "stargazers", pageHelper.nextPage());
        } else {
            UserInfoClient.getRepoFollows(username, reponame, "forks", pageHelper.nextPage());
        }
    }

    @Override
    public void afterInitListView() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                OtherUserInfoActivity.startOtherUserInfoActivity(context, ((OtherUsersAdapter.ViewHolder) view.getTag()).getName());
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