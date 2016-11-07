package cn.renyuzhuo.rgithubandroidsdk.net.search;

import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.Token;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.search.SearchBean;
import cn.renyuzhuo.rgithubandroidsdk.net.Base.ApiBase.ApiBase;
import cn.renyuzhuo.rgithubandroidsdk.net.result.MySubscriber;
import cn.renyuzhuo.rgithubandroidsdk.service.search.SearchService;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by renyuzhuo on 16-11-7.
 */
public class SearchClient {
    private static SearchService trendingService = ApiBase.getInstance().build().create(SearchService.class);

    public static void getSearchResult(final SearchClientListener searchClientListener, String keyWord, int page) {
        trendingService.getSearchResult("token " + Token.getAuthorization(), keyWord, page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber<SearchBean>() {
                    @Override
                    public void onNext(SearchBean searchBean) {
                        if (searchClientListener != null) {
                            searchClientListener.onGetSearchResult(searchBean);
                        }
                    }
                });
    }
}
