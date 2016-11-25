package cn.renyuzhuo.rgithubandroidsdk.net.repo;

import java.util.List;

import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.Token;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.repo.Readme;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.repo.RepoBean;
import cn.renyuzhuo.rgithubandroidsdk.net.Base.ApiBase.ApiBase;
import cn.renyuzhuo.rgithubandroidsdk.net.result.MySubscriber;
import cn.renyuzhuo.rgithubandroidsdk.service.repo.RepoService;
import cn.renyuzhuo.rlog.rlog;
import rx.android.schedulers.AndroidSchedulers;
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
                .subscribe(new MySubscriber<List<RepoBean>>() {
                    @Override
                    public void onNext(List<RepoBean> repoBeanList) {
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
                .subscribe(new MySubscriber<List<RepoBean>>() {
                    @Override
                    public void onNext(List<RepoBean> repoBeanList) {
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
                .subscribe(new MySubscriber<RepoBean>() {
                    @Override
                    public void onNext(RepoBean repoBean) {
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
                .subscribe(new MySubscriber<List<RepoBean>>() {
                    @Override
                    public void onNext(List<RepoBean> repoBeanList) {
                        if (repoClientListener != null) {
                            repoClientListener.onGetRepoList(repoBeanList);
                        }
                    }
                });
    }

    public static void getRepoReadMe(final RepoClientListener repoClientListener, String username, String reponame) {
        repoService.getRepoReadMe("token " + Token.getAuthorization(), username, reponame)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber<Readme>() {
                    @Override
                    public void onNext(Readme readme) {
                        if (repoClientListener != null) {
                            repoClientListener.onGetReadme(readme);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        rlog.d("no readme");
                    }
                });
    }

    public static void ifStarRepo(final RepoClientListener repoClientListener, final String username, final String reponame) {
        repoService.ifStarRepo("token " + Token.getAuthorization(), username, reponame)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber<Void>() {
                    @Override
                    public void onNext(Void aVoid) {
                        rlog.d("ifStarRepo: Success");
                        if (repoClientListener != null) {
                            repoClientListener.onHaveStar(username, reponame);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        rlog.d("ifStarRepo: ERR");
                        if (repoClientListener != null) {
                            repoClientListener.onNotStar(username, reponame);
                        }
                    }
                });
    }

    public static void starRepo(final RepoClientListener repoClientListener, final String username, final String reponame) {
        repoService.starRepo("token " + Token.getAuthorization(), username, reponame)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber<Void>() {
                    @Override
                    public void onNext(Void aVoid) {
                        if (repoClientListener != null) {
                            repoClientListener.onStarSuccess(username, reponame);
                        }
                    }
                });
    }

    public static void unStarRepo(final RepoClientListener repoClientListener, final String username, final String reponame) {
        repoService.unStarRepo("token " + Token.getAuthorization(), username, reponame)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber<Void>() {
                    @Override
                    public void onNext(Void aVoid) {
                        if (repoClientListener != null) {
                            repoClientListener.onUnStarSuccess(username, reponame);
                        }
                    }
                });
    }
}
