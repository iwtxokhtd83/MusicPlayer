package com.iwtxokhtd.android.musicplayer.util;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

/**
 * Created by tujiyue on 25/6/17.
 */

public class PermissionChecker {

    //请求码
    public static final int PERMISSION_REQUEST_CODE = 1;

    /**
     * 在API23+以上，光是从Manifest中添加权限是不够的，还要在java中申请
     * Checks if the app has permission to write to device storage
     * <p/>
     * If the app does not has permission then the user will be prompted to
     * grant permissions
     * 若未取得权限，会弹出提示去获取权限  -- 授权
     *
     * @param activity
     */

    public static boolean checkIfHasWriteStoragePermission(Activity activity) {
        int permissionResult = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionResult != PackageManager.PERMISSION_GRANTED) {
            //Request permission
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
            return false;
        }
        return true;
    }

}
