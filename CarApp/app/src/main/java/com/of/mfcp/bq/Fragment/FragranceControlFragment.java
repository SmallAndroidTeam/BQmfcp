package com.of.mfcp.bq.Fragment;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.of.mfcp.bq.DefineView.AutoLocateRecycleView;
import com.of.mfcp.bq.R;

import java.util.ArrayList;


public class FragranceControlFragment extends Fragment implements View.OnClickListener {
    private View fragrance_item;
    private Button btnFragranceOpenOrClose;
    private ImageButton picFragranceYhhyDisable;
    private ImageButton picFragranceJdssDisable;
    private ImageButton picFragranceMlcsDisable;
    private ImageButton ivBgChooseYhhy;
    private ImageButton ivBgChooseJdss;
    private ImageButton ivBgChooseMlcs;
    private Button btnHigh;
    private Button btnLow;


    private AnimationDrawable animationDrawableOpen;
    private AnimationDrawable animationDrawableClose;




    private boolean fragranceOpen;
    private boolean isHigh;
    private boolean canClick;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragrance_item = inflater.inflate(R.layout.fragrance_control_item,null);
        initView();
        viewClick();

        return fragrance_item;
    }

    private void initView() {
        btnFragranceOpenOrClose = (Button) fragrance_item.findViewById(R.id.btn_fragrance_openorclose);
        picFragranceYhhyDisable = (ImageButton)  fragrance_item.findViewById(R.id.pic_fragrance_yhhy_disable);
        picFragranceJdssDisable = (ImageButton) fragrance_item.findViewById(R.id.pic_fragrance_jdss_disable);
        picFragranceMlcsDisable = (ImageButton) fragrance_item.findViewById(R.id.pic_fragrance_mlcs_disable);
        ivBgChooseYhhy = (ImageButton) fragrance_item.findViewById(R.id.iv_bg_choose_yhhy);
        ivBgChooseJdss = (ImageButton) fragrance_item.findViewById(R.id.iv_bg_choose_jdss);
        ivBgChooseMlcs = (ImageButton) fragrance_item.findViewById(R.id.iv_bg_choose_mlcs);
        btnHigh = (Button) fragrance_item.findViewById(R.id.btn_high);
        btnLow = (Button) fragrance_item.findViewById(R.id.btn_low);




        fragranceOpen = false;
        isHigh = true;
        canClick = false;


    }

    private void viewClick(){
        btnFragranceOpenOrClose.setOnClickListener(this);
        picFragranceYhhyDisable.setOnClickListener(this);
        picFragranceJdssDisable.setOnClickListener(this);
        picFragranceMlcsDisable.setOnClickListener(this);
        btnHigh.setOnClickListener(this);
        btnLow.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_fragrance_openorclose:
                if (fragranceOpen==false){
                    btnFragranceOpenOrClose.setBackgroundResource(R.drawable.fragrance_btn_choose_animation);
                    animationDrawableOpen = (AnimationDrawable) btnFragranceOpenOrClose.getBackground();
                    btnFragranceOpenOrClose.setText("关闭");
                    btnFragranceOpenOrClose.setBackground(animationDrawableOpen);
                    animationDrawableOpen.start();
                    picFragranceYhhyDisable.setBackground(getResources().getDrawable(R.mipmap.pic_fragrance_yhhy));
                    picFragranceJdssDisable.setBackground(getResources().getDrawable(R.mipmap.pic_fragrance_jdss));
                    picFragranceMlcsDisable.setBackground(getResources().getDrawable(R.mipmap.pic_fragrance_mlcs));
                    if (isHigh){
                        btnHigh.setBackground(getResources().getDrawable(R.drawable.button_frame_choose));
                    }else {
                        btnLow.setBackground(getResources().getDrawable(R.drawable.button_frame_choose));
                    }

                    canClick = true;
                    fragranceOpen=true;
                }else{
                    btnFragranceOpenOrClose.setBackgroundResource(R.drawable.fragrance_btn_close_animation);
                    animationDrawableClose = (AnimationDrawable) btnFragranceOpenOrClose.getBackground();
                    btnFragranceOpenOrClose.setText("打开");
                    btnFragranceOpenOrClose.setBackground(animationDrawableClose);
                    animationDrawableClose.start();
                    picFragranceYhhyDisable.setBackground(getResources().getDrawable(R.mipmap.pic_fragrance_yhhy_disable));
                    picFragranceJdssDisable.setBackground(getResources().getDrawable(R.mipmap.pic_fragrance_jdss_disable));
                    picFragranceMlcsDisable.setBackground(getResources().getDrawable(R.mipmap.pic_fragrance_mlcs_disable));

                    ivBgChooseYhhy.setVisibility(View.INVISIBLE);
                    ivBgChooseJdss.setVisibility(View.INVISIBLE);
                    ivBgChooseMlcs.setVisibility(View.INVISIBLE);
                    if (!isHigh){
                        btnLow.setBackground(getResources().getDrawable(R.drawable.button_frame));
                    }else{
                        btnHigh.setBackground(getResources().getDrawable(R.drawable.button_frame));
                    }

                    canClick = false;
                    fragranceOpen=false;
                }
                break;
            case R.id.btn_high:
                if (canClick){
                    btnLow.setBackground(getResources().getDrawable(R.mipmap.bg_deep));
                    btnHigh.setBackground(getResources().getDrawable(R.drawable.button_frame_choose));
                    isHigh = true;
                }

                break;
            case R.id.btn_low:
                if (canClick){
                    btnHigh.setBackground(getResources().getDrawable(R.mipmap.bg_deep));
                    btnLow.setBackground(getResources().getDrawable(R.drawable.button_frame_choose));
                    isHigh = false;
                }

                break;
            case R.id.pic_fragrance_yhhy_disable:
                if (canClick){
                    ivBgChooseYhhy.setVisibility(View.VISIBLE);
                    ivBgChooseJdss.setVisibility(View.INVISIBLE);
                    ivBgChooseMlcs.setVisibility(View.INVISIBLE);
                }

                break;
            case R.id.pic_fragrance_jdss_disable:
                if (canClick){
                    ivBgChooseYhhy.setVisibility(View.INVISIBLE);
                    ivBgChooseJdss.setVisibility(View.VISIBLE);
                    ivBgChooseMlcs.setVisibility(View.INVISIBLE);
                }

                break;
            case R.id.pic_fragrance_mlcs_disable:
                if (canClick){
                    ivBgChooseYhhy.setVisibility(View.INVISIBLE);
                    ivBgChooseJdss.setVisibility(View.INVISIBLE);
                    ivBgChooseMlcs.setVisibility(View.VISIBLE);
                }

                break;

        }
    }
}
