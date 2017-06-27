package com.iwtxokhtd.android.musicplayer.service;

import android.app.Notification;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.Messenger;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.iwtxokhtd.android.musicplayer.R;
import com.iwtxokhtd.android.musicplayer.model.Song;
import com.iwtxokhtd.android.musicplayer.util.MusicUtil;
import com.iwtxokhtd.android.musicplayer.widget.MusicWidgetProvider;

import java.util.ArrayList;
import java.util.List;

public class MusicService extends Service {

    //Loop flags
    public static final int LOOP_ALL_MODE = 0;
    public static final int LOOP_ONE_ONLY_MODE = 1;
    public static final int RANDOM_MODE = 2;

    //Playing flags
    public static final int PLAYING_STATE = 0;
    public static final int STOP_STATE = 2;
    public static final int TRIGGER_START_PROGRESS_STATE = 99;

    private int playMode = LOOP_ALL_MODE;
    private int playState = STOP_STATE;

    private int currentPosition;

    private Notification mNotification;
    private Messenger mMainMessenger;
    private Messenger mListActivityMessegner;
    private Notification.Builder mBuilder;

    private MediaPlayer mMediaPlayer;

    private List<Song> songs;

    private boolean isFirstTimePlaying = true;
    private boolean isInProgressing = false;

    public MusicService() {
    }

    private void playNextSong() {

    }

    private void playPreviousSong() {

    }

    private void setPlayState() {

    }

    public class MusicServiceBinder extends Binder {
        public MusicService getServiceInstance() {
            return MusicService.this;
        }
    }

    //Receive broadcast from widget
    class MusicServiceReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent != null) {
                if (TextUtils.equals(intent.getAction(), MusicWidgetProvider.WIDGET_PLAY_CURRENT_SERVICE)) {
                    setPlayState();
                } else if (TextUtils.equals(intent.getAction(), MusicWidgetProvider.WIDGET_PLAY_NEXT_SERVICE)) {
                    playNextSong();
                } else if (TextUtils.equals(intent.getAction(), MusicWidgetProvider.WIDGET_PLAY_PREVIOUS_SERVICE)) {
                    playPreviousSong();
                }
            }

        }
    }

    private List<Song> scanAndGetSongsFromLocalLibrary() {
        Cursor cursor = getContentResolver().
                query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null,
                        MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        if (cursor == null) {
            return null;
        }
        songs = new ArrayList<Song>();
        if (cursor.moveToFirst()) {
            do {
                Song song = new Song();
                song.setArtist(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)));
                song.setTitle(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)));
                String duration = MusicUtil.getTimeStrByMils((int) cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION)));
                song.setDuration(duration);
                song.setSongUri(Uri.parse(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA))));
                song.setAvatar(BitmapFactory.decodeResource(getResources(), R.mipmap.musicplayer_512px));
                songs.add(song);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return songs;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
