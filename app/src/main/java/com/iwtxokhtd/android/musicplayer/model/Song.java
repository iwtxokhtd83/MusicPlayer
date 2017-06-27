package com.iwtxokhtd.android.musicplayer.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.graphics.Bitmap;
import android.net.Uri;
/**
 * Created by tujiyue on 21/6/17.
 */

public class Song implements Parcelable {
    private String title;
    private String artist;
    private String duration;
    private boolean isLiked;
    private Bitmap avatar;

    private Uri songUri;

    public Song(){

    }

    public Song(Parcel in) {
        title = in.readString();
        artist = in.readString();
        duration = in.readString();
        avatar = in.readParcelable(Bitmap.class.getClassLoader());
        songUri = in.readParcelable(Uri.class.getClassLoader());
        isLiked = in.readByte() != 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public Bitmap getAvatar() {
        return avatar;
    }

    public void setAvatar(Bitmap avatar) {
        this.avatar = avatar;
    }

    public Uri getSongUri() {
        return songUri;
    }

    public void setSongUri(Uri songUri) {
        this.songUri = songUri;
    }
}
