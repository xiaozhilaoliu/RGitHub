package cn.renyuzhuo.rgithubandroidsdk.net.user;

import java.util.List;

import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.Token;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.user.OtherUserInfoBean;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.user.OtherUserInfoDetailBean;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.user.UserInfoBean;
import cn.renyuzhuo.rgithubandroidsdk.net.Base.ApiBase.ApiBase;
import cn.renyuzhuo.rgithubandroidsdk.net.result.MySubscriber;
import cn.renyuzhuo.rgithubandroidsdk.service.user.UserService;
import cn.renyuzhuo.rlog.rlog;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by renyuzhuo on 16-10-31.
 */
public class UserInfoClient {
    private static UserService userService = ApiBase.getInstance().build().create(UserService.class);

    public static void getLoginUserInfo(final UserInfoClientListener userInfoClientListener) {
        userService.getUserInfo("token " + Token.getAuthorization())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber<UserInfoBean>() {
                    @Override
                    public void onNext(UserInfoBean userInfoBean) {
                        rlog.d(userInfoBean);
                        UserInfoBean.getInstance().setUserInfoBean(userInfoBean);
                        if (userInfoClientListener != null) {
                            userInfoClientListener.onGetUserInfoSuccess();
                        }
                    }
                });
    }

    public static void getOtherUserInfo(final UserInfoClientListener userInfoClientListener, String username) {
        userService.getOtherUserInfo("token " + Token.getAuthorization(), username)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber<OtherUserInfoDetailBean>() {
                    @Override
                    public void onNext(OtherUserInfoDetailBean otherUserInfoDetailBean) {
                        if (userInfoClientListener != null) {
                            userInfoClientListener.onGetOtherUserInfoSuccess(otherUserInfoDetailBean);
                        }
                    }
                });
    }

    public static void getUserFollowingList(final UserInfoClientListener userInfoClientListener, String username, int page) {
        userService.getUserFollowingMore("token " + Token.getAuthorization(), username, page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber<List<OtherUserInfoBean>>() {
                    @Override
                    public void onNext(List<OtherUserInfoBean> otherUserInfoBeenList) {
                        if (userInfoClientListener != null) {
                            userInfoClientListener.onGetUserList(otherUserInfoBeenList);
                        }
                    }
                });
    }

    public static void getUserFollowersList(final UserInfoClientListener userInfoClientListener, final String username, int page) {
        userService.getUserFollowersMore("token " + Token.getAuthorization(), username, page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber<List<OtherUserInfoBean>>() {
                    @Override
                    public void onNext(List<OtherUserInfoBean> otherUserInfoBeenList) {
                        if (userInfoClientListener != null) {
                            userInfoClientListener.onGetUserList(otherUserInfoBeenList);
                        }
                    }
                });
    }

    public static void getRepoFollows(UserInfoClientListener userInfoClientListener, String username, String reponame, String type, int page) {
        getRepoOtherUserList(userInfoClientListener, username, reponame, type, page);
    }


    public static void getRepoWatchers(UserInfoClientListener userInfoClientListener, String username, String reponame, String type, int page) {
        getRepoOtherUserList(userInfoClientListener, username, reponame, type, page);
    }

    private static void getRepoOtherUserList(final UserInfoClientListener userInfoClientListener, String username, String reponame, String type, int page) {
        userService.getRepoFollowList("token " + Token.getAuthorization(), username, reponame, type, page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber<List<OtherUserInfoBean>>() {
                    @Override
                    public void onNext(List<OtherUserInfoBean> otherUserInfoBeenList) {
                        if (userInfoClientListener != null) {
                            userInfoClientListener.onGetUserList(otherUserInfoBeenList);
                        }
                    }
                });
    }

    public static void isFollowing(final UserInfoClientListener userInfoClientListener, final String username) {
        userService.isFollowing("token " + Token.getAuthorization(), username)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber<Void>() {
                    @Override
                    public void onNext(Void aVoid) {
                        if (userInfoClientListener != null) {
                            userInfoClientListener.onFollowing(username);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (userInfoClientListener != null) {
                            userInfoClientListener.onNotFollowing(username);
                        }
                    }
                });
    }

    public static void notFollowing(final UserInfoClientListener userInfoClientListener, String username) {
        userService.notFollowing("token " + Token.getAuthorization(), username)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber<Void>() {
                    @Override
                    public void onNext(Void aVoid) {
                        if (userInfoClientListener != null) {
                            userInfoClientListener.onDeletetFollowingSuccess();
                        }
                    }
                });

    }

    public static void following(final UserInfoClientListener userInfoClientListener, String username) {
        userService.following("token " + Token.getAuthorization(), username)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber<Void>() {
                    @Override
                    public void onNext(Void aVoid) {
                        if (userInfoClientListener != null) {
                            userInfoClientListener.onPutFollowingSuccess();
                        }
                    }
                });
    }
}
