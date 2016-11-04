package cn.renyuzhuo.rgithub.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import cn.renyuzhuo.rgithub.R;
import cn.renyuzhuo.rgithub.activity.PageHelper;
import cn.renyuzhuo.rgithub.adapter.UserEventAdapter;
import cn.renyuzhuo.rgithubandroidsdk.Dialog.LoadingDialog;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.event.EventBean;
import cn.renyuzhuo.rgithubandroidsdk.net.Event.EventClient;
import cn.renyuzhuo.rlog.rlog;

public class SecondFragment extends BaseListViewFragment {

    Context context;
    View view;
    private static boolean isFirst = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_second, container, false);
        rlog.d("onCreateView");
        context = getActivity();
        listView = (ListView) view.findViewById(R.id.listview);
        if (isFirst) {
            isFirst = false;
            pageHelper = new PageHelper();
            EventClient.getLoginUserEvent(this, pageHelper.nextPage());
            LoadingDialog.openLoadingDialogLoading(context);
        } else {
            initListView();
        }
        return view;
    }

    @Override
    public void onGetUserEvent(List<EventBean> eventBeen) {
        LoadingDialog.closeDialog();
        pageHelper.hasMoreOrNot(eventBeen.size());

        if (adapter != null) {
            ((UserEventAdapter) adapter).addEvent(eventBeen);
            return;
        }
        adapter = new UserEventAdapter(context, eventBeen);
        initListView();
    }

    @Override
    public void afterInitListView() {

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
        EventClient.getLoginUserEvent(this, pageHelper.nextPage());
    }
}
