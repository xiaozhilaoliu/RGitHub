package cn.renyuzhuo.rgithub.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import cn.renyuzhuo.rgithub.R;
import cn.renyuzhuo.rgithub.RGitHubApplication;
import cn.renyuzhuo.rgithub.activity.OtherUsersActivity;
import cn.renyuzhuo.rgithub.activity.RepoActivity;
import cn.renyuzhuo.rgithub.activity.SettingActivity;
import cn.renyuzhuo.rgithub.utils.OpenWeb;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.user.UserInfoBean;

public class FourthFragment extends BaseFragment {

    ImageView avatar;
    TextView name, bio;
    Context context;
    TextView followersNum, followingNum;
    View view;

    LinearLayout followers, following;

    LinearLayout starts, repos;

    LinearLayout websit;

    LinearLayout forkMe;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fourth, container, false);
        context = getActivity();
        initViews();
        if (RGitHubApplication.isLogin) {
            initUserView();
        }
        return view;
    }

    private void initViews() {
        if (view == null) {
            return;
        }
        avatar = (ImageView) view.findViewById(R.id.avatar);
        name = (TextView) view.findViewById(R.id.name);
        bio = (TextView) view.findViewById(R.id.bio);
        followersNum = (TextView) view.findViewById(R.id.followers_num);
        followingNum = (TextView) view.findViewById(R.id.following_num);

        followers = (LinearLayout) view.findViewById(R.id.followers);
        following = (LinearLayout) view.findViewById(R.id.following);

        starts = (LinearLayout) view.findViewById(R.id.stars);
        repos = (LinearLayout) view.findViewById(R.id.repos);
        websit = (LinearLayout) view.findViewById(R.id.websit);

        forkMe = (LinearLayout) view.findViewById(R.id.fork_me);
    }

    private void initUserView() {
        Picasso.with(context).load(UserInfoBean.getInstance().getAvatar_url()).into(avatar);
        name.setText(UserInfoBean.getInstance().getName());
        bio.setText(UserInfoBean.getInstance().getBio());
        followersNum.setText(String.valueOf(UserInfoBean.getInstance().getFollowers()));
        followingNum.setText(String.valueOf(UserInfoBean.getInstance().getFollowing()));

        followers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OtherUsersActivity.class);
                intent.putExtra("username", UserInfoBean.getInstance().getLogin());
                intent.putExtra("type", getString(R.string.followers));
                startActivity(intent);
            }
        });

        following.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OtherUsersActivity.class);
                intent.putExtra("username", UserInfoBean.getInstance().getLogin());
                intent.putExtra("type", getString(R.string.following));
                startActivity(intent);
            }
        });

        starts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RepoActivity.startActivity(context, UserInfoBean.getInstance().getLogin(), getString(R.string.stars));
            }
        });

        repos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RepoActivity.startActivity(context, UserInfoBean.getInstance().getLogin(), getString(R.string.repos));
            }
        });

        websit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenWeb.open(context, UserInfoBean.getInstance().getHtml_url());
            }
        });

        forkMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SettingActivity.class);
                context.startActivity(intent);
            }
        });
    }
}
