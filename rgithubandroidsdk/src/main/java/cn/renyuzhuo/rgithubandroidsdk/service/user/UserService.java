package cn.renyuzhuo.rgithubandroidsdk.service.user;

import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.user.UserInfoBean;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
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
}
