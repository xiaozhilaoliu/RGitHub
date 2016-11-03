package cn.renyuzhuo.rgithubandroidsdk.service.event;

import java.util.List;

import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.event.EventBean;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by renyuzhuo on 16-11-3.
 */
public interface EventService {

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "User-Agent: RGitHub"
    })
    @GET("repos/{username}/{pname}/events")
    Observable<List<EventBean>> getRepoEvent(@Header("Authorization") String token, @Path("username") String username, @Path("pname") String pname, @Query("page") int page);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "User-Agent: RGitHub"
    })
    @GET("users/{username}/received_events")
    Observable<List<EventBean>> getUserEvent(@Header("Authorization") String token, @Path("username") String username, @Query("page") int page);
}
