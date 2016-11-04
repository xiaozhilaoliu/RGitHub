package cn.renyuzhuo.rgithubandroidsdk.service.file;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by renyuzhuo on 16-11-4.
 */
public interface FileService {
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "User-Agent: RGitHub"
    })
    @Streaming
    @GET
    Observable<ResponseBody> downloadReadme(@Header("Authorization") String token, @Url String url);

}
