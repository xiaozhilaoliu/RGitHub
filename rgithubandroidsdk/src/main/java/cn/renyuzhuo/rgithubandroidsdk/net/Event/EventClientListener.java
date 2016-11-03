package cn.renyuzhuo.rgithubandroidsdk.net.Event;

import java.util.List;

import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.event.EventBean;
import cn.renyuzhuo.rgithubandroidsdk.net.BaseListener;

/**
 * Created by renyuzhuo on 16-11-3.
 */
public interface EventClientListener extends BaseListener {
    void onGetRepoEvent(List<EventBean> eventBeen);

    void onGetUserEvent(List<EventBean> eventBeen);
}
