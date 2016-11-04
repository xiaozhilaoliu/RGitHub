package cn.renyuzhuo.rgithubandroidsdk.Dialog;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;

import cn.renyuzhuo.rgithubandroidsdk.R;
import cn.renyuzhuo.rgithubandroidsdk.activity.WebActivity;

/**
 * Created by renyuzhuo on 16-11-1.
 */
public class LoadingDialog {
    private static MaterialDialog progressDialog;

    private static void openLoadingDialog(Context context, int sourceId) {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
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
