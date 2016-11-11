package cn.renyuzhuo.rgithubandroidsdk.Dialog;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.support.annotation.NonNull;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import cn.renyuzhuo.rgithubandroidsdk.R;
import cn.renyuzhuo.rgithubandroidsdk.activity.WebActivity;
import cn.renyuzhuo.rgithubandroidsdk.net.update.UpdateClientListener;
import cn.renyuzhuo.rlog.rlog;
import okhttp3.HttpUrl;

/**
 * Created by renyuzhuo on 16-11-1.
 */
public class LoadingDialog {
    private static MaterialDialog progressDialog;

    private static void openLoadingDialog(Context context, int sourceId) {
        closeDialog();
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
            progressDialog = null;
        }
    }

    public static void openLoadingDialogLoading(Context context) {
        openLoadingDialog(context, R.string.loading);
    }

    public static void openLoadingDialogLoadingMore(Context context) {
        openLoadingDialog(context, R.string.loading_more);
    }

    static MaterialDialog updateDialog;

    public static void openDownloadDialog(final UpdateClientListener updateClientListener, Context context, String content) {
        updateDialog = new MaterialDialog.Builder(context)
                .title(R.string.new_download)
                .content(content)
                .positiveText(R.string.agree)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        updateClientListener.onAcceptUpdate();
                        updateDialog = null;
                    }
                })
                .negativeText(R.string.disagree)
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        updateClientListener.onDoNotAcceptUpdate();
                        updateDialog = null;
                    }
                })
                .show();
    }

    static MaterialDialog logoutDialog;

    public static void logoutDialog(final LogoutListener logoutListener, final Fragment fragment) {
        logoutDialog = new MaterialDialog.Builder(fragment.getActivity())
                .title(R.string.logout)
                .content(fragment.getActivity().getString(R.string.logout))
                .positiveText(R.string.agree)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        logoutListener.logout(fragment);
                        updateDialog = null;
                    }
                })
                .negativeText(R.string.disagree)
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        updateDialog = null;
                    }
                })
                .show();
    }
}
