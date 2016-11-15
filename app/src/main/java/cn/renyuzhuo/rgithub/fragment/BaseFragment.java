package cn.renyuzhuo.rgithub.fragment;

import android.app.Fragment;
import android.content.Context;
import android.widget.Toast;

import java.io.File;
import java.util.List;

import cn.renyuzhuo.rgithub.R;
import cn.renyuzhuo.rgithubandroidsdk.Dialog.LoadingDialog;
import cn.renyuzhuo.rgithubandroidsdk.Dialog.LogoutListener;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.event.EventBean;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.issues.IssuesBean;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.repo.RepoBean;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.search.SearchBean;
import cn.renyuzhuo.rgithubandroidsdk.net.Event.EventClientListener;
import cn.renyuzhuo.rgithubandroidsdk.net.issues.IssuesClientListener;
import cn.renyuzhuo.rgithubandroidsdk.net.search.SearchClientListener;
import cn.renyuzhuo.rlog.rlog;

/**
 * Created by renyuzhuo on 16-10-28.
 */
public class BaseFragment extends Fragment implements EventClientListener, SearchClientListener, LogoutListener, IssuesClientListener {
    @Override
    public void onGetRepoEvent(List<EventBean> eventBeen) {
    }

    @Override
    public void onGetUserEvent(List<EventBean> eventBeen) {
    }

    @Override
    public void onGetSearchResult(String key, SearchBean searchBean) {
    }

    @Override
    public void onGetIssues(List<IssuesBean> issuesBeanList) {
    }

    private int clearCacheFolder(File dir) {
        int deletedFiles = 0;
        if (dir != null && dir.isDirectory()) {
            try {
                for (File child : dir.listFiles()) {
                    if (child.isDirectory()) {
                        deletedFiles += clearCacheFolder(child);
                    }
                    if (child.delete()) {
                        deletedFiles++;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return deletedFiles;
    }

    @Override
    public void logout(Fragment fragment) {
        rlog.d("logout");
        Toast.makeText(fragment.getActivity(), getString(R.string.logout_success), Toast.LENGTH_SHORT).show();
        String cachePath = getActivity().getCacheDir().getPath();
        rlog.d(cachePath);
        String webViewPath = cachePath + "/app_webview";
        rlog.d(webViewPath);

        rlog.d("begin clean cache");
        clearCacheFolder(new File(cachePath));
        clearCacheFolder(new File(webViewPath));
        rlog.d("finish clean cache and will exit");
        System.exit(0);
    }

}
