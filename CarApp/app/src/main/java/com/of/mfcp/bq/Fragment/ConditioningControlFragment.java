package com.of.mfcp.bq.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.String;

import com.of.mfcp.bq.R;


public class ConditioningControlFragment extends Fragment implements View.OnClickListener {
    private ImageButton IB_TemperatureUp_Driver;
    private ImageButton IB_TemperatureDown_Driver;
    private ImageButton IB_WindSpeedUp_Driver;
    private ImageButton IB_WindSpeedDown_Driver;
    private ImageButton IB_TemperatureUp_ViceDriver;
    private ImageButton IB_TemperatureDown_ViceDriver;
    private ImageButton IB_WindSpeedUp_ViceDriver;
    private ImageButton IB_WindSpeedDown_ViceDriver;
    private TextView TV_Temperature_Driver;
    private TextView TV_Wind_Speed_Driver;
    private TextView TV_Temperature_ViceDriver;
    private TextView TV_Wind_Speed_ViceDriver;
    private int Temperature_Driver=26;
    private int WindSpeed_Driver=3;
    private int Temperature_ViceDriver=26;
    private int WindSpeed_ViceDriver=3;
    private View conditioningControl_item;
    private ImageButton IB_AorC;
    private ImageButton IB_OFF;
    private ImageButton IB_Wind1;
    private ImageButton IB_SYNC;
    private ImageButton IB_InnerLoop;
    private ImageButton IB_Deforst;

    private ImageView bg_line_choose_ac;
    private ImageView bg_line_choose_sync;

    private boolean on_ac;
    private boolean on_wind;
    private boolean on_sync;
    private boolean on_off;
    private boolean on_loop;
    private boolean on_deforst;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        conditioningControl_item = inflater.inflate(R.layout.conditioning_control_item,null);
        InitView();
        ViewClick();

