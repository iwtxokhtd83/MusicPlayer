package com.iwtxokhtd.android.musicplayer.util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;

/**
 * Created by tujiyue on 26/6/17.
 */

public class ProgressDialogUtil {
    private static ProgressDialog mProgressDialog = null;
    private static Activity mContext;
    private static ProgressDialogUtil mInstance = null;

    public static ProgressDialogUtil getInstance() {
        if (mInstance == null) {
            mInstance = new ProgressDialogUtil();

        }
        return mInstance;
    }

    public void show(Activity activity, int mMessage) {


        try {
            if (activity == null) {
                return;
            }
            // 开始请求是，显示请求对话框
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialog(activity, 0);
                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                mProgressDialog.setMessage(activity.getResources().getString(mMessage));
                mProgressDialog.setCanceledOnTouchOutside(false);
                mProgressDialog.setCancelable(true);
                mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        mProgressDialog.dismiss();
                        mProgressDialog = null;
                    }
                });
            }
            if (!activity.isFinishing()) {
                mProgressDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void show(Activity activity, String mMessage) {

        try {
            if (activity == null) {
                return;
            }
            // 开始请求是，显示请求对话框
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialog(activity, 0);
                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                mProgressDialog.setMessage(mMessage);
                mProgressDialog.setCanceledOnTouchOutside(false);
                mProgressDialog.setCancelable(true);
                mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        mProgressDialog.dismiss();
                        mProgressDialog = null;
                    }
                });
            }

            if (!activity.isFinishing()) {
                mProgressDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void dismiss() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }
}
