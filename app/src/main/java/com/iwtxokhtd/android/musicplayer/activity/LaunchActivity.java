package com.iwtxokhtd.android.musicplayer.activity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.iwtxokhtd.android.musicplayer.R;
import com.iwtxokhtd.android.musicplayer.util.ActivityUtil;
import com.iwtxokhtd.android.musicplayer.util.ConstantUtil;
import com.iwtxokhtd.android.musicplayer.util.PermissionChecker;
import com.iwtxokhtd.android.musicplayer.util.SharedPreferenceUtil;
import com.iwtxokhtd.android.musicplayer.util.ToastUtil;

/**
 * Created by tujiyue on 25/6/17.
 */
public class LaunchActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean isFirstLaunch= SharedPreferenceUtil.getBoolean(this,ConstantUtil.IS_FIRST_LAUNCH,true);
        if(isFirstLaunch){
            ActivityUtil.startActivity(this,GuideActivity.class);
            finish();
            return;
        }
        setContentView(R.layout.activity_launch);
        checkPermissions();
    }

    private void doCountDown(){
        new Handler().postDelayed(new Runnable(){

            @Override
            public void run() {
                startMainActivity();
            }
        },3000);
    }

    private void startMainActivity(){
        ActivityUtil.startActivity(this,MainActivity.class);
        finish();
    }

    private void checkPermissions() {
        boolean hasWriteStoragePermission = PermissionChecker.checkIfHasWriteStoragePermission(this);
        if (hasWriteStoragePermission) {
            doCountDown();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PermissionChecker.PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    doCountDown();
                } else {
                    ToastUtil.showCenterToast(this,"You rejected permission request");
                    finish();
                }
                return;
        }
    }
}
