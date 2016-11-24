package cn.renyuzhuo.rgithub.activity;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.renyuzhuo.rgithub.R;
import cn.renyuzhuo.rgithub.utils.OpenWeb;
import cn.renyuzhuo.rlog.rlog;

public class SettingActivity extends Activity {

    Context context;
    TextView name, bio;
    LinearLayout lay1, repos;

    TextView gotoRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        context = this;

        name = (TextView) findViewById(R.id.name);
        bio = (TextView) findViewById(R.id.bio);
        lay1 = (LinearLayout) findViewById(R.id.lay1);
        repos = (LinearLayout) findViewById(R.id.repos);
        gotoRepo = (TextView) findViewById(R.id.goto_repo);
        gotoRepo.setText(getString(R.string.repo_index));

        name.setText(getString(R.string.app_name));

        String versionName = null;
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            rlog.d("get package info err");
        }
        if (versionName != null) {
            bio.setText(getString(R.string.about_description) + " ( " + versionName + " )");
        } else {
            bio.setText(getString(R.string.about_description));
        }
        lay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenWeb.open("http://renyuzhuo.cn");
            }
        });

        repos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenWeb.open("http://github.com/RWebRTC/RGitHub");
            }
        });
    }
}
