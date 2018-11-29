package com.of.mfcp.bq.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.of.mfcp.bq.Adapter.ModeAdapter;
import com.of.mfcp.bq.DefineView.AutoLocateRecycleView;
import com.of.mfcp.bq.DefineView.WheelView;
import com.of.mfcp.bq.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MediaControlFragment extends Fragment {
    private static final String TAG = "MediaControlFragment";
    private int soundProgress;
    private SeekBar sbSound;
    private TextView tvSound;
    private View mediaControl_item;
    //private AutoLocateRecycleView rvEqualizer;
    private WheelView wheelView;

    private ModeAdapter equalizerAdapter;
    private String[] modes = new String[]{"摇滚", "流行", "爵士", "打击", "古典", "默认","自定义"};
    List<String> modeList;
@Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mediaControl_item = inflater.inflate(R.layout.media_control_item,null);
        initView();
        viewClick();
        return mediaControl_item;
    }
    private void initView(){
        sbSound = (SeekBar) mediaControl_item.findViewById(R.id.sb_sound);
        tvSound = (TextView)mediaControl_item.findViewById(R.id.tv_sound);
        //rvEqualizer = (AutoLocateRecycleView) mediaControl_item.findViewById(R.id.rv_equalizer);
        wheelView = (WheelView) mediaControl_item.findViewById(R.id.wv_equalizer);

        modeList = new ArrayList<>();
        for (String mode:modes){
            modeList.add(mode);
        }
        equalizerAdapter = new ModeAdapter(getContext(),modeList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        wheelView.setOffset(1);
        wheelView.setItems(Arrays.asList(modes));
        wheelView.setOnWheelViewListener(new WheelView.OnWheelViewListener(){
            @Override
            public void onSelected(int selectedIndex, String item) {
                Log.d(TAG, "selectedIndex: " + selectedIndex + ", item: " + item);
            }
        });
//        rvEqualizer.setLayoutManager(linearLayoutManager);
//        rvEqualizer.setInitPos(1);
//        rvEqualizer.setAdapter(equalizerAdapter);
    }

    private void viewClick(){
        sbSound.setOnSeekBarChangeListener(mSeekbarChange);

    }



    SeekBar.OnSeekBarChangeListener mSeekbarChange = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            soundProgress = progress;
            tvSound.setText(String.valueOf(soundProgress));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };


}
