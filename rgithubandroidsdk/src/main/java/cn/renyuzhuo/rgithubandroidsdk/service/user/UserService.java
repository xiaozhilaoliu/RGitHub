package cn.renyuzhuo.rgithubandroidsdk.service.user;

import java.util.List;

import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.user.OtherUserInfoBean;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.user.OtherUserInfoDetailBean;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.user.UserInfoBean;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by renyuzhuo on 16-10-31.
 */
public interface UserService {
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "User-Agent: RGitHub"
    })
    @GET("user")
    Observable<UserInfoBean> getUserInfo(@Header("Authorization") String token);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "User-Agent: RGitHub"
    })
    @GET("users/{username}/followers")
    Observable<List<OtherUserInfoBean>> getUserFollowersList(@Path("username") String username);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "User-Agent: RGitHub"
    })
    @GET("users/{username}/following")
    Observable<List<OtherUserInfoBean>> getUserFollowingList(@Path("username") String username);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "User-Agent: RGitHub"
    })
    @GET("users/{username}")
    Observable<OtherUserInfoDetailBean> getOtherUserInfo(@Path("username") String username);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "User-Agent: RGitHub"
    })
    @GET("users/{username}/following")
    Observable<List<OtherUserInfoBean>> getUserFollowingMore(@Path("username") String username, @Query("page") int page);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "User-Agent: RGitHub"
    })
    @GET("users/{username}/followers")
    Observable<List<OtherUserInfoBean>> getUserFollowersMore(@Path("username") String username, @Query("page") int page);
}
