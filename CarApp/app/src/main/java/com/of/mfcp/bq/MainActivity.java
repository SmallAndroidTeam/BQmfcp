package com.of.mfcp.bq;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.of.mfcp.bq.Fragment.ConditioningControlFragment;
import com.of.mfcp.bq.Fragment.FragranceControlFragment;
import com.of.mfcp.bq.Fragment.MediaControlFragment;
import com.of.mfcp.bq.Fragment.MyFragmentPagerAdapter;
import com.of.mfcp.bq.Fragment.SeatControlFragment;
import com.of.mfcp.bq.R;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private List<Fragment> listFragment;
    private ImageView IV_conditioning_control;
    private ImageView IV_seat_control;
    private ImageView IV_media_control;
    private ImageView IV_fragrance_control;
    private ViewPager myViewPager;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    private Fragment conditioningControlFragment;
    private Fragment seatControlFragment;
    private Fragment mediaControlFragment;
    private Fragment fragranceControlFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉Activity上面的状态栏
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        AddFragment();

    }
    private void AddFragment(){
        listFragment = new ArrayList<>();
        conditioningControlFragment = new ConditioningControlFragment();
        seatControlFragment = new SeatControlFragment();
        mediaControlFragment = new MediaControlFragment();
        fragranceControlFragment = new FragranceControlFragment();
        listFragment.add(conditioningControlFragment);
        listFragment.add(seatControlFragment);
        listFragment.add(mediaControlFragment);
        listFragment.add(fragranceControlFragment);
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),listFragment);
        initView();
        ViewClick();
    }
    private void initView(){
        IV_conditioning_control = (ImageView) findViewById(R.id.IV_conditioning_control);
        IV_seat_control = (ImageView)findViewById(R.id.IV_seat_control);
        IV_media_control = (ImageView)findViewById(R.id.IV_media_control);
        IV_fragrance_control = (ImageView)findViewById(R.id.IV_fragrance_control);

        myViewPager = (ViewPager)findViewById(R.id.myViewPager);
        myViewPager.setAdapter(myFragmentPagerAdapter);

        myViewPager.setCurrentItem(0);


    }
    private void ViewClick(){
        IV_conditioning_control.setOnClickListener(this);
        IV_seat_control.setOnClickListener(this);
        IV_media_control.setOnClickListener(this);
        IV_fragrance_control.setOnClickListener(this);
        myViewPager.addOnPageChangeListener(new MyPageChangeListener());

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.IV_conditioning_control:
                IV_conditioning_control.setImageDrawable(getDrawable(R.mipmap.icon_air_choose));
                IV_seat_control.setImageDrawable(getDrawable(R.mipmap.icon_seat));
                IV_media_control.setImageDrawable(getDrawable(R.mipmap.icon_media));
                IV_fragrance_control.setImageDrawable(getDrawable(R.mipmap.icon_fragrance));

                IV_conditioning_control.setBackground(getDrawable(R.mipmap.bg_tab_choose));
                IV_seat_control.setBackground(null);
                IV_media_control.setBackground(null);
                IV_fragrance_control.setBackground(null);
                myViewPager.setCurrentItem(0);
                break;
            case  R.id.IV_seat_control:
                IV_conditioning_control.setImageDrawable(getDrawable(R.mipmap.icon_air));
                IV_seat_control.setImageDrawable(getDrawable(R.mipmap.icon_seat_choose));
                IV_media_control.setImageDrawable(getDrawable(R.mipmap.icon_media));
                IV_fragrance_control.setImageDrawable(getDrawable(R.mipmap.icon_fragrance));

                IV_conditioning_control.setBackground(null);
                IV_seat_control.setBackground(getDrawable(R.mipmap.bg_tab_choose));
                IV_media_control.setBackground(null);
                IV_fragrance_control.setBackground(null);
                myViewPager.setCurrentItem(1);
                break;
            case R.id.IV_media_control:
                IV_conditioning_control.setImageDrawable(getDrawable(R.mipmap.icon_air));
                IV_seat_control.setImageDrawable(getDrawable(R.mipmap.icon_seat));
                IV_media_control.setImageDrawable(getDrawable(R.mipmap.icon_media_choose));
                IV_fragrance_control.setImageDrawable(getDrawable(R.mipmap.icon_fragrance));

                IV_conditioning_control.setBackground(null);
                IV_seat_control.setBackground(null);
                IV_media_control.setBackground(getDrawable(R.mipmap.bg_tab_choose));
                IV_fragrance_control.setBackground(null);
                myViewPager.setCurrentItem(2);
                break;
            case R.id.IV_fragrance_control:
                IV_conditioning_control.setImageDrawable(getDrawable(R.mipmap.icon_air));
                IV_seat_control.setImageDrawable(getDrawable(R.mipmap.icon_seat));
                IV_media_control.setImageDrawable(getDrawable(R.mipmap.icon_media));
                IV_fragrance_control.setImageDrawable(getDrawable(R.mipmap.icon_fragrance_choose));

                IV_conditioning_control.setBackground(null);
                IV_seat_control.setBackground(null);
                IV_media_control.setBackground(null);
                IV_fragrance_control.setBackground(getDrawable(R.mipmap.bg_tab_choose));
                myViewPager.setCurrentItem(3);
                break;

        }
    }
    public class MyPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageSelected(int i) {
            switch (i){
                case 0:
                    IV_conditioning_control.setImageDrawable(getDrawable(R.mipmap.icon_air_choose));
                    IV_seat_control.setImageDrawable(getDrawable(R.mipmap.icon_seat));
                    IV_media_control.setImageDrawable(getDrawable(R.mipmap.icon_media));
                    IV_fragrance_control.setImageDrawable(getDrawable(R.mipmap.icon_fragrance));

                    IV_conditioning_control.setBackground(getDrawable(R.mipmap.bg_tab_choose));
                    IV_seat_control.setBackground(null);
                    IV_media_control.setBackground(null);
                    IV_fragrance_control.setBackground(null);
                    myViewPager.setCurrentItem(0);
                    break;
                case 1:
                    IV_conditioning_control.setImageDrawable(getDrawable(R.mipmap.icon_air));
                    IV_seat_control.setImageDrawable(getDrawable(R.mipmap.icon_seat_choose));
                    IV_media_control.setImageDrawable(getDrawable(R.mipmap.icon_media));
                    IV_fragrance_control.setImageDrawable(getDrawable(R.mipmap.icon_fragrance));

                    IV_conditioning_control.setBackground(null);
                    IV_seat_control.setBackground(getDrawable(R.mipmap.bg_tab_choose));
                    IV_media_control.setBackground(null);
                    IV_fragrance_control.setBackground(null);
                    myViewPager.setCurrentItem(1);
                    break;
                case 2:
                    IV_conditioning_control.setImageDrawable(getDrawable(R.mipmap.icon_air));
                    IV_seat_control.setImageDrawable(getDrawable(R.mipmap.icon_seat));
                    IV_media_control.setImageDrawable(getDrawable(R.mipmap.icon_media_choose));
                    IV_fragrance_control.setImageDrawable(getDrawable(R.mipmap.icon_fragrance));

                    IV_conditioning_control.setBackground(null);
                    IV_seat_control.setBackground(null);
                    IV_media_control.setBackground(getDrawable(R.mipmap.bg_tab_choose));
                    IV_fragrance_control.setBackground(null);
                    myViewPager.setCurrentItem(2);
                    break;
                case 3:
                    IV_conditioning_control.setImageDrawable(getDrawable(R.mipmap.icon_air));
                    IV_seat_control.setImageDrawable(getDrawable(R.mipmap.icon_seat));
                    IV_media_control.setImageDrawable(getDrawable(R.mipmap.icon_media));
                    IV_fragrance_control.setImageDrawable(getDrawable(R.mipmap.icon_fragrance_choose));

                    IV_conditioning_control.setBackground(null);
                    IV_seat_control.setBackground(null);
                    IV_media_control.setBackground(null);
                    IV_fragrance_control.setBackground(getDrawable(R.mipmap.bg_tab_choose));
                    myViewPager.setCurrentItem(3);
                    break;
            }
        }

        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    }

}
