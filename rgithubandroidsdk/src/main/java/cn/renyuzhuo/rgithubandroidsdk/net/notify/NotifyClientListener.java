package cn.renyuzhuo.rgithubandroidsdk.net.notify;

import java.util.List;

import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.notify.NotifyBean;

/**
 * Created by renyuzhuo on 16-11-25.
 */
public interface NotifyClientListener {
    void onGetNotifyListSuccess(List<NotifyBean> notifyBeen);
}
