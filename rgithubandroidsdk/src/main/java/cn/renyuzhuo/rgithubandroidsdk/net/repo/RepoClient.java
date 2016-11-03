package cn.renyuzhuo.rgithubandroidsdk.net.repo;

import java.util.List;

import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.Token;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.repo.RepoBean;
import cn.renyuzhuo.rgithubandroidsdk.net.Base.ApiBase.ApiBase;
import cn.renyuzhuo.rgithubandroidsdk.service.repo.RepoService;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by renyuzhuo on 16-11-1.
 */
public class RepoClient {
    private static RepoClientListener repoClientListener;
    private static RepoService repoService = ApiBase.getInstance().build().create(RepoService.class);

    public static void setRepoClientListener(RepoClientListener repoClientListener) {
        RepoClient.repoClientListener = repoClientListener;
    }

    public static void getStarsList(String username, int page) {
        repoService.getStarList("token " + Token.getAuthorization(), username, page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<RepoBean>>() {
                    @Override
                    public void call(List<RepoBean> repoBeanList) {
                        if (repoClientListener != null) {
                            repoClientListener.onGetRepoList(repoBeanList);
                        }
                    }
                });
    }

    public static void getReposList(String username, int page) {
        repoService.getRepoList("token " + Token.getAuthorization(), username, page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<RepoBean>>() {
                    @Override
                    public void call(List<RepoBean> repoBeanList) {
                        if (repoClientListener != null) {
                            repoClientListener.onGetRepoList(repoBeanList);
                        }
                    }
                });
    }
}
