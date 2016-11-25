package cn.renyuzhuo.rgithubandroidsdk.net.repo;

import java.util.List;

import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.repo.Readme;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.repo.RepoBean;
import cn.renyuzhuo.rgithubandroidsdk.net.BaseListener;

/**
 * Created by renyuzhuo on 16-11-1.
 */
public interface RepoClientListener extends BaseListener {
    void onGetRepoList(List<RepoBean> repoBeanList);

    void onGetRepo(RepoBean repoBean);

    void onGetReadme(Readme readme);

    void onNotStar(String username, String reponame);

    void onHaveStar(String username, String reponame);

    void onStarSuccess(String username, String reponame);

    void onUnStarSuccess(String username, String reponame);
}
