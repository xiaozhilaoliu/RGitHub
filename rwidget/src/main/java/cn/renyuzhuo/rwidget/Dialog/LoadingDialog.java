package cn.renyuzhuo.rwidget.Dialog;

import android.content.Context;
import android.view.ContextThemeWrapper;

import com.afollestad.materialdialogs.MaterialDialog;

import cn.renyuzhuo.rwidget.R;

/**
 * Created by renyuzhuo on 16-11-1.
 */
public class LoadingDialog {
    private static MaterialDialog progressDialog;

    private static void openLoadingDialog(Context context, int sourceId) {
        progressDialog = new MaterialDialog.Builder(context)
                .progress(true, 0)
                .content(sourceId)
                .show();
    }

    public static void openLoadingDialogLogin(Context context) {
        openLoadingDialog(context, R.string.login_activity_authenticating);
    }


    public static void closeDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    public static void openLoadingDialogLoading(Context context) {
        openLoadingDialog(context, R.string.loading);
    }

    public static void openLoadingDialogLoadingMore(Context context) {
        openLoadingDialog(context, R.string.loading_more);
    }
}
