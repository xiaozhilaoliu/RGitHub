package cn.renyuzhuo.rgithub.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.renyuzhuo.rgithub.R;
import cn.renyuzhuo.rgithub.adapter.OtherUsersAdapter;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.user.OtherUserInfoBean;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.user.OtherUserInfoDetailBean;
import cn.renyuzhuo.rgithubandroidsdk.net.user.UserInfoClient;
import cn.renyuzhuo.rgithubandroidsdk.net.user.UserInfoClientListener;
import cn.renyuzhuo.rlog.rlog;
import cn.renyuzhuo.rwidget.Dialog.LoadingDialog;

public class OtherUsersActivity extends Activity implements UserInfoClientListener {

    Intent intent;
    String username, type;
    TextView titleText;

    Context context;
    ListView listView;
    private static boolean isFollowing;

    private static Map<String, List<OtherUserInfoBean>> followersMap = new HashMap<>();
    private static Map<String, List<OtherUserInfoBean>> followingMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_users);
        context = this;

        titleText = (TextView) findViewById(R.id.title_text);
        listView = (ListView) findViewById(R.id.listview);

        intent = getIntent();
        username = intent.getStringExtra("username");
        type = intent.getStringExtra("type");
        UserInfoClient.setUserInfoClientListener(this);
        if (type.equals(getString(R.string.followers))) {
            isFollowing = false;
            titleText.setText(getString(R.string.followers));
            if (followersMap.get(username) != null) {
                onGetUserList(followersMap.get(username));
                return;
            }
            UserInfoClient.getUserFollowersList(username);
        } else if (type.equals(getString(R.string.following))) {
            isFollowing = true;
            titleText.setText(getString(R.string.following));
            if (followingMap.get(username) != null) {
                onGetUserList(followingMap.get(username));
                return;
            }
            UserInfoClient.getUserFollowingList(username);
        }
        LoadingDialog.openLoadingDialogLoading(this);
    }

    @Override
    public void onGetUserInfoSuccess() {

    }

    @Override
    public void onGetUserList(List<OtherUserInfoBean> otherUserInfoBeenList) {
        LoadingDialog.closeDialog();
        OtherUsersAdapter otherUsersAdapter = new OtherUsersAdapter(context, otherUserInfoBeenList);
        listView.setAdapter(otherUsersAdapter);
        if (otherUserInfoBeenList.size() != 0) {
            listView.setVisibility(View.VISIBLE);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                rlog.d(position);
                if (view.getTag() instanceof OtherUsersAdapter.ViewHolder) {
                    String name = ((OtherUsersAdapter.ViewHolder) view.getTag()).getName().getText().toString();
                    OtherUserInfoActivity.startOtherUserInfoActivity(context, name);
                }
            }
        });

        if (isFollowing) {
            followingMap.put(username, otherUserInfoBeenList);
        } else {
            followersMap.put(username, otherUserInfoBeenList);
        }
    }

    @Override
    public void onGetOtherUserInfoSuccess(OtherUserInfoDetailBean otherUserInfoBean) {

    }

}
