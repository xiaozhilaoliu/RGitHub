package cn.renyuzhuo.rgithub;

import android.app.Application;
import android.net.Uri;
import android.os.Environment;


import com.squareup.leakcanary.LeakCanary;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.io.File;

import cn.renyuzhuo.rgithub.utils.OpenWeb;
import cn.renyuzhuo.rgithubandroidsdk.GitHubSdk;
import cn.renyuzhuo.rlog.rlog;

/**
 * Created by renyuzhuo on 16-10-30.
 */
public class RGitHubApplication extends Application {

    public static boolean isLogin = false;
    public static String clientId = "c5c00b034fb934c6ae4b";
    public static String clientSecret = "8c0f9748134af3497331f4761fd9da7356ccaa4e";
    public static String redirectUri = "http://renyuzhuo.cn";

    String filePath = null;
    String picTempPath = null;

    @Override
    public void onCreate() {
        super.onCreate();
        rlog.setDebugLever(rlog.DEBUG_LEVEL.debug);
        rlog.d("init GitHubSdk");
        GitHubSdk.init(this);
        OpenWeb.init(this);

        rlog.d("LeakCanary init");
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);

        createFilePath();
        initPicasso();
    }

    private void initPicasso() {
        Picasso picasso = new Picasso.Builder(getApplicationContext())
                .downloader(new OkHttpDownloader(new File(picTempPath)))
                .listener(new Picasso.Listener() {
                    @Override
                    public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                        rlog.d("Picasso download pic err:" + uri.toString());
                        rlog.ebegin();
                        exception.printStackTrace();
                        rlog.eend();
                    }
                })
                .build();
        Picasso.setSingletonInstance(picasso);
    }

    private void createFilePath() {
        filePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        picTempPath = filePath + "/RGitHub/img";
        createPath(picTempPath);
    }

    public void createPath(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
            rlog.d("create path:" + path);
        }
    }
}
