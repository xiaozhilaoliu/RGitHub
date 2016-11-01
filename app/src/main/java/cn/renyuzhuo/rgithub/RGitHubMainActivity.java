package cn.renyuzhuo.rgithub;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import java.util.List;

import cn.renyuzhuo.rgithub.activity.OtherUserInfoActivity;
import cn.renyuzhuo.rgithub.fragment.FragmentFactory;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.AccessTokenBean;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.user.OtherUserInfoBean;
import cn.renyuzhuo.rgithubandroidsdk.net.login.LoginClient;
import cn.renyuzhuo.rgithubandroidsdk.net.login.LoginClientListener;
import cn.renyuzhuo.rgithubandroidsdk.net.user.UserInfoClient;
import cn.renyuzhuo.rgithubandroidsdk.net.user.UserInfoClientListener;
import cn.renyuzhuo.rlog.rlog;
import cn.renyuzhuo.rwidget.Dialog.LoadingDialog;

public class RGitHubMainActivity extends AppCompatActivity implements LoginClientListener, UserInfoClientListener {

    FragmentFactory fragmentFactory;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (RGitHubApplication.isLogin) {
            initFinish();
        } else {
            login();
        }
    }

    private void initFinish() {
        rlog.d();
        rlog.d("init finish");
        rlog.d();
        initView();
        setOnClickListener();
        radioGroup.check(R.id.first);
        RGitHubApplication.isLogin = true;
        LoadingDialog.closeDialog();
    }

    private void setOnClickListener() {
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                group.check(checkedId);
                fragmentFactory.replaceFragment(checkedId);
            }
        });
    }

    private void initView() {
        fragmentFactory = new FragmentFactory(this);
    }

    private void login() {
        LoadingDialog.openLoadingDialogLogin(this);
        LoginClient.setLoginSuccessListener(this);
        LoginClient.login(this, RGitHubApplication.clientId, RGitHubApplication.clientSecret, RGitHubApplication.redirectUri);
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFail() {

    }

    @Override
    public void onLoginSuccess(AccessTokenBean accessToken) {
        rlog.d("login success");
        UserInfoClient.setUserInfoClientListener(this);
        UserInfoClient.getLoginUserInfo();
    }

    @Override
    public void onGetUserInfoSuccess() {
        initFinish();
    }

    @Override
    public void onGetUserList(List<OtherUserInfoBean> otherUserInfoBeenList) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OtherUserInfoActivity.clear();
    }
}
