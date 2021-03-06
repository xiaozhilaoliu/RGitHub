package cn.renyuzhuo.rgithub.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import cn.renyuzhuo.rgithub.R;
import cn.renyuzhuo.rlog.rlog;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by renyuzhuo on 16-11-3.
 */
public class OpenWeb {

    static Context context;

    public static void open(String url) {
        if (context == null) {
            rlog.e("OpenWeb.context == null");
        }
        rlog.d("open Web");
        if (url.length() == 0) {
            rlog.d("url.length()==0");
        }
        if (!url.contains("http://") && !url.contains("https://")) {
            url = "http://" + url;
        }
        rlog.d("open url: " + url);
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            rlog.ebegin();
            e.printStackTrace();
            rlog.eend();
            Toast.makeText(context, context.getString(R.string.url_err), Toast.LENGTH_SHORT).show();
        }
    }

    public static void init(Context context) {
        OpenWeb.context = context;
    }
}
