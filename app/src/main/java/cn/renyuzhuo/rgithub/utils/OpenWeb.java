package cn.renyuzhuo.rgithub.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by renyuzhuo on 16-11-3.
 */
public class OpenWeb {
    public static void open(Context context, String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(intent);
    }
}
