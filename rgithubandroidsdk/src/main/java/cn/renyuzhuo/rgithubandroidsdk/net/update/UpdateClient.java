package cn.renyuzhuo.rgithubandroidsdk.net.update;

import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.Token;
import cn.renyuzhuo.rgithubandroidsdk.net.Base.GitHubContent.GitHubContentBase;
import cn.renyuzhuo.rgithubandroidsdk.net.result.MySubscriber;
import cn.renyuzhuo.rgithubandroidsdk.service.update.UpdateService;
import okhttp3.ResponseBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by renyuzhuo on 16-11-10.
 */
public class UpdateClient {
    private static UpdateService updateService = GitHubContentBase.getInstance().build().create(UpdateService.class);

    public static void ifNeedUpdate(final UpdateClientListener updateClientListener, String username, String repo, String branch, String file) {
        updateService.ifNeedUpdate(username, repo, branch, file)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber<ResponseBody>() {
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        if (updateClientListener != null) {
                            updateClientListener.onGetVersion(responseBody);
                        }
                    }
                });
    }

}
