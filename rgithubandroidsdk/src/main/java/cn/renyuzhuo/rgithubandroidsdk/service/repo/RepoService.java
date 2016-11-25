package cn.renyuzhuo.rgithubandroidsdk.service.repo;

import java.util.List;

import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.repo.Readme;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.repo.RepoBean;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Completable;
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
    Observable<List<RepoBean>> getStarList(@Header("Authorization") String token, @Path("username") String username, @Query("page") int page);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "User-Agent: RGitHub"
    })
    @GET("users/{username}/repos")
    Observable<List<RepoBean>> getRepoList(@Header("Authorization") String token, @Path("username") String username, @Query("page") int page);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "User-Agent: RGitHub"
    })
    @GET("repos/{username}/{reponame}")
    Observable<RepoBean> getRepo(@Header("Authorization") String token, @Path("username") String username, @Path("reponame") String reponame);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "User-Agent: RGitHub"
    })
    @GET("repos/{username}/{reponame}/{type}")
    Observable<List<RepoBean>> getRepoList(@Header("Authorization") String token, @Path("username") String username, @Path("reponame") String reponame, @Path("type") String type, @Query("page") int page);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "User-Agent: RGitHub"
    })
    @GET("repos/{username}/{reponame}/readme")
    Observable<Readme> getRepoReadMe(@Header("Authorization") String token, @Path("username") String username, @Path("reponame") String reponame);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "User-Agent: RGitHub"
    })
    @GET("/user/starred/{username}/{reponame}")
    Observable<Void> ifStarRepo(@Header("Authorization") String token, @Path("username") String username, @Path("reponame") String reponame);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "User-Agent: RGitHub"
    })
    @PUT("/user/starred/{username}/{reponame}")
    Observable<Void> starRepo(@Header("Authorization") String token, @Path("username") String username, @Path("reponame") String reponame);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "User-Agent: RGitHub"
    })
    @DELETE("/user/starred/{username}/{reponame}")
    Observable<Void> unStarRepo(@Header("Authorization") String token, @Path("username") String username, @Path("reponame") String reponame);
}
