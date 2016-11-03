package cn.renyuzhuo.rgithub.activity;

import android.app.Activity;

import java.util.List;

import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.AccessTokenBean;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.event.EventBean;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.repo.RepoBean;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.user.OtherUserInfoBean;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.user.OtherUserInfoDetailBean;
import cn.renyuzhuo.rgithubandroidsdk.net.Event.EventClientListener;
import cn.renyuzhuo.rgithubandroidsdk.net.login.LoginClientListener;
import cn.renyuzhuo.rgithubandroidsdk.net.repo.RepoClientListener;
import cn.renyuzhuo.rgithubandroidsdk.net.user.UserInfoClientListener;

public class BaseActivity extends Activity implements UserInfoClientListener, RepoClientListener, LoginClientListener, EventClientListener {

    @Override
    public void onGetUserInfoSuccess() {

    }

    @Override
    public void onGetOtherUserInfoSuccess(OtherUserInfoDetailBean otherUserInfoBean) {

    }

    @Override
    public void onGetRepoList(List<RepoBean> repoBeanList) {

    }

    @Override
    public void onGetRepo(RepoBean repoBean) {

    }

    @Override
    public void onGetUserList(List<OtherUserInfoBean> otherUserInfoBeenList) {

    }

    @Override
    public void onLoginSuccess(AccessTokenBean accessToken) {

    }

    @Override
    public void onGetRepoEvent(List<EventBean> eventBeen) {

    }

    @Override
    public void onGetUserEvent(List<EventBean> eventBeen) {

    }
}
