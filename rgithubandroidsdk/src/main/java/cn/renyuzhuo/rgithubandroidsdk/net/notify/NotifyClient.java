package cn.renyuzhuo.rgithubandroidsdk.net.notify;

import java.util.List;

import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.Token;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.notify.NotifyBean;
import cn.renyuzhuo.rgithubandroidsdk.net.Base.ApiBase.ApiBase;
import cn.renyuzhuo.rgithubandroidsdk.net.result.MySubscriber;
import cn.renyuzhuo.rgithubandroidsdk.service.notify.NotifyService;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by renyuzhuo on 16-11-25.
 */
public class NotifyClient {
    private static NotifyService notifyService = ApiBase.getInstance().build().create(NotifyService.class);

    public static void getNotifyList(final NotifyClientListener notifyClientListener) {
        notifyService.getNotifyList("token " + Token.getAuthorization(), true)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber<List<NotifyBean>>() {
                    @Override
                    public void onNext(List<NotifyBean> notifyBeen) {
                        if (notifyClientListener != null) {
                            notifyClientListener.onGetNotifyListSuccess(notifyBeen);
                        }
                    }
                });
    }
}
