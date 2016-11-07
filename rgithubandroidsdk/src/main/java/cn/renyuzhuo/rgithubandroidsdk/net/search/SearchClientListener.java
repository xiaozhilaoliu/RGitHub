package cn.renyuzhuo.rgithubandroidsdk.net.search;

import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.search.SearchBean;
import cn.renyuzhuo.rgithubandroidsdk.net.BaseListener;

/**
 * Created by renyuzhuo on 16-11-7.
 */
public interface SearchClientListener extends BaseListener {
    void onGetSearchResult(SearchBean searchBean);
}
