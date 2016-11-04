package cn.renyuzhuo.rgithub.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import cn.renyuzhuo.rgithub.R;
import cn.renyuzhuo.rgithub.utils.OpenWeb;
import cn.renyuzhuo.rgithubandroidsdk.Dialog.LoadingDialog;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.user.OtherUserInfoDetailBean;
import cn.renyuzhuo.rgithubandroidsdk.net.user.UserInfoClient;

public class OtherUserInfoActivity extends BaseActivity {

    Context context;
    public static Map<String, OtherUserInfoDetailBean> map = new HashMap<>();
    private Intent intent;

    ImageView avatar;
    TextView name, bio;
    TextView followersNum, followingNum;
    LinearLayout followers, following;
    private OtherUserInfoDetailBean otherUserInfoDetailBean;
    String username;

    LinearLayout starts, repos;

    LinearLayout websit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_user_info);
        context = this;

        intent = getIntent();
        username = intent.getStringExtra("name");
        if (map.get(username) != null) {
            onGetOtherUserInfoSuccess(map.get(username));
            return;
        }
        UserInfoClient.getOtherUserInfo(this, username);
        LoadingDialog.openLoadingDialogLoading(context);
    }


    private void initViews() {
        avatar = (ImageView) findViewById(R.id.avatar);
        name = (TextView) findViewById(R.id.name);
        bio = (TextView) findViewById(R.id.bio);
        followersNum = (TextView) findViewById(R.id.followers_num);
        followingNum = (TextView) findViewById(R.id.following_num);

        followers = (LinearLayout) findViewById(R.id.followers);
        following = (LinearLayout) findViewById(R.id.following);

        starts = (LinearLayout) findViewById(R.id.stars);
        repos = (LinearLayout) findViewById(R.id.repos);
        websit = (LinearLayout) findViewById(R.id.websit);
    }

    private void initUserView() {
        Picasso.with(context).load(otherUserInfoDetailBean.getAvatar_url()).into(avatar);
        name.setText(otherUserInfoDetailBean.getLogin());
        bio.setText(otherUserInfoDetailBean.getBio());
        followersNum.setText(String.valueOf(otherUserInfoDetailBean.getFollowers()));
        followingNum.setText(String.valueOf(otherUserInfoDetailBean.getFollowing()));

        followers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OtherUsersActivity.class);
                intent.putExtra("username", otherUserInfoDetailBean.getLogin());
                intent.putExtra("type", getString(R.string.followers));
                startActivity(intent);
            }
        });

        following.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OtherUsersActivity.class);
                intent.putExtra("username", otherUserInfoDetailBean.getLogin());
                intent.putExtra("type", getString(R.string.following));
                startActivity(intent);
            }
        });

        starts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RepoActivity.startActivity(context, username, getString(R.string.stars));
            }
        });

        repos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RepoActivity.startActivity(context, username, getString(R.string.repos));
            }
        });

        websit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenWeb.open(context, otherUserInfoDetailBean.getHtml_url());
            }
        });

    }

    public static void startOtherUserInfoActivity(Context context, String name) {
        Intent intent = new Intent(context, OtherUserInfoActivity.class);
        intent.putExtra("name", name);
        context.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public static void clear() {
        map.clear();
    }

    @Override
    public void onGetOtherUserInfoSuccess(OtherUserInfoDetailBean otherUserInfoDetailBean) {
        this.otherUserInfoDetailBean = otherUserInfoDetailBean;
        LoadingDialog.closeDialog();
        initViews();
        initUserView();
        map.put(username, otherUserInfoDetailBean);
    }

}
