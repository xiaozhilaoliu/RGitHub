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
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.user.OtherUserInfoDetailBean;
import cn.renyuzhuo.rgithubandroidsdk.net.user.UserInfoClient;

public class OtherUsersActivity extends BaseListViewActivity {

    Intent intent;
    String username, type;
    TextView titleText;
    Context context;
    private boolean isFollowing;

    private static Map<String, List<OtherUserInfoBean>> mapFollowers = new HashMap<>();
    private static Map<String, List<OtherUserInfoBean>> mapFollowing = new HashMap<>();

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
        if (type.equals(getString(R.string.followers))) {
            isFollowing = false;
            titleText.setText(getString(R.string.followers));
            if (mapFollowers.get(username) != null) {
                List<OtherUserInfoBean> tempOtherUserInfo = mapFollowers.get(username);
                pageHelper = new PageHelper(tempOtherUserInfo.size());
                initList(tempOtherUserInfo);
                return;
            }
            pageHelper = new PageHelper();
            UserInfoClient.getUserFollowersList(this, username, pageHelper.nextPage());
        } else if (type.equals(getString(R.string.following))) {
            isFollowing = true;
            titleText.setText(getString(R.string.following));
            if (mapFollowing.get(username) != null) {
                List<OtherUserInfoBean> tempOtherUserInfo = mapFollowing.get(username);
                pageHelper = new PageHelper(tempOtherUserInfo.size());
                initList(tempOtherUserInfo);
                return;
            }
            pageHelper = new PageHelper();
            UserInfoClient.getUserFollowingList(this, username, pageHelper.nextPage());
        }
        LoadingDialog.openLoadingDialogLoading(this);
    }

    private void initList(List<OtherUserInfoBean> tempOtherUserInfo) {
        adapter = new OtherUsersAdapter(context, tempOtherUserInfo);
        initListView();
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

        if (isFollowing) {
            mapFollowing.put(username, otherUserInfoBeenList);
        } else {
            mapFollowers.put(username, otherUserInfoBeenList);
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
        if (isFollowing) {
            UserInfoClient.getUserFollowingList(this, username, pageHelper.nextPage());
        } else {
            UserInfoClient.getUserFollowersList(this, username, pageHelper.nextPage());
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
}
