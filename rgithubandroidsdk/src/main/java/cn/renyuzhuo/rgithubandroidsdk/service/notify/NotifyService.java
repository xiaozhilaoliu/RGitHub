package cn.renyuzhuo.rgithubandroidsdk.service.notify;

import java.util.List;

import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.notify.NotifyBean;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by renyuzhuo on 16-11-25.
 */
public interface NotifyService {

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "User-Agent: RGitHub"
    })
    @GET("/notifications")
    Observable<List<NotifyBean>> getNotifyList(@Header("Authorization") String token, @Query("all") boolean all);
}
