package cn.renyuzhuo.rgithubandroidsdk.bean.githubean;

/**
 * Created by renyuzhuo on 16-10-31.
 */
public class Token {
    private static String Authorization;

    public static String getAuthorization() {
        return Authorization;
    }

    public static void setAuthorization(String authorization) {
        Authorization = authorization;
    }
}