        return conditioningControl_item;
    }
    private void InitView(){
        IB_TemperatureUp_Driver = (ImageButton)conditioningControl_item.findViewById(R.id.IB_TemperatureUp_Driver);
        IB_TemperatureDown_Driver = (ImageButton)conditioningControl_item.findViewById(R.id.IB_TemperatureDown_Driver);
        IB_WindSpeedUp_Driver = (ImageButton)conditioningControl_item.findViewById(R.id.IB_WindSpeedUp_Driver);
        IB_WindSpeedDown_Driver = (ImageButton)conditioningControl_item.findViewById(R.id.IB_WindSpeedDown_Driver);
        IB_TemperatureUp_ViceDriver = (ImageButton)conditioningControl_item.findViewById(R.id.IB_TemperatureUp_ViceDriver);
        IB_TemperatureDown_ViceDriver = (ImageButton)conditioningControl_item.findViewById(R.id.IB_TemperatureDown_ViceDriver);
        IB_WindSpeedUp_ViceDriver = (ImageButton)conditioningControl_item.findViewById(R.id.IB_WindSpeedUp_ViceDriver);
        IB_WindSpeedDown_ViceDriver = (ImageButton)conditioningControl_item.findViewById(R.id.IB_WindSpeedDown_ViceDriver);
        TV_Temperature_Driver = (TextView)conditioningControl_item.findViewById(R.id.TV_Temperature_Driver);
        TV_Wind_Speed_Driver = (TextView)conditioningControl_item.findViewById(R.id.TV_Wind_Speed_Driver);
        TV_Temperature_ViceDriver = (TextView)conditioningControl_item.findViewById(R.id.TV_Temperature_ViceDriver);
        TV_Wind_Speed_ViceDriver = (TextView)conditioningControl_item.findViewById(R.id.TV_Wind_Speed_ViceDriver);

        TV_Temperature_Driver.setText(String.valueOf(Temperature_Driver));
        TV_Wind_Speed_Driver.setText(String.valueOf(WindSpeed_Driver));
        TV_Temperature_ViceDriver.setText(String.valueOf(Temperature_ViceDriver));
        TV_Wind_Speed_ViceDriver.setText(String.valueOf(WindSpeed_ViceDriver));

        IB_AorC = (ImageButton)conditioningControl_item.findViewById(R.id.IB_AorC);
        IB_OFF = (ImageButton)conditioningControl_item.findViewById(R.id.IB_OFF);
        IB_Wind1 = (ImageButton) conditioningControl_item.findViewById(R.id.IB_Wind1);
        IB_SYNC = (ImageButton) conditioningControl_item.findViewById(R.id.IB_SYNC);
        IB_InnerLoop = (ImageButton) conditioningControl_item.findViewById(R.id.IB_InnerLoop);
        IB_Deforst = (ImageButton) conditioningControl_item.findViewById(R.id.IB_Deforst);

        bg_line_choose_ac = (ImageView) conditioningControl_item.findViewById(R.id.bg_line_choose_ac);
        bg_line_choose_sync = (ImageView) conditioningControl_item.findViewById(R.id.bg_line_choose_sync);

        on_ac=false;
        on_wind=false;
        on_sync=false;
        on_off=false;
        on_loop=false;
        on_deforst=false;
    }
    private void ViewClick(){
        IB_TemperatureUp_Driver.setOnClickListener(this);
        IB_TemperatureDown_Driver.setOnClickListener(this);
        IB_WindSpeedUp_Driver.setOnClickListener(this);
        IB_WindSpeedDown_Driver.setOnClickListener(this);
        IB_TemperatureUp_ViceDriver.setOnClickListener(this);
        IB_TemperatureDown_ViceDriver.setOnClickListener(this);
        IB_WindSpeedUp_ViceDriver.setOnClickListener(this);
        IB_WindSpeedDown_ViceDriver.setOnClickListener(this);

        IB_AorC.setOnClickListener(this);
        IB_OFF.setOnClickListener(this);
        IB_Wind1.setOnClickListener(this);
        IB_SYNC.setOnClickListener(this);
        IB_InnerLoop.setOnClickListener(this);
        IB_Deforst.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.IB_TemperatureUp_Driver:
                if (Temperature_Driver<32){
                    Temperature_Driver++;
                    TV_Temperature_Driver.setText(String.valueOf(Temperature_Driver));
                }else{
                    Toast.makeText(getContext(),"已超出空调温度控制范围",Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.IB_TemperatureDown_Driver:
                if (Temperature_Driver>16){
                    Temperature_Driver--;
                    TV_Temperature_Driver.setText(String.valueOf(Temperature_Driver));
                }else{
                    Toast.makeText(getContext(),"已超出空调温度控制范围",Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.IB_WindSpeedUp_Driver:
                if (WindSpeed_Driver<7){
                    WindSpeed_Driver++;
                    TV_Wind_Speed_Driver.setText(String.valueOf(WindSpeed_Driver));
                }else{
                    Toast.makeText(getContext(),"已超出风扇速度控制范围",Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.IB_WindSpeedDown_Driver:
                if (WindSpeed_Driver>1){
                    WindSpeed_Driver--;
                    TV_Wind_Speed_Driver.setText(String.valueOf(WindSpeed_Driver));
                }else{
                    Toast.makeText(getContext(),"已超出风扇速度控制范围",Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.IB_TemperatureUp_ViceDriver:
                if (Temperature_ViceDriver<32){
                    Temperature_ViceDriver++;
                    TV_Temperature_ViceDriver.setText(String.valueOf(Temperature_ViceDriver));
                }else{
                    Toast.makeText(getContext(),"已超出空调温度控制范围",Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.IB_TemperatureDown_ViceDriver:
                if (Temperature_ViceDriver>16){
                    Temperature_ViceDriver--;
                    TV_Temperature_ViceDriver.setText(String.valueOf(Temperature_ViceDriver));
                }else{
                    Toast.makeText(getContext(),"已超出空调温度控制范围",Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.IB_WindSpeedUp_ViceDriver:
                if (WindSpeed_ViceDriver<7){
                    WindSpeed_ViceDriver++;
                    TV_Wind_Speed_ViceDriver.setText(String.valueOf(WindSpeed_ViceDriver));
                }else{
                    Toast.makeText(getContext(),"已超出风扇速度控制范围",Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.IB_WindSpeedDown_ViceDriver:
                if (WindSpeed_ViceDriver>1){
                    WindSpeed_ViceDriver--;
                    TV_Wind_Speed_ViceDriver.setText(String.valueOf(WindSpeed_ViceDriver));
                }else{
                    Toast.makeText(getContext(),"已超出风扇速度控制范围",Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.IB_AorC:
                if(on_ac==false){
                    IB_AorC.setBackground(getResources().getDrawable(R.mipmap.icon_ac));
                    bg_line_choose_ac.setVisibility(View.VISIBLE);
                    on_ac=true;

                }else {
                    IB_AorC.setBackground(getResources().getDrawable(R.mipmap.icon_ac_opacity));
                    bg_line_choose_ac.setVisibility(View.INVISIBLE);
                    on_ac=false;
                }

                break;
            case R.id.IB_OFF:
                bg_line_choose_ac.setVisibility(View.INVISIBLE);
                bg_line_choose_sync.setVisibility(View.INVISIBLE);
                IB_AorC.setBackground(getResources().getDrawable(R.mipmap.icon_ac_opacity));
                IB_Wind1.setBackground(getResources().getDrawable(R.mipmap.icon_wind1_opacity));
                IB_SYNC.setBackground(getResources().getDrawable(R.mipmap.icon_sync_opacity));
                IB_OFF.setBackground(getResources().getDrawable(R.mipmap.icon_off_opacity));
                IB_InnerLoop.setBackground(getResources().getDrawable(R.mipmap.icon_loop_opacity));
                IB_Deforst.setBackground(getResources().getDrawable(R.mipmap.icon_defrost_opacity));
                break;
            case R.id.IB_Wind1:
                if (on_wind==false){
                    IB_Wind1.setBackground(getResources().getDrawable(R.mipmap.icon_wind1));
                    on_wind=true;
                }else {
                    IB_Wind1.setBackground(getResources().getDrawable(R.mipmap.icon_wind1_opacity));
                    on_wind=false;
                }
                break;
            case R.id.IB_SYNC:
                if (on_sync==false){
                    IB_SYNC.setBackground(getResources().getDrawable(R.mipmap.icon_sync));
                    bg_line_choose_sync.setVisibility(View.VISIBLE);
                    on_sync=true;
                }else {
                    IB_SYNC.setBackground(getResources().getDrawable(R.mipmap.icon_sync_opacity));
                    bg_line_choose_sync.setVisibility(View.INVISIBLE);
                    on_sync=false;
                }
                break;
            case R.id.IB_InnerLoop:
                if (on_loop==false){
                    IB_InnerLoop.setBackground(getResources().getDrawable(R.mipmap.icon_loop));
                    on_loop=true;
                }else {
                    IB_InnerLoop.setBackground(getResources().getDrawable(R.mipmap.icon_loop_opacity));
                    on_loop=false;
                }
                break;
            case R.id.IB_Deforst:
                if (on_deforst==false){
                    IB_Deforst.setBackground(getResources().getDrawable(R.mipmap.icon_defrost));
                    on_deforst=true;
                }else {
                    IB_Deforst.setBackground(getResources().getDrawable(R.mipmap.icon_defrost_opacity));
                    on_deforst=false;
                }
                break;
        }
    }
}
