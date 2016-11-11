package cn.renyuzhuo.rgithubandroidsdk.net.update;

import okhttp3.ResponseBody;

/**
 * Created by renyuzhuo on 16-11-10.
 */
public interface UpdateClientListener {
    void onGetVersion(ResponseBody responseBody);

    void onAcceptUpdate();

    void onDoNotAcceptUpdate();
}
