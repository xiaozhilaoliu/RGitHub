package cn.renyuzhuo.rgithubandroidsdk.service.search;

import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.search.SearchBean;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by renyuzhuo on 16-11-7.
 */
public interface SearchService {
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "User-Agent: RGitHub"
    })
    @GET("/search/repositories")
    Observable<SearchBean> getSearchResult(@Header("Authorization") String token, @Query("q") String keyWord, @Query("page") int page);
}
