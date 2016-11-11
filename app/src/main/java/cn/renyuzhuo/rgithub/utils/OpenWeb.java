package cn.renyuzhuo.rgithub.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import cn.renyuzhuo.rgithub.R;
import cn.renyuzhuo.rlog.rlog;

/**
 * Created by renyuzhuo on 16-11-3.
 */
public class OpenWeb {
    public static void open(Context context, String url) {
        if (!url.contains("http://") && !url.contains("https://")) {
            url = "http://" + url;
        }
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            context.startActivity(intent);
        } catch (Exception e) {
            rlog.ebegin();
            e.printStackTrace();
            rlog.eend();
            Toast.makeText(context, context.getString(R.string.url_err), Toast.LENGTH_SHORT).show();
        }
    }
}
