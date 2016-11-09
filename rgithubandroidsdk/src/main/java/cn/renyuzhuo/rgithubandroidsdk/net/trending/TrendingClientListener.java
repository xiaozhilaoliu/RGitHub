package cn.renyuzhuo.rgithubandroidsdk.net.trending;

import java.util.List;

import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.trending.TrendingBean;

/**
 * Created by renyuzhuo on 16-11-7.
 */
public interface TrendingClientListener {
    void onGetTrendingSuccess(String key, List<TrendingBean> trendingBeen);

    void onNetErr();
}
