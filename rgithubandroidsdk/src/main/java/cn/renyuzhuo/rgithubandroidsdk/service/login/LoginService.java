package cn.renyuzhuo.rgithubandroidsdk.service.login;

import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.AccessTokenBean;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.OAuthBean;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by renyuzhuo on 16-10-31.
 */
public interface LoginService {
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "User-Agent: RGitHub"
    })
    @POST("login/oauth/access_token")
    Observable<AccessTokenBean> getToken(@Body OAuthBean oAuthBean);
}
