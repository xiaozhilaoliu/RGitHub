package cn.renyuzhuo.autoupdate;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;

import com.google.gson.Gson;

import cn.renyuzhuo.rgithubandroidsdk.Dialog.LoadingDialog;
import cn.renyuzhuo.rgithubandroidsdk.net.update.UpdateClient;
import cn.renyuzhuo.rgithubandroidsdk.net.update.UpdateClientListener;
import cn.renyuzhuo.rlog.rlog;
import okhttp3.ResponseBody;

/**
 * Created by renyuzhuo on 16-11-10.
 */
public class UpdateMain implements UpdateClientListener {
    private static UpdateMain updateMain;
    Context context;
    Version version;

    public UpdateMain(Context context) {
        this.context = context;
    }

    public static UpdateMain getUpdateMain(Context context) {
        if (updateMain == null) {
            updateMain = new UpdateMain(context);
            return updateMain;
        } else {
            return updateMain;
        }
    }

    public final boolean ifNeedUpdate(String username, String repo, String branch, String file) {
        UpdateClient.ifNeedUpdate(this, username, repo, branch, file);
        return false;
    }

    @Override
    public void onGetVersion(ResponseBody responseBody) {
        try {
            String response = responseBody.string();
            rlog.d(response);
            version = new Gson().fromJson(response, Version.class);
            rlog.d(version);
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            int buildVersionCode = pi.versionCode;
            if (version.getVersionCode() > buildVersionCode) {
                rlog.d("need to update");
                LoadingDialog.openDownloadDialog(this, context, version.getDescription());
            } else {
                rlog.d("not need to update");
            }
        } catch (Exception e) {
            rlog.ebegin();
            e.printStackTrace();
            rlog.eend();
        }
    }

    @Override
    public void onAcceptUpdate() {
        if (version != null && version.getUrl() != null) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(version.getUrl()));
            context.startActivity(intent);
        }
    }
}
