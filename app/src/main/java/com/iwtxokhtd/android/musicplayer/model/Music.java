package com.iwtxokhtd.android.musicplayer.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.graphics.Bitmap;
import android.net.Uri;
/**
 * Created by tujiyue on 21/6/17.
 */

public class Music implements Parcelable {
    private String title;
    private String artist;
    private String duration;
    private boolean isFavoried;
    private Bitmap musicAvatar;

    private Uri musicUri;

    public Music(Parcel in) {
        title = in.readString();
        artist = in.readString();
        duration = in.readString();
        musicAvatar = in.readParcelable(Bitmap.class.getClassLoader());
        musicUri = in.readParcelable(Uri.class.getClassLoader());
        isFavoried = in.readByte() != 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public static final Creator<Music> CREATOR = new Creator<Music>() {
        @Override
        public Music createFromParcel(Parcel in) {
            return new Music(in);
        }

        @Override
        public Music[] newArray(int size) {
            return new Music[size];
        }
    };
}
