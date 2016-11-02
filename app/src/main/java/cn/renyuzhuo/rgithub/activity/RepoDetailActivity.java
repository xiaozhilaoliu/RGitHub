package cn.renyuzhuo.rgithub.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.HashMap;
import java.util.Map;

import cn.renyuzhuo.rgithub.R;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.repo.RepoBean;
import cn.renyuzhuo.rlog.rlog;

public class RepoDetailActivity extends Activity {

    private static Map<String, RepoBean> mapRepos = new HashMap<>();
    Intent intent;
    private RepoBean repoBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_detail);
        intent = getIntent();
        String fullname = intent.getStringExtra("fullname");
        repoBean = mapRepos.get(fullname);
        rlog.d(repoBean);

    }

    public static void startRepoDetailActivity(Context context, RepoBean repoBean) {
        Intent intent = new Intent(context, RepoDetailActivity.class);
        mapRepos.put(repoBean.getFull_name(), repoBean);
        intent.putExtra("fullname", repoBean.getFull_name());
        context.startActivity(intent);
    }
}
