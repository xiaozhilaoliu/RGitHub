package cn.renyuzhuo.rgithubandroidsdk.service.trending;

import java.util.List;

import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.trending.TrendingBean;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by renyuzhuo on 16-11-7.
 */
public interface TrendingService {
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "User-Agent: RGitHub"
    })
    @GET("trending")
    Observable<List<TrendingBean>> getTrending(@Query("since") String since);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "User-Agent: RGitHub"
    })
    @GET("trending")
    Observable<List<TrendingBean>> getTrending(@Query("since") String since, @Query("language") String language);
}
