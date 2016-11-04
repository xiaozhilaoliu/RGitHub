package cn.renyuzhuo.rgithubandroidsdk.net.Event;

import java.util.List;

import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.Token;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.event.EventBean;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.user.UserInfoBean;
import cn.renyuzhuo.rgithubandroidsdk.net.Base.ApiBase.ApiBase;
import cn.renyuzhuo.rgithubandroidsdk.net.result.MySubscriber;
import cn.renyuzhuo.rgithubandroidsdk.service.event.EventService;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by renyuzhuo on 16-11-2.
 */
public class EventClient {
    private static EventService eventService = ApiBase.getInstance().build().create(EventService.class);

    public static void getUserEvent(final EventClientListener eventClientListener, String username, int page) {
        eventService.getUserEvent("token " + Token.getAuthorization(), username, page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber<List<EventBean>>() {
                    @Override
                    public void onNext(List<EventBean> eventBeen) {
                        if (eventClientListener != null) {
                            eventClientListener.onGetUserEvent(eventBeen);
                        }
                    }
                });
    }

    public static void getRepoEvent(final EventClientListener eventClientListener, String username, String pname, int page) {
        eventService.getRepoEvent("token " + Token.getAuthorization(), username, pname, page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber<List<EventBean>>() {
                    @Override
                    public void onNext(List<EventBean> eventBeen) {
                        if (eventClientListener != null) {
                            eventClientListener.onGetRepoEvent(eventBeen);
                        }
                    }
                });
    }

    public static void getLoginUserEvent(final EventClientListener eventClientListener, int page) {
        getUserEvent(eventClientListener, UserInfoBean.getInstance().getLogin(), page);
    }
}
