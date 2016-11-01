package cn.renyuzhuo.rgithubandroidsdk.net.repo;

import java.util.List;

import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.repo.RepoBean;
import cn.renyuzhuo.rgithubandroidsdk.net.BaseListener;

/**
 * Created by renyuzhuo on 16-11-1.
 */
public interface RepoClientListener extends BaseListener {
    void onGetRepoList(List<RepoBean> repoBeanList);
}
