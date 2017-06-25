package com.iwtxokhtd.android.musicplayer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.iwtxokhtd.android.musicplayer.R;
import com.iwtxokhtd.android.musicplayer.util.ConstantUtil;
import com.iwtxokhtd.android.musicplayer.util.MySharedPreference;

public class GuideActivity extends AppCompatActivity {
    private MySharedPreference mySharedPreference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        mySharedPreference=new MySharedPreference(this);
        initView();
    }

    private void initView(){
        Button guideBtn=(Button)findViewById(R.id.btn_guide);
        guideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveIfIsFirstLaunch();
                Intent switchActivityIntent=new Intent(GuideActivity.this,MainActivity.class);
                startActivity(switchActivityIntent);
                finish();
            }
        });
    }

    private void saveIfIsFirstLaunch(){
        mySharedPreference.setData(ConstantUtil.IS_FIRST_LAUNCH,true);
    }
}
