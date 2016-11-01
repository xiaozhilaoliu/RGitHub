package cn.renyuzhuo.rgithubandroidsdk.service.user;

import java.util.List;

import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.repo.RepoBean;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by renyuzhuo on 16-11-1.
 */
public interface RepoService {
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "User-Agent: RGitHub"
    })
    @GET("users/{username}/starred")
    Observable<List<RepoBean>> getStarList(@Path("username") String username);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "User-Agent: RGitHub"
    })
    @GET("users/{username}/repos")
    Observable<List<RepoBean>> getRepoList(@Path("username") String username);

}
