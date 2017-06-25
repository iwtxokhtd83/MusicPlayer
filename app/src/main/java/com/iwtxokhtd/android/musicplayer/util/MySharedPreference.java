package com.iwtxokhtd.android.musicplayer.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by tujiyue on 25/6/17.
 */

public class MySharedPreference {
    private SharedPreferences mSharedPreferences;
    private Context mContext;
    private SharedPreferences.Editor mEditor;

    public MySharedPreference(Context context) {
        mContext = context;
        mSharedPreferences = mContext.getSharedPreferences(ConstantUtil.SHARED_PREFERENCE_FILE, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
    }


    public void setData(String key, String value) {
        mEditor.putString(key, value);
        mEditor.commit();
    }

    public void setData(String key, int value) {
        mEditor.putInt(key, value);
        mEditor.commit();
    }

    public void setData(String key, boolean value) {
        mEditor.putBoolean(key, value);
        mEditor.commit();
    }

    public boolean getData(String key) {
        return mSharedPreferences.getBoolean(key, true);
    }

    public void removeData(String key) {
        mEditor.remove(key);
        mEditor.commit();
    }
}
