package cn.renyuzhuo.rgithub;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import cn.renyuzhuo.autoupdate.UpdateMain;
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

    ImageButton searchTrending;
    boolean isSearching = false;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UpdateMain.getUpdateMain(this).ifNeedUpdate("RWebRTC", "RGitHub", "master", "version");

        appTitle = (TextView) findViewById(R.id.app_title);
        searchTrending = (ImageButton) findViewById(R.id.search_trending);

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
        setOnClickListener();
        radioGroup.check(R.id.first);
        RGitHubApplication.isLogin = true;
        LoadingDialog.closeDialog();
    }

    private void setOnClickListener() {
        rlog.d("set down redio listener");
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        fragmentFactory = new FragmentFactory();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                group.check(checkedId);
                if (checkedId != R.id.first) {
                    searchTrending.setVisibility(View.GONE);
                } else {
                    searchTrending.setVisibility(View.VISIBLE);
                }
                switch (checkedId) {
                    case R.id.first: {
                        initSearchingView();
                        return;
                    }
                    case R.id.second: {
                        appTitle.setText(getString(R.string.fragment_second_title));
                        break;
                    }
                    case R.id.third: {
                        appTitle.setText(getString(R.string.fragment_third_title));
                        break;
                    }
                    case R.id.fourth: {
                        appTitle.setText(getString(R.string.fragment_fourth_title));
                        break;
                    }
                    default: {
                        appTitle.setText(getString(R.string.app_name));
                        break;
                    }
                }
                fragmentFactory.replaceFragment(RGitHubMainActivity.this, checkedId);
            }
        });

        searchTrending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSearching = !isSearching;
                initSearchingView();
            }
        });
    }

    private void initSearchingView() {
        if (isSearching) {
            appTitle.setText(getString(R.string.search));
            searchTrending.setBackgroundResource(R.drawable.trending);
            fragmentFactory.replaceFragment(RGitHubMainActivity.this, R.id.search_icon);
        } else {
            appTitle.setText(getString(R.string.fragment_first_title));
            searchTrending.setBackgroundResource(R.drawable.search);
            fragmentFactory.replaceFragment(RGitHubMainActivity.this, R.id.trending_icon);
        }
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
        if (fragmentFactory != null) {
            fragmentFactory.onDestroy();
        }
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
