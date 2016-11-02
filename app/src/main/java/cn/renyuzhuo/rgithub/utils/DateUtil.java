package cn.renyuzhuo.rgithub.utils;

/**
 * Created by renyuzhuo on 16-11-2.
 */
public class DateUtil {
    //2016-09-10T00:36:01Z
    public static String formate(String created_at) {
        return created_at.substring(0, 10);
    }
}
