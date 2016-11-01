package cn.renyuzhuo.rgithub.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import cn.renyuzhuo.rgithub.R;
import cn.renyuzhuo.rgithub.adapter.OtherUsersAdapter;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.user.OtherUserInfoBean;
import cn.renyuzhuo.rgithubandroidsdk.net.user.UserInfoClient;
import cn.renyuzhuo.rgithubandroidsdk.net.user.UserInfoClientListener;
import cn.renyuzhuo.rwidget.Dialog.LoadingDialog;

public class OtherUsersActivity extends Activity implements UserInfoClientListener {

    Intent intent;
    String username, type;
    TextView titleText;

    Context context;
    ListView listView;

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
            UserInfoClient.getUserFollowersList(username);
            titleText.setText(getString(R.string.followers));
        } else if (type.equals(getString(R.string.following))) {
            UserInfoClient.getUserFollowingList(username);
            titleText.setText(getString(R.string.following));
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
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFail() {

    }
}
