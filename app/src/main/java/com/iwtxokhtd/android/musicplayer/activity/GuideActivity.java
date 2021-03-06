package com.iwtxokhtd.android.musicplayer.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.iwtxokhtd.android.musicplayer.R;
import com.iwtxokhtd.android.musicplayer.util.ActivityUtil;
import com.iwtxokhtd.android.musicplayer.util.ConstantUtil;
import com.iwtxokhtd.android.musicplayer.util.SharedPreferenceUtil;
import com.iwtxokhtd.android.musicplayer.view.GuideViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tujiyue on 25/6/17.
 */
public class GuideActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager viewPager;
    private GuideViewPagerAdapter adapter;
    private List<View> views;
    private Button startBtn;

    // 引导页图片资源
    private static final int[] guidePictures = { R.layout.guide_view1,
            R.layout.guide_view2, R.layout.guide_view3, R.layout.guide_view4 };

    // 底部小点图片
    private ImageView[] dots;

    // 记录当前选中位置
    private int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        views = new ArrayList<View>();

        // 初始化引导页视图列表
        for (int i = 0; i < guidePictures.length; i++) {
            View view = LayoutInflater.from(this).inflate(guidePictures[i], null);

            if (i == guidePictures.length - 1) {
                startBtn = (Button) view.findViewById(R.id.btn_login);
                startBtn.setTag("enter");
                startBtn.setOnClickListener(this);
            }

            views.add(view);

        }

        viewPager = (ViewPager) findViewById(R.id.vp_guide);
        // 初始化adapter
        adapter = new GuideViewPagerAdapter(views);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new PageChangeListener());

        initDots();
    }




    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 如果切换到后台，就设置下次不进入功能引导页
        SharedPreferenceUtil.putBoolean(this,ConstantUtil.IS_FIRST_LAUNCH,false);
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    private void initDots() {
        LinearLayout ll = (LinearLayout) findViewById(R.id.ll);
        dots = new ImageView[guidePictures.length];

        // 循环取得小点图片
        for (int i = 0; i < guidePictures.length; i++) {
            // 得到一个LinearLayout下面的每一个子元素
            dots[i] = (ImageView) ll.getChildAt(i);
            dots[i].setEnabled(false);// 都设为灰色
            dots[i].setOnClickListener(this);
            dots[i].setTag(i);// 设置位置tag，方便取出与当前位置对应
        }

        currentIndex = 0;
        dots[currentIndex].setEnabled(true); // 设置为白色，即选中状态

    }

    /**
     * 设置当前view
     *
     * @param position
     */
    private void setCurView(int position) {
        if (position < 0 || position >= guidePictures.length) {
            return;
        }
        viewPager.setCurrentItem(position);
    }

    /**
     * 设置当前指示点
     *
     * @param position
     */
    private void setCurrentDot(int position) {
        if (position < 0 || position > guidePictures.length || currentIndex == position) {
            return;
        }
        dots[position].setEnabled(true);
        dots[currentIndex].setEnabled(false);
        currentIndex = position;
    }

    @Override
    public void onClick(View v) {
        if (v.getTag().equals("enter")) {
            startMainActivity();
            return;
        }

        int position = (Integer) v.getTag();
        setCurView(position);
        setCurrentDot(position);
    }


    private void startMainActivity() {
        ActivityUtil.startActivity(this,LaunchActivity.class);
        SharedPreferenceUtil.putBoolean(this,ConstantUtil.IS_FIRST_LAUNCH,false);
        finish();
    }

    private class PageChangeListener implements ViewPager.OnPageChangeListener {
        // 当滑动状态改变时调用
        @Override
        public void onPageScrollStateChanged(int position) {
            // arg0 ==1的时辰默示正在滑动，arg0==2的时辰默示滑动完毕了，arg0==0的时辰默示什么都没做。

        }

        // 当前页面被滑动时调用
        @Override
        public void onPageScrolled(int position, float arg1, int arg2) {
            // arg0 :当前页面，即你点击滑动的页面
            // arg1:当前页面偏移的百分比
            // arg2:当前页面偏移的像素位置

        }

        // 当新的页面被选中时调用
        @Override
        public void onPageSelected(int position) {
            // 设置底部小点选中状态
            setCurrentDot(position);
        }

    }

}
