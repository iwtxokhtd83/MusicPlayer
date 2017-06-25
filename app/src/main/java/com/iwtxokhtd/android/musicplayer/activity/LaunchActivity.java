package com.iwtxokhtd.android.musicplayer.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.iwtxokhtd.android.musicplayer.R;
import com.iwtxokhtd.android.musicplayer.util.ConstantUtil;
import com.iwtxokhtd.android.musicplayer.util.MySharedPreference;
import com.iwtxokhtd.android.musicplayer.util.PermissionChecker;

public class LaunchActivity extends AppCompatActivity {
    private int count = 3;
    private TextView mTextView;
    private Handler mHandler;
    private MySharedPreference mySharedPreference;
    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        initView();
        initHandlers();
        checkPermissions();
    }

    private void initView() {
        mTextView = (TextView) findViewById(R.id.tv_launch_count);
    }

    private void initHandlers(){
        mHandler = new Handler();
    }

    private void checkPermissions() {
        mySharedPreference = new MySharedPreference(this);
        boolean hasWriteStoragePermission = PermissionChecker.checkIfHasWriteStoragePermission(this);
        if (hasWriteStoragePermission) {
            countDown2SwitchView();
        }
    }

    private void countDown2SwitchView() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (count == 1) {
                    boolean isFirstLaunch = mySharedPreference.getData(ConstantUtil.IS_FIRST_LAUNCH);
                    Log.i("btu", "Is First Launch:" + isFirstLaunch);
                    if (isFirstLaunch) {
                        mIntent = new Intent(LaunchActivity.this, GuideActivity.class);
                    } else {
                        mIntent = new Intent(LaunchActivity.this, MainActivity.class);
                    }
                    startActivity(mIntent);
                    finish();
                } else {
                    count--;
                    mTextView.setText(count + "");
                    countDown2SwitchView();
                }

            }
        }, 1000);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PermissionChecker.PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    countDown2SwitchView();
                } else {
                    Toast.makeText(this, "You rejected permission request.", Toast.LENGTH_SHORT).show();
                    finish();
                }
                return;
        }
    }
}
