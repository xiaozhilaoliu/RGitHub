package cn.renyuzhuo.rgithubandroidsdk.net.file;

import okhttp3.ResponseBody;

/**
 * Created by renyuzhuo on 16-11-4.
 */
public interface FileClientListener {
    void onDownload(ResponseBody responseBody);
}
