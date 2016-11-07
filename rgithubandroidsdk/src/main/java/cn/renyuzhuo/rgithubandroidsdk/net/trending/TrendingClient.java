package cn.renyuzhuo.rgithubandroidsdk.net.trending;

import java.util.List;

import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.trending.TrendingBean;
import cn.renyuzhuo.rgithubandroidsdk.net.Base.CodeHub.TrendingBase;
import cn.renyuzhuo.rgithubandroidsdk.net.result.MySubscriber;
import cn.renyuzhuo.rgithubandroidsdk.service.trending.TrendingService;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by renyuzhuo on 16-11-7.
 */
public class TrendingClient {
    private static TrendingService trendingService = TrendingBase.getInstance().build().create(TrendingService.class);

    public static void getTrending(final TrendingClientListener trendingClientListener, String since) {
        trendingService.getTrending(since)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber<List<TrendingBean>>() {
                    @Override
                    public void onNext(List<TrendingBean> trendingBeanList) {
                        if (trendingClientListener != null) {
                            trendingClientListener.onGetTrendingSuccess(trendingBeanList);
                        }
                    }
                });
    }

    public static void getTrending(final TrendingClientListener trendingClientListener, String since, String language) {
        trendingService.getTrending(since, language)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber<List<TrendingBean>>() {
                    @Override
                    public void onNext(List<TrendingBean> trendingBeanList) {
                        if (trendingClientListener != null) {
                            trendingClientListener.onGetTrendingSuccess(trendingBeanList);
                        }
                    }
                });
    }

}
