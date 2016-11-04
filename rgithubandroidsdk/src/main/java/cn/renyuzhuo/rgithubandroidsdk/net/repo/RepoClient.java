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
    private static RepoService repoService = ApiBase.getInstance().build().create(RepoService.class);

    public static void getStarsList(final RepoClientListener repoClientListener, String username, int page) {
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

    public static void getReposList(final RepoClientListener repoClientListener, String username, int page) {
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

    public static void getRepo(final RepoClientListener repoClientListener, String username, String reponame) {
        repoService.getRepo("token " + Token.getAuthorization(), username, reponame)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<RepoBean>() {
                    @Override
                    public void call(RepoBean repoBean) {
                        if (repoClientListener != null) {
                            repoClientListener.onGetRepo(repoBean);
                        }
                    }
                });
    }

    public static void getRepoForks(final RepoClientListener repoClientListener, String username, String reponame, String type, int page) {
        repoService.getRepoList("token " + Token.getAuthorization(), username, reponame, type, page)
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
