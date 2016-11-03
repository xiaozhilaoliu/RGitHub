package cn.renyuzhuo.rgithub.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.nio.charset.CoderMalfunctionError;
import java.util.HashMap;
import java.util.Map;

import cn.renyuzhuo.rgithub.R;
import cn.renyuzhuo.rgithub.utils.DateUtil;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.repo.RepoBean;
import cn.renyuzhuo.rgithubandroidsdk.net.repo.RepoClient;
import cn.renyuzhuo.rlog.rlog;

public class RepoDetailActivity extends BaseActivity {

    private static Map<String, RepoBean> mapRepos = new HashMap<>();
    Intent intent;
    Context context;
    private RepoBean repoBean;
    ImageView avatar;
    TextView repoName;
    TextView description;
    String fullname;

    TextView starNum, watchNum, forkNum;

    TextView lock, language, issues, branch, calendar, tool, ownerName;
    LinearLayout ownerLinerar;

    LinearLayout eventsEvents, eventsWarn, eventsReadme;

    LinearLayout commit, pull, source;

    LinearLayout website;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_detail);
        context = this;
        intent = getIntent();
        fullname = intent.getStringExtra("fullname");
        repoBean = mapRepos.get(fullname);
        rlog.d(repoBean);
        findViewIds();
        if (repoBean != null) {
            initView();
            initListener();
        } else {
            String[] names = fullname.split("/");
            if (names != null && names.length == 2) {
                RepoClient.setRepoClientListener(this);
                RepoClient.getRepo(names[0], names[1]);
            }
        }
    }

    @Override
    public void onGetRepo(RepoBean repoBean) {
        this.repoBean = repoBean;
        mapRepos.put(fullname, repoBean);
        initView();
        initListener();
    }

    private void findViewIds() {
        avatar = (ImageView) findViewById(R.id.avatar);
        repoName = (TextView) findViewById(R.id.repo_name);
        description = (TextView) findViewById(R.id.description);

        starNum = (TextView) findViewById(R.id.stars_num);
        watchNum = (TextView) findViewById(R.id.watch_num);
        forkNum = (TextView) findViewById(R.id.forks_num);

        lock = (TextView) findViewById(R.id.lock);
        language = (TextView) findViewById(R.id.language);
        issues = (TextView) findViewById(R.id.issues);
        branch = (TextView) findViewById(R.id.branch);
        calendar = (TextView) findViewById(R.id.calendar);
        tool = (TextView) findViewById(R.id.tool);
        ownerName = (TextView) findViewById(R.id.owner_name);
        ownerLinerar = (LinearLayout) findViewById(R.id.owner_linerar);

        eventsEvents = (LinearLayout) findViewById(R.id.events_events);
        eventsWarn = (LinearLayout) findViewById(R.id.events_warn);
        eventsReadme = (LinearLayout) findViewById(R.id.events_readme);

        commit = (LinearLayout) findViewById(R.id.commit);
        pull = (LinearLayout) findViewById(R.id.pull);
        source = (LinearLayout) findViewById(R.id.source);
        website = (LinearLayout) findViewById(R.id.websit);

    }

    private void initView() {
        Picasso.with(context).load(repoBean.getOwner().getAvatar_url()).placeholder(R.drawable.logo).into(avatar);
        repoName.setText(repoBean.getName());
        description.setText(repoBean.getDescription());

        starNum.setText(String.valueOf(repoBean.getStargazers_count()));
        watchNum.setText(String.valueOf(repoBean.getWatchers_count()));
        forkNum.setText(String.valueOf(repoBean.getForks_count()));

        lock.setText(repoBean.getPrivate() ? getString(R.string.is_private) : getString(R.string.is_public));
        language.setText(repoBean.getLanguage());
        issues.setText(String.valueOf(repoBean.getOpen_issues_count()) + " Issues");
        branch.setText(repoBean.getDefault_branch());
        calendar.setText(DateUtil.formate(repoBean.getCreated_at()));
        tool.setText(String.valueOf(repoBean.getSize() / 1024) + " MB");
        ownerName.setText(repoBean.getOwner().getLogin());


    }

    private void initListener() {
        ownerLinerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OtherUserInfoActivity.startOtherUserInfoActivity(context, repoBean.getOwner().getLogin());
            }
        });

        eventsEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventActivity.startEventActivity(context, repoBean.getOwner().getLogin(), repoBean.getName());
            }
        });

        eventsWarn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        eventsReadme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        pull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        source.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public static void startRepoDetailActivity(Context context, RepoBean repoBean) {
        Intent intent = new Intent(context, RepoDetailActivity.class);
        mapRepos.put(repoBean.getFull_name(), repoBean);
        intent.putExtra("fullname", repoBean.getFull_name());
        context.startActivity(intent);
    }

    public static void startRepoDetailActivity(Context context, String repoName) {
        Intent intent = new Intent(context, RepoDetailActivity.class);
        intent.putExtra("fullname", repoName);
        context.startActivity(intent);
    }
}
