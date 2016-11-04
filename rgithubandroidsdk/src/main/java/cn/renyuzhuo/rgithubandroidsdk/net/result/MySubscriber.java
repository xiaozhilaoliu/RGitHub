package cn.renyuzhuo.rgithubandroidsdk.net.result;

import android.content.Context;
import android.widget.Toast;

import cn.renyuzhuo.rgithubandroidsdk.Dialog.LoadingDialog;
import cn.renyuzhuo.rgithubandroidsdk.R;
import cn.renyuzhuo.rlog.rlog;
import rx.Subscriber;

/**
 * Created by renyuzhuo on 16-11-4.
 */
public abstract class MySubscriber<T> extends Subscriber<T> {

    private static Context context;

    public static void init(Context context) {
        MySubscriber.context = context;
    }

    @Override
    public void onCompleted() {
        rlog.d("RxJava Network Finish");
    }

    @Override
    public void onError(Throwable e) {
        rlog.ebegin();
        e.printStackTrace();
        rlog.eend();
        LoadingDialog.closeDialog();
        if (context != null) {
            try {
                Toast.makeText(context, context.getString(R.string.net_err), Toast.LENGTH_LONG).show();
            } catch (Exception ex) {
                rlog.ebegin();
                rlog.d("net err");
                rlog.d();
                ex.printStackTrace();
                rlog.eend();
            }
        }
    }
}
