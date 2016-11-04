package cn.renyuzhuo.rgithubandroidsdk.net.file;

import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.Token;
import cn.renyuzhuo.rgithubandroidsdk.net.Base.GitHubContent.GitHubContentBase;
import cn.renyuzhuo.rgithubandroidsdk.net.result.MySubscriber;
import cn.renyuzhuo.rgithubandroidsdk.service.file.FileService;
import okhttp3.ResponseBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by renyuzhuo on 16-11-4.
 */
public class FileClient {
    private static FileService fileService = GitHubContentBase.getInstance().build().create(FileService.class);

    public static void downLoadFile(final FileClientListener fileClientListener, String url) {
        fileService.downloadReadme("token " + Token.getAuthorization(), url)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber<ResponseBody>() {
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        fileClientListener.onDownload(responseBody);
                    }
                });
    }
}
