package cn.renyuzhuo.rgithubandroidsdk.service.issues;

import java.util.List;

import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.issues.IssuesBean;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by renyuzhuo on 16-11-11.
 */
public interface IssuesService {

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "User-Agent: RGitHub"
    })
    @GET("issues")
    Observable<List<IssuesBean>> getIssues(@Header("Authorization") String token, @Query("sort") String sort,
                                           @Query("filter") String filter, @Query("state") String state, @Query("page") int page);
}
