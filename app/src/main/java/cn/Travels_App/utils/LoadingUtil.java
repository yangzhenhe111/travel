package cn.Travels_App.utils;

import android.content.Context;

import com.android.tu.loadingdialog.LoadingDailog;


public class LoadingUtil {

    private static LoadingDailog dialog = null;

    public static void showLoading(Context mContext, String msg) {
        if (dialog != null) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            dialog = null;
        }
        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(mContext)
                .setMessage(msg)
                .setCancelable(true)
                .setCancelOutside(false);
        dialog = loadBuilder.create();
        if (dialog != null)
            dialog.show();
    }

    public static void showLoading(Context mContext) {
        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(mContext)
                .setCancelable(true)
                .setMessage("")
                .setCancelOutside(false);
        dialog = loadBuilder.create();
        dialog.show();
    }

    public static void showLoadingNew(Context mContext, String msg) {
        if (dialog != null) {
            dialog.show();
        } else {
            LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(mContext)
                    .setCancelable(true)
                    .setMessage(msg)
                    .setCancelOutside(false);
            dialog = loadBuilder.create();
            dialog.show();
        }

    }

    public static void hideLoading() {
        if (dialog != null) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            dialog = null;
        }
    }
}
