package com.iwtxokhtd.android.musicplayer.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.iwtxokhtd.android.musicplayer.IMyAidlInterface;

public class AidlService extends Service {
    public AidlService() {
    }

    IMyAidlInterface.Stub mAidlStub = new IMyAidlInterface.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public int doSomething(int a, int b) throws RemoteException {
            return a + b;
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mAidlStub;
    }
}
