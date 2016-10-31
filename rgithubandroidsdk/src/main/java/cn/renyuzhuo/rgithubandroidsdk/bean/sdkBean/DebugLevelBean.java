package cn.renyuzhuo.rgithubandroidsdk.bean.sdkBean;

import cn.renyuzhuo.rlog.rlog;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by renyuzhuo on 16-10-30.
 */
public class DebugLevelBean {
    public static enum DEBUG_LEVEL {
        DEBUG
    }

    static {
        setLogLevel(DEBUG_LEVEL.DEBUG);
    }

    private static DEBUG_LEVEL LogLevel = DEBUG_LEVEL.DEBUG;
    private static HttpLoggingInterceptor.Level httpLogLevel;

    public static DEBUG_LEVEL getLogLevel() {
        return LogLevel;
    }

    public static void setLogLevel(DEBUG_LEVEL logLevel) {
        LogLevel = logLevel;
        switch (logLevel) {
            case DEBUG: {
                rlog.setDebugLever(rlog.DEBUG_LEVEL.debug);
                httpLogLevel = HttpLoggingInterceptor.Level.BODY;
                break;
            }
        }
    }

    public static HttpLoggingInterceptor.Level getHttpLogLevel() {
        return httpLogLevel;
    }

    public static void setHttpLogLevel(HttpLoggingInterceptor.Level httpLogLevel) {
        DebugLevelBean.httpLogLevel = httpLogLevel;
    }
}
