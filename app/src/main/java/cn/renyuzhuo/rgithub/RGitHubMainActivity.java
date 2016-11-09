package cn.renyuzhuo.rgithub;

import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import cn.renyuzhuo.rgithub.activity.BaseActivity;
import cn.renyuzhuo.rgithub.activity.OtherUserInfoActivity;
import cn.renyuzhuo.rgithub.fragment.FragmentFactory;
import cn.renyuzhuo.rgithubandroidsdk.Dialog.LoadingDialog;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.AccessTokenBean;
import cn.renyuzhuo.rgithubandroidsdk.net.login.LoginClient;
import cn.renyuzhuo.rgithubandroidsdk.net.user.UserInfoClient;
import cn.renyuzhuo.rlog.rlog;

public class RGitHubMainActivity extends BaseActivity {

    FragmentFactory fragmentFactory;
    RadioGroup radioGroup;
    TextView appTitle;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appTitle = (TextView) findViewById(R.id.app_title);

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
                switch (checkedId) {
                    case R.id.first: {
                        appTitle.setText(getString(R.string.fragment_first_title));
                        break;
                    }
                    case R.id.second:{
                        appTitle.setText(getString(R.string.fragment_second_title));
                        break;
                    }
                    case R.id.third:{
                        appTitle.setText(getString(R.string.fragment_third_title));
                        break;
                    }
                    case R.id.fourth:{
                        appTitle.setText(getString(R.string.fragment_fourth_title));
                        break;
                    }
                    default: {
                        appTitle.setText(getString(R.string.app_name));
                        break;
                    }
                }
                group.check(checkedId);
                fragmentFactory.replaceFragment(RGitHubMainActivity.this, checkedId);
            }
        });
    }

    private void initView() {
        fragmentFactory = new FragmentFactory();
    }

    private void login() {
        LoadingDialog.openLoadingDialogLogin(this);
        LoginClient.setLoginSuccessListener(this);
        LoginClient.login(this, RGitHubApplication.clientId, RGitHubApplication.clientSecret, RGitHubApplication.redirectUri);
    }

    @Override
    public void onLoginSuccess(AccessTokenBean accessToken) {
        rlog.d("login success");
        UserInfoClient.getLoginUserInfo(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OtherUserInfoActivity.clear();
        fragmentFactory.onDestroy();
    }

    private long mPressedTime = 0;

    @Override
    public void onBackPressed() {
        long mNowTime = System.currentTimeMillis();
        if ((mNowTime - mPressedTime) > 1000) {
            Toast.makeText(this, getString(R.string.again), Toast.LENGTH_SHORT).show();
            mPressedTime = mNowTime;
        } else {
            this.finish();
            System.exit(0);
        }
    }

    @Override
    public void onGetUserInfoSuccess() {
        initFinish();
    }
}
