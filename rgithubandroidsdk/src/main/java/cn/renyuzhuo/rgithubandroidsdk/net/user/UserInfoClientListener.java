package cn.renyuzhuo.rgithubandroidsdk.net.user;

import java.util.List;

import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.user.OtherUserInfoBean;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.user.OtherUserInfoDetailBean;
import cn.renyuzhuo.rgithubandroidsdk.net.BaseListener;

/**
 * Created by renyuzhuo on 16-10-31.
 */
public interface UserInfoClientListener extends BaseListener {
    void onGetUserInfoSuccess();

    void onGetUserList(List<OtherUserInfoBean> otherUserInfoBeenList);

    void onGetUserList(List<OtherUserInfoBean> otherUserInfoBeenList, String type);

    void onGetOtherUserInfoSuccess(OtherUserInfoDetailBean otherUserInfoBean);

    void onNotFollowing(String username);

    void onFollowing(String username);

    void onDeletetFollowingSuccess();

    void onPutFollowingSuccess();

}
