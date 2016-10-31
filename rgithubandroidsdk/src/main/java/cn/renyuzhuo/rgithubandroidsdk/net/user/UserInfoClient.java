package cn.renyuzhuo.rgithubandroidsdk.net.user;

import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.Token;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.user.UserInfoBean;
import cn.renyuzhuo.rgithubandroidsdk.net.Base.ApiBase.ApiBase;
import cn.renyuzhuo.rgithubandroidsdk.service.user.UserService;
import cn.renyuzhuo.rlog.rlog;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by renyuzhuo on 16-10-31.
 */
public class UserInfoClient {
    public static UserInfoClientListener userInfoClientListener;

    public static void getLoginUserInfo() {
        UserService userService = ApiBase.getInstance().build().create(UserService.class);
        userService.getUserInfo("token " + Token.getAuthorization())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<UserInfoBean>() {
                    @Override
                    public void call(UserInfoBean userInfoBean) {
                        rlog.d(userInfoBean);
                        UserInfoBean.getInstance().setUserInfoBean(userInfoBean);
                        if (userInfoClientListener != null) {
                            userInfoClientListener.onGetUserInfoSuccess();
                        }
                    }
                });
    }

    public static void setUserInfoClientListener(UserInfoClientListener userInfoClientListener) {
        UserInfoClient.userInfoClientListener = userInfoClientListener;
    }
}
