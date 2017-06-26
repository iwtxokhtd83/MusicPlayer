package com.iwtxokhtd.android.musicplayer.util;

import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
/**
 * Created by tujiyue on 25/6/17.
 */

public class MusicUtil {
    public static String getTimeStrByMils(int mils) {
        int seconds = mils / 1000;
        int min = seconds / 60;
        int sec = seconds % 60;
        String min_str;
        String sec_str;
        if (min < 10) {
            min_str = "0" + min;
        } else {
            min_str = min + "";
        }
        if (sec < 10) {
            sec_str = "0" + sec;
        } else {
            sec_str = sec + "";
        }
        return min_str + ":" + sec_str;
    }

    /**
     * Play current song by clicking play button
     */
    public static void play(Messenger service) {
        //多进程方式
        Message msg = Message.obtain();
        msg.what = ConstantUtil.WHAT_PLAY_PLAY_BTN;
        try {
            service.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Play previous song
     */
    public static void playPreviousSong(Messenger service) {
        Message msg = Message.obtain();
        msg.what = ConstantUtil.WHAT_PLAY_PRE_BTN;
        try {
            service.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Play next song
     */
    public static void playNextSong(Messenger service) {
        Message msg = Message.obtain();
        msg.what = ConstantUtil.WHAT_PLAY_NEXT_BTN;
        try {
            service.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
