package com.iwtxokhtd.android.musicplayer.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by tujiyue on 26/6/17.
 */

public class ActivityUtil {
    public static void startActivity(Activity activity, Class clz) {
        Intent intent = new Intent(activity, clz);
        activity.startActivity(intent);
    }

    public static void startActivityForResult(Activity activity, Class clz, int requestCode) {
        Intent intent = new Intent(activity, clz);
        activity.startActivityForResult(intent, requestCode);
    }

    public static void startActivityWithBundle(Activity activity, Class clz, Bundle bundle) {
        Intent intent = new Intent(activity, clz);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    public static void startActivity(Activity activity, Intent intent) {
        activity.startActivity(intent);
    }

    public static void startActivityForResult(Activity activity, Intent intent, int requestCode) {
        activity.startActivityForResult(intent, requestCode);
    }
}
