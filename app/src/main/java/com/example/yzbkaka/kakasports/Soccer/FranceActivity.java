package com.example.yzbkaka.kakasports.Soccer;

import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.yzbkaka.kakasports.R;

public class FranceActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {  //需要继承父类
    private RadioGroup radioGroup;
    private RadioButton vs;
    private RadioButton grade;
    private ViewPager viewPager;

    private SoccerFragmentAdapter soccerFragmentAdapter;  //ViewPager的适配器

    public static final int PAGE_ONE = 0;  //页面1
    public static final int PAGE_TWO = 1;  //页面2


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_german);
        setLightMode();


        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        vs = (RadioButton) findViewById(R.id.vs);
        grade = (RadioButton) findViewById(R.id.grade);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        soccerFragmentAdapter = new SoccerFragmentAdapter(getSupportFragmentManager());  //创建适配器实例

        viewPager.setAdapter(soccerFragmentAdapter);  //设置适配器
        viewPager.setCurrentItem(0);  //设置页面1为主页面
        viewPager.addOnPageChangeListener(this);
        vs.setChecked(true);  //显示为主页面
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == 2) {  //state的状态有三个，0表示什么都没做，1正在滑动，2滑动完毕
            switch (viewPager.getCurrentItem()) {
                case PAGE_ONE:
                    vs.setChecked(true);
                    break;
                case PAGE_TWO:
                    grade.setChecked(true);
                    break;
            }
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        switch (checkedId) {
            case R.id.vs:
                viewPager.setCurrentItem(PAGE_ONE);
                break;
            case R.id.grade:
                viewPager.setCurrentItem(PAGE_TWO);
                break;
        }

    }


    private void setLightMode(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

}
