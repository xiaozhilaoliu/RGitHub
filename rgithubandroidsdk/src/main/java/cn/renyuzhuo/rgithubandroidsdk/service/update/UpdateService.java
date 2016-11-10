package cn.renyuzhuo.rgithubandroidsdk.service.update;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by renyuzhuo on 16-11-10.
 */
public interface UpdateService {

    @Headers({
            "Accept: text/html",
            "User-Agent: RGitHub"
    })
    @GET("/{username}/{repo}/{branch}/{file}")
    Observable<ResponseBody> ifNeedUpdate(@Path("username") String username,
                                          @Path("repo") String repo, @Path("branch") String branch, @Path("file") String file);
}
