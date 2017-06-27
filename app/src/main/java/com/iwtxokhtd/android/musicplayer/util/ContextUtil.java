package com.iwtxokhtd.android.musicplayer.util;

import android.content.Context;
import android.content.Intent;

/**
 * Created by tujiyue on 27/6/17.
 */

public class ContextUtil {

    public static void sendBroadCast(Context context, String action){
        Intent mIntent = new Intent();
        mIntent.setAction(action);
        context.sendBroadcast(mIntent);
    }
}
