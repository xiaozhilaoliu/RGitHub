package cn.renyuzhuo.rgithub.fragment;

import android.app.Fragment;

import java.util.List;

import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.event.EventBean;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.repo.RepoBean;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.search.SearchBean;
import cn.renyuzhuo.rgithubandroidsdk.net.Event.EventClientListener;
import cn.renyuzhuo.rgithubandroidsdk.net.search.SearchClientListener;

/**
 * Created by renyuzhuo on 16-10-28.
 */
public class BaseFragment extends Fragment implements EventClientListener, SearchClientListener {
    @Override
    public void onGetRepoEvent(List<EventBean> eventBeen) {

    }

    @Override
    public void onGetUserEvent(List<EventBean> eventBeen) {

    }

    @Override
    public void onGetSearchResult(SearchBean searchBean) {

    }
}
