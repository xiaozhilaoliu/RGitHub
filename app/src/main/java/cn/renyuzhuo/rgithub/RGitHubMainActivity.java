package cn.renyuzhuo.rgithub;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import cn.renyuzhuo.rautoupdate.UpdateMain;
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

        // 应用自动更新检测
        UpdateMain.getUpdateMain(this).ifNeedUpdate("RWebRTC", "RGitHub", "master", "version");
        rlog.d("auto update");

        appTitle = (TextView) findViewById(R.id.app_title);
        searchTrending = (ImageButton) findViewById(R.id.search_trending);

        if (RGitHubApplication.isLogin) {
            // 如果登陆成功，进行界面初始化
            rlog.d("login success and init view");
            initView();
        } else {
            rlog.d("login");
            login();
        }
    }

    /**
     * 初始化界面
     */
    private void initView() {
        rlog.d();
        rlog.d("init finish");
        rlog.d();
        setOnClickListener();
        rlog.d("select the first fragment by default");
        radioGroup.check(R.id.fourth);
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
                        // 第一个按钮，可能是search或者是treading，需要分别进行处理
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

        rlog.d("set search and treading listener");
        searchTrending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSearching = !isSearching;
                initSearchingView();
            }
        });
    }

    /**
     * 初始化搜索按钮
     */
    private void initSearchingView() {
        if (isSearching) {
            rlog.d("search");
            appTitle.setText(getString(R.string.search));
            searchTrending.setBackgroundResource(R.drawable.trending);
            fragmentFactory.replaceFragment(RGitHubMainActivity.this, R.id.search_icon);
        } else {
            rlog.d("treading");
            appTitle.setText(getString(R.string.fragment_first_title));
            searchTrending.setBackgroundResource(R.drawable.search);
            fragmentFactory.replaceFragment(RGitHubMainActivity.this, R.id.trending_icon);
        }
    }

    private void login() {
        rlog.d("login begin");
        LoadingDialog.openLoadingDialogLogin(this);
        LoginClient.setLoginSuccessListener(this);
        LoginClient.login(this, RGitHubApplication.clientId, RGitHubApplication.clientSecret, RGitHubApplication.redirectUri);
    }

    @Override
    public void onLoginSuccess(AccessTokenBean accessToken) {
        rlog.d("login success");
        rlog.d("get userInfo");
        UserInfoClient.getLoginUserInfo(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OtherUserInfoActivity.clear();
        if (fragmentFactory != null) {
            fragmentFactory.onDestroy();
        }
        rlog.d("exit application");
        System.exit(0);
    }

    private long mPressedTime = 0;

    @Override
    public void onBackPressed() {
        long mNowTime = System.currentTimeMillis();
        if ((mNowTime - mPressedTime) > 1000) {
            rlog.d("back 1 times");
            Toast.makeText(this, getString(R.string.again), Toast.LENGTH_SHORT).show();
            mPressedTime = mNowTime;
        } else {
            rlog.d("back 2 times and exit");
            this.finish();
        }
    }

    @Override
    public void onGetUserInfoSuccess() {
        rlog.d("get userInfo success");
        initView();
    }

}
