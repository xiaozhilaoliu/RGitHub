package cn.renyuzhuo.rgithub.utils;

import cn.renyuzhuo.rlog.rlog;

/**
 * Created by renyuzhuo on 16-11-2.
 */
public class DateUtil {
    //2016-09-10T00:36:01Z
    public static String formate(String timeString) {
        rlog.d("timeString: " + timeString);
        if (timeString != null && timeString.length() >= 10) {
            return timeString.substring(0, 10);
        } else {
            return timeString;
        }
    }
}
