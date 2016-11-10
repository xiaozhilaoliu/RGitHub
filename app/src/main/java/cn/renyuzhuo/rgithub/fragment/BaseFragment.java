package cn.renyuzhuo.rgithub.fragment;

import android.app.Fragment;
import android.content.Context;
import android.widget.Toast;

import java.io.File;
import java.util.List;

import cn.renyuzhuo.rgithub.R;
import cn.renyuzhuo.rgithubandroidsdk.Dialog.LogoutListener;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.event.EventBean;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.repo.RepoBean;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.search.SearchBean;
import cn.renyuzhuo.rgithubandroidsdk.net.Event.EventClientListener;
import cn.renyuzhuo.rgithubandroidsdk.net.search.SearchClientListener;
import cn.renyuzhuo.rlog.rlog;

/**
 * Created by renyuzhuo on 16-10-28.
 */
public class BaseFragment extends Fragment implements EventClientListener, SearchClientListener, LogoutListener {
    @Override
    public void onGetRepoEvent(List<EventBean> eventBeen) {

    }

    @Override
    public void onGetUserEvent(List<EventBean> eventBeen) {

    }

    @Override
    public void onGetSearchResult(SearchBean searchBean) {

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
        Toast.makeText(fragment.getActivity(), getString(R.string.logout_success), Toast.LENGTH_SHORT).show();
        String chahePath = getActivity().getCacheDir().getPath() + "/app_webview";
        rlog.d(chahePath);
        clearCacheFolder(getActivity().getCacheDir());
        clearCacheFolder(new File(chahePath));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
