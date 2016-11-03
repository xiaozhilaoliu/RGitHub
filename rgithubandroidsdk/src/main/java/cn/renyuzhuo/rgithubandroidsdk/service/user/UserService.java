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
    Observable<List<OtherUserInfoBean>> getUserFollowersList(@Header("Authorization") String token,
                                                             @Path("username") String username);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "User-Agent: RGitHub"
    })
    @GET("users/{username}/following")
    Observable<List<OtherUserInfoBean>> getUserFollowingList(@Header("Authorization") String token,
                                                             @Path("username") String username);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "User-Agent: RGitHub"
    })
    @GET("users/{username}")
    Observable<OtherUserInfoDetailBean> getOtherUserInfo(@Header("Authorization") String token,
                                                         @Path("username") String username);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "User-Agent: RGitHub"
    })
    @GET("users/{username}/following")
    Observable<List<OtherUserInfoBean>> getUserFollowingMore(@Header("Authorization") String token,
                                                             @Path("username") String username,
                                                             @Query("page") int page);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "User-Agent: RGitHub"
    })
    @GET("users/{username}/followers")
    Observable<List<OtherUserInfoBean>> getUserFollowersMore(@Header("Authorization") String token,
                                                             @Path("username") String username,
                                                             @Query("page") int page);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "User-Agent: RGitHub"
    })
    @GET("repos/{username}/{reponame}/{type}")
    Observable<List<OtherUserInfoBean>> getRepoFollowList(@Header("Authorization") String token, @Path("username") String username,
                                                          @Path("reponame") String reponame, @Path("type") String type,
                                                          @Query("page") int page);

}
