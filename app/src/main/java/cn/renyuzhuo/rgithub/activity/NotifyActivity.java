package cn.renyuzhuo.rgithub.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import cn.renyuzhuo.rgithub.R;
import cn.renyuzhuo.rgithub.adapter.NotifyAdapter;
import cn.renyuzhuo.rgithub.utils.GitHubData;
import cn.renyuzhuo.rgithub.utils.OpenWeb;
import cn.renyuzhuo.rgithubandroidsdk.Dialog.LoadingDialog;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.notify.NotifyBean;
import cn.renyuzhuo.rgithubandroidsdk.net.notify.NotifyClient;

public class NotifyActivity extends BaseListViewActivity {

    Context context;
    private static boolean isFirst = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        context = this;
        listView = (ListView) findViewById(R.id.listview);
        if (isFirst) {
            LoadingDialog.openLoadingDialogLoading(context);
            NotifyClient.getNotifyList(this);
        } else {
            showView();
        }
    }

    private void showView() {
        adapter = new NotifyAdapter(context, GitHubData.getNotifyBeanList());
        initListView();
    }

    @Override
    public void onGetNotifyListSuccess(List<NotifyBean> notifyBeen) {
        LoadingDialog.closeDialog();
        GitHubData.setNotifyBeanList(notifyBeen);
        isFirst = false;
        if (GitHubData.getNotifyBeanList().size() == 0) {
            Toast.makeText(context, getString(R.string.no_notify), Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        adapter = new NotifyAdapter(context, notifyBeen);
        initListView();
    }

    @Override
    public void afterInitListView() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NotifyAdapter.ViewHolder viewHolder = (NotifyAdapter.ViewHolder) view.getTag();
                OpenWeb.open(viewHolder.getUrl());
            }
        });
    }
}
