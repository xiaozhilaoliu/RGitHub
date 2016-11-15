package cn.renyuzhuo.rgithub.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import cn.renyuzhuo.rgithub.R;
import cn.renyuzhuo.rgithub.activity.PageHelper;
import cn.renyuzhuo.rgithub.adapter.IssuesBeanAdapter;
import cn.renyuzhuo.rgithub.utils.OpenWeb;
import cn.renyuzhuo.rgithubandroidsdk.Dialog.LoadingDialog;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.issues.IssuesBean;
import cn.renyuzhuo.rgithubandroidsdk.net.issues.IssuesClient;
import cn.renyuzhuo.rlog.rlog;

public class ThirdFragment extends BaseListViewFragment {

    Context context;
    View view;

    private static boolean isFirst = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_third, container, false);
        context = getActivity();
        listView = (ListView) view.findViewById(R.id.listview);

        if (isFirst) {
            isFirst = false;
            pageHelper = new PageHelper();
            IssuesClient.getIssues(this, pageHelper.nextPage());
            LoadingDialog.openLoadingDialogLoading(context);
        } else {
            initListView();
        }

        return view;
    }

    @Override
    public void onGetIssues(List<IssuesBean> issuesBeanList) {
        LoadingDialog.closeDialog();
        rlog.d("get issues success");
        rlog.d(issuesBeanList);
        pageHelper.hasMoreOrNot(issuesBeanList.size());

        if (adapter != null) {
            adapter.addLists(issuesBeanList);
            return;
        }
        adapter = new IssuesBeanAdapter(context, issuesBeanList);
        initListView();
    }

    @Override
    public void loadMore() {
        if (!pageHelper.hasMore()) {
            if (pageHelper.showToast()) {
                Toast.makeText(context, getString(R.string.has_no_more), Toast.LENGTH_SHORT).show();
            }
            return;
        }
        LoadingDialog.openLoadingDialogLoading(context);
        IssuesClient.getIssues(this, pageHelper.nextPage());
    }

    @Override
    public void afterInitListView() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String issueUrl = ((IssuesBeanAdapter.ViewHolder) view.getTag()).getIssueUrl();
                OpenWeb.open(context, issueUrl);
            }
        });
    }
}
