package com.of.mfcp.bq.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.of.mfcp.bq.R;


public class SeatControlFragment extends Fragment implements View.OnClickListener {
    private View seatControl_item;
    private ImageButton icon_adjust;
    private ImageButton icon_adjust2;
    private ImageButton icon_plus_choose;
    private  ImageButton icon_plus_choose2;
    private ImageView bg_line_path;
    private ImageView bg_line_path2;

    private float lastX;
    private float lastY;

    private double  seatControlAngle=45*2*Math.PI/360;//设置默认的控制按钮与水平线的夹角为45度(Math 类中:PI 代表180°对应的弧度)
    private double  seatControlAngle2=45*2*Math.PI/360;
    private  final  static  float Arc_R=270;//设置默认圆的半径大小（单位为px)
    private  final static float SPACE=40;
    private double backseatAngle=0;//后背与Y轴的夹角，往左偏为负值，往右偏为正值
    private static final String TAG = "SeatControlFragment";
    private ImageView iconMinusChoose;
    private ImageView iconMinusChoose2;
    private  boolean isMeasured=false;
    private  boolean isMeasured2=false;
    private ImageView picSeatBackrest1;
    private ImageView picSeatBackrest2;

     private double MAX_ROTATION_ANGLE=30.0*2*Math.PI/360;//后背最大的旋转角度
    private double CHANGE_ANGLE=5.0*2*Math.PI/360;//点击增加和减小按钮，每次变化的角度
    private TextView tvBackAngle;
    private TextView tvBackAngle2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        seatControl_item = inflater.inflate(R.layout.seat_control_item,null);
        InitView();
        ViewClick();

        return seatControl_item;
    }

    @Override
    public void onResume() {
        super.onResume();
       ViewTreeObserver viewTreeObserver=icon_plus_choose.getViewTreeObserver();
       ViewTreeObserver viewTreeObserver2=icon_plus_choose2.getViewTreeObserver();
       viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
           @Override
           public void onGlobalLayout() {
                  if(!isMeasured){
                      double offsetX= Arc_R*(1-Math.cos(seatControlAngle));
                      double offsetY= Arc_R*Math.sin(seatControlAngle);
                      setSeatControInitlPosition(offsetX,offsetY);//设置控制按钮的初始位置
                      double seatAngle=getBackSeatAngleBySeatControlAngle(seatControlAngle);//获取靠背的角度
                      setBackseatAngle(backseatAngle,seatAngle);
                      isMeasured=true;
                  }

           }
       });

        viewTreeObserver2.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if(!isMeasured2){
                    double offsetX= Arc_R*(1-Math.cos(seatControlAngle2));
                    double offsetY= Arc_R*Math.sin(seatControlAngle2);
                    setSeatControInitlPosition2(offsetX,offsetY);//设置控制按钮的初始位置
                    double seatAngle=getBackSeatAngleBySeatControlAngle(seatControlAngle2);//获取靠背的角度
                    setBackseatAngle(backseatAngle,seatAngle);
                    isMeasured2=true;
                }

            }
        });
    }

    /**
     *
     * @param offsetX
     * @param offsetY
     * offsetX为控制按钮距离iconMinusChoose按钮的水平距离
     * offsetY为控制按钮距离iconMinusChoose按钮的垂直距离
     */
    private void setSeatControInitlPosition(double offsetX,double offsetY) {//根据offsetX，offsetY来设置icon_adjust的位置
        double X=160;
        double Y=323;
        //160 323 1120 323
        Log.i("jfKSDJF", "setSeatControInitlPosition: "+X+"//"+Y);
      RelativeLayout.LayoutParams layoutParams= (RelativeLayout.LayoutParams) icon_adjust.getLayoutParams();
//      RelativeLayout.LayoutParams layoutParams2= (RelativeLayout.LayoutParams) icon_adjust2.getLayoutParams();
      layoutParams.leftMargin= (int) (X+offsetX-SPACE);//SPACE表示icon_adjust的长宽
      layoutParams.topMargin= (int) (Y-offsetY-SPACE);
//      layoutParams2.rightMargin= (int) (X+offsetX-SPACE);//SPACE表示icon_adjust的长宽
//      layoutParams2.topMargin= (int) (Y-offsetY-SPACE);
        icon_adjust.setLayoutParams(layoutParams);
//        icon_adjust2.setLayoutParams(layoutParams2);
    }

    private void setSeatControInitlPosition2(double offsetX,double offsetY) {//根据offsetX，offsetY来设置icon_adjust的位置
        double X=160;
        double Y=323;
        //160 323 1120 323
        Log.i("jfKSDJF", "setSeatControInitlPosition: "+X+"//"+Y);
        //RelativeLayout.LayoutParams layoutParams= (RelativeLayout.LayoutParams) icon_adjust.getLayoutParams();
        RelativeLayout.LayoutParams layoutParams2= (RelativeLayout.LayoutParams) icon_adjust2.getLayoutParams();
        //layoutParams.leftMargin= (int) (X+offsetX-SPACE);//SPACE表示icon_adjust的长宽
        //layoutParams.topMargin= (int) (Y-offsetY-SPACE);
        layoutParams2.rightMargin= (int) (X+offsetX-SPACE);//SPACE表示icon_adjust的长宽
        layoutParams2.topMargin= (int) (Y-offsetY-SPACE);
        //icon_adjust.setLayoutParams(layoutParams);
        icon_adjust2.setLayoutParams(layoutParams2);
    }

    /**
     * 设置后背座椅的旋转角度
     */
    private  void setBackseatAngle(double currentAngle,double angle){
        double fromDegress=currentAngle;
        double toDegress=angle;
        if(currentAngle<0){
             fromDegress=2*Math.PI+currentAngle;
        }
        if(angle<0){
            toDegress=2*Math.PI+angle;
        }
        fromDegress=fromDegress/(2*Math.PI)*360;
        toDegress=toDegress/(2*Math.PI)*360;
        Log.i("Fksjdkfjd", "setBackseatAngle: "+fromDegress+"//"+toDegress);
        RotateAnimation animation=new RotateAnimation((float)fromDegress,(float)toDegress,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,1.0f);//（pivotXValue，pivotYValue）=（1,1）表示右下角
        animation.setDuration(0);
        animation.setInterpolator(new LinearInterpolator());//匀速旋转
        animation.setRepeatCount(0);//动画重复次数
        animation.setFillAfter(true);//控件动画结束时是否保持动画最后的状态
        picSeatBackrest1.setAnimation(animation);
        backseatAngle=angle;
        tvBackAngle.setText(String.format("%.1f",90+backseatAngle/(2*Math.PI)*360));
    }

    private  void setBackseatAngle2(double currentAngle,double angle){
        double fromDegress=currentAngle;
        double toDegress=angle;
        if(currentAngle<0){
            fromDegress=2*Math.PI+currentAngle;
        }
        if(angle<0){
            toDegress=2*Math.PI+angle;
        }
        fromDegress=fromDegress/(2*Math.PI)*360;
        toDegress=toDegress/(2*Math.PI)*360;
        Log.i("Fksjdkfjd", "setBackseatAngle: "+fromDegress+"//"+toDegress);
        RotateAnimation animation=new RotateAnimation((float)fromDegress,(float)toDegress,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,1.0f);//（pivotXValue，pivotYValue）=（1,1）表示右下角
        animation.setDuration(0);
        animation.setInterpolator(new LinearInterpolator());//匀速旋转
        animation.setRepeatCount(0);//动画重复次数
        animation.setFillAfter(true);//控件动画结束时是否保持动画最后的状态
        picSeatBackrest2.setAnimation(animation);
        backseatAngle=angle;
        tvBackAngle2.setText(String.format("%.1f",90-backseatAngle/(2*Math.PI)*360));
    }

    /**
     * 通过控制按钮（icon_adjust）的旋转角度获取后背座椅的角度
     * @param angle
     */
    private double getBackSeatAngleBySeatControlAngle(double angle){//座椅垂直时记角度为0
        final double rotation_angle=45.0*2*Math.PI/360;
       if(angle<0){
           return -30.0*2*Math.PI/360;
       }else if(angle<=45.0*2*Math.PI/360){
           return -1.0*(rotation_angle-angle)/rotation_angle*MAX_ROTATION_ANGLE;
       }else if(angle<=90*2*Math.PI/360){
return (angle-rotation_angle)/rotation_angle*MAX_ROTATION_ANGLE;
       }else{
           return (rotation_angle)/rotation_angle*MAX_ROTATION_ANGLE;
       }
    }

    private double getBackSeatAngleBySeatControlAngle2(double angle){//座椅垂直时记角度为0
        final double rotation_angle=45.0*2*Math.PI/360;
       if(angle<0){
           return 30.0*2*Math.PI/360;
       }else if(angle<=45.0*2*Math.PI/360){
           return 1.0*(rotation_angle-angle)/rotation_angle*MAX_ROTATION_ANGLE;
       }else if(angle<=90*2*Math.PI/360){
return -(angle-rotation_angle)/rotation_angle*MAX_ROTATION_ANGLE;
       }else{
           return -(rotation_angle)/rotation_angle*MAX_ROTATION_ANGLE;
       }
    }

    private void InitView(){
        icon_adjust = (ImageButton) seatControl_item.findViewById(R.id.icon_adjust_l03);
        icon_adjust2 = (ImageButton) seatControl_item.findViewById(R.id.icon_adjust_r03);
        icon_plus_choose = (ImageButton) seatControl_item.findViewById(R.id.icon_plus_choose);
        icon_plus_choose2 = (ImageButton) seatControl_item.findViewById(R.id.icon_plus_choose2);
        iconMinusChoose = seatControl_item.findViewById(R.id.icon_minus_choose);
        iconMinusChoose2 = seatControl_item.findViewById(R.id.icon_minus_choose2);
        bg_line_path = (ImageView) seatControl_item.findViewById(R.id.bg_line_path);
        bg_line_path2 = (ImageView) seatControl_item.findViewById(R.id.bg_line_path2);
        picSeatBackrest1 = seatControl_item.findViewById(R.id.pic_seat_backrest_l);
        picSeatBackrest2 = seatControl_item.findViewById(R.id.pic_seat_backrest_r);
        tvBackAngle = seatControl_item.findViewById(R.id.tv_back_angle);
        tvBackAngle2 = seatControl_item.findViewById(R.id.tv_back_angle2);
    }
    private void ViewClick(){
        icon_adjust.setOnTouchListener(touchListener_left);
        icon_adjust2.setOnTouchListener(touchListener_right);
        icon_plus_choose.setOnClickListener(this);
        iconMinusChoose.setOnClickListener(this);
    }
    public View.OnTouchListener touchListener_left = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {

            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    lastX = event.getRawX();
                    lastY = event.getRawY();
                    Log.i("fjksdjf", "ondown: 111");
                    return true;

                case MotionEvent.ACTION_MOVE:
                    synchronized (this){
                        float currentX=event.getRawX();
                        float currentY=event.getRawY();
                        float offsetX =  currentX - lastX;//计算滑动的距离
                        float offsetY =  currentY - lastY;
                        // v.layout((int)(v.getLeft()+offsetX),(int)(v.getTop()+offsetY),(int)(v.getRight()+offsetX),(int)(v.getBottom()+offsetY));
                        setSeatControlPosition(offsetX);  //重新设置新的位置
//                      lastX=currentX;
//                      lastY  =currentY;
                        Log.i("fjksdjf", "ondown: 222"+"//"+lastX+"//"+lastY+"//"+currentX+"//"+currentY+"//"+offsetX+"//"+offsetY+"//"+v.getLeft()+"//"+v.getRight()+"//"+v.getTop()+"//"+v.getBottom());
                    }

                    break;
                case MotionEvent.ACTION_UP:
                    float currentX=event.getRawX();
                    float currentY=event.getRawY();
                    float offsetX =  currentX- lastX;//计算滑动的距离
                    float offsetY = currentY-lastY;
                    changeSeatControlAngle(offsetX);//改变角度
                    break;

            }
            return false;
        }
    };

    public View.OnTouchListener touchListener_right = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {

            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    lastX = event.getRawX();
                    lastY = event.getRawY();
                    Log.i("fjksdjf", "ondown: 111");
                    break;
                case MotionEvent.ACTION_MOVE:
                    synchronized (this){
                        float currentX=event.getRawX();
                        float currentY=event.getRawY();
                        float offsetX =  currentX - lastX;//计算滑动的距离
                        float offsetY =  currentY - lastY;
                        // v.layout((int)(v.getLeft()+offsetX),(int)(v.getTop()+offsetY),(int)(v.getRight()+offsetX),(int)(v.getBottom()+offsetY));
                        setSeatControlPosition2(offsetX);  //重新设置新的位置
//                      lastX=currentX;
//                      lastY  =currentY;
                        Log.i("fjksdjf", "ondown: 222"+"//"+lastX+"//"+lastY+"//"+currentX+"//"+currentY+"//"+offsetX+"//"+offsetY+"//"+v.getLeft()+"//"+v.getRight()+"//"+v.getTop()+"//"+v.getBottom());
                    }

                    break;
                case MotionEvent.ACTION_UP:
                    float currentX=event.getRawX();
                    float currentY=event.getRawY();
                    float offsetX =  currentX- lastX;//计算滑动的距离
                    float offsetY = currentY-lastY;
                    changeSeatControlAngle2(offsetX);//改变角度
                    break;

            }
            return true;
        }
    };

    /**
     * 设置座椅控制按钮的位置
     * @param offsetX
     * offsetX为滑动的水平距离
     */
    //      v.layout((int)(v.getLeft()),(int)(v.getTop()),(int)(v.getRight()),(int)(v.getBottom()));
    private void setSeatControlPosition(float offsetX){//通过监控offsetX的大小计算出其他参数，最终决定icon_adjust的位置和座椅靠背的旋转角度
        double offsetY=0;
        double Bottom_Max_Height=Arc_R*Math.sin(seatControlAngle);//识别下滑的最大的距离
        double Top_Max_Height=Arc_R-Bottom_Max_Height;//识别上滑的最大距离

        double Right_Max_Width=Arc_R*Math.cos(seatControlAngle);//识别右滑的最大距离
        double Left_Max_Width=Arc_R-Right_Max_Width;//识别左滑的最大的距离
       // v.layout(l,t,r,b);
        if(offsetX>=0){
            if(offsetX>=Right_Max_Width){
                setSeatControInitlPosition(Arc_R,Arc_R);
                double Angle=90*2*Math.PI/360;
                setBackseatAngle(backseatAngle,getBackSeatAngleBySeatControlAngle(Angle));
         }else{
               offsetY=Math.sqrt(Math.pow(Arc_R,2)-Math.pow(Arc_R*Math.cos(seatControlAngle)-offsetX,2))-Arc_R*Math.sin(seatControlAngle);//Math.pow(x,n)方法,表示x的n次幂
              setSeatControInitlPosition(Arc_R*(1-Math.cos(seatControlAngle))+offsetX,Arc_R*Math.sin(seatControlAngle)+offsetY);
              double Angle=Math.acos((Arc_R*Math.cos(seatControlAngle)-offsetX)/Arc_R);
                setBackseatAngle(backseatAngle,getBackSeatAngleBySeatControlAngle(Angle));
            }
        }else{
              offsetX=Math.abs(offsetX);//去绝对值
               if(offsetX>=Left_Max_Width){
                   setSeatControInitlPosition(0,0);
                   setBackseatAngle(backseatAngle,getBackSeatAngleBySeatControlAngle(0));
               }else{
                   offsetY=Arc_R*Math.sin(seatControlAngle)-Math.sqrt(Math.pow(Arc_R,2)-Math.pow(Arc_R*Math.cos(seatControlAngle)+offsetX,2));
                   setSeatControInitlPosition(Arc_R*(1-Math.cos(seatControlAngle))-offsetX,Arc_R*Math.sin(seatControlAngle)-offsetY);
                  double Angle=Math.acos((Arc_R*Math.cos(seatControlAngle)+offsetX)/Arc_R);
                   setBackseatAngle(backseatAngle,getBackSeatAngleBySeatControlAngle(Angle));
               }
        }
    }

    private void setSeatControlPosition2(float offsetX){//通过监控offsetX(横向滑动距离)的大小计算出其他参数，最终决定icon_adjust的位置和座椅靠背的旋转角度
        double offsetY=0;
        double Bottom_Max_Height=Arc_R*Math.sin(seatControlAngle2);//识别下滑的最大的距离
        double Top_Max_Height=Arc_R-Bottom_Max_Height;//识别上滑的最大距离

        double Left_Max_Width=Arc_R*Math.cos(seatControlAngle2);//识别右滑的最大距离
        double Right_Max_Width=Arc_R-Left_Max_Width;//识别左滑的最大的距离
        // v.layout(l,t,r,b);
        if(offsetX>=0){
            if(offsetX>=Right_Max_Width){
                setSeatControInitlPosition2(0,0);
                setBackseatAngle2(backseatAngle,getBackSeatAngleBySeatControlAngle2(0));
            }else{
                offsetY=Arc_R*Math.sin(seatControlAngle2)-Math.sqrt(Math.pow(Arc_R,2)-Math.pow(Arc_R*Math.cos(seatControlAngle2)+offsetX,2));
                setSeatControInitlPosition2(Arc_R*(1-Math.cos(seatControlAngle2))-offsetX,Arc_R*Math.sin(seatControlAngle2)-offsetY);
                double Angle=Math.acos((Arc_R*Math.cos(seatControlAngle2)+offsetX)/Arc_R);
                setBackseatAngle2(backseatAngle,getBackSeatAngleBySeatControlAngle2(Angle));
            }
        }else{
            offsetX=Math.abs(offsetX);//去绝对值
            if(offsetX>=Left_Max_Width){
                setSeatControInitlPosition2(Arc_R,Arc_R);
                Log.d(TAG, "setSeatControlPosition2: offsetX>=Left_Max_Width");
                double Angle=90*2*Math.PI/360;
                setBackseatAngle2(backseatAngle,getBackSeatAngleBySeatControlAngle2(Angle));
            }else{
                offsetY=Math.sqrt(Math.pow(Arc_R,2)-Math.pow(Arc_R*Math.cos(seatControlAngle2)-offsetX,2))-Arc_R*Math.sin(seatControlAngle2);//Math.pow(x,n)方法,表示x的n次幂
                setSeatControInitlPosition2(Arc_R*(1-Math.cos(seatControlAngle2))+offsetX,Arc_R*Math.sin(seatControlAngle2)+offsetY);
                double Angle=Math.acos((Arc_R*Math.cos(seatControlAngle2)-offsetX)/Arc_R);
                setBackseatAngle2(backseatAngle,getBackSeatAngleBySeatControlAngle2(Angle));
            }
        }
    }

    /**
     * 改变角度
     * @param offsetX
     */
    private void changeSeatControlAngle(float offsetX){
        double offsetY=0;
        double Bottom_Max_Height=Arc_R*Math.sin(seatControlAngle);//识别下滑的最大的距离
        double Top_Max_Height=Arc_R-Bottom_Max_Height;//识别上滑的最大距离
        double Right_Max_Width=Arc_R*Math.cos(seatControlAngle);//识别右滑的最大距离
        double Left_Max_Width=Arc_R-Right_Max_Width;//识别左滑的最大的距离
        if(offsetX>=0){
            if(offsetX>=Right_Max_Width){
             seatControlAngle=90*2*Math.PI/360;
            }else{
               seatControlAngle=Math.acos((Arc_R*Math.cos(seatControlAngle)-offsetX)/Arc_R);
            }
        }else{
            offsetX=Math.abs(offsetX);//去绝对值
            if(offsetX>=Left_Max_Width){
                 seatControlAngle=0;
            }else{
                seatControlAngle=Math.acos((Arc_R*Math.cos(seatControlAngle)+offsetX)/Arc_R);
            }

        }
    }

    private void changeSeatControlAngle2(float offsetX){//offsetX为水平滑动距离
        double offsetY=0;
        double Bottom_Max_Height=Arc_R*Math.sin(seatControlAngle2);//识别下滑的最大的距离
        double Top_Max_Height=Arc_R-Bottom_Max_Height;//识别上滑的最大距离
        double Left_Max_Width=Arc_R*Math.cos(seatControlAngle2);//识别右滑的最大距离
        double Right_Max_Width=Arc_R-Left_Max_Width;//识别左滑的最大的距离
        if(offsetX>=0){
            if(offsetX>=Right_Max_Width){
                seatControlAngle2=0;
            }else{
                seatControlAngle2=Math.acos((Arc_R*Math.cos(seatControlAngle2)+offsetX)/Arc_R);
            }
        }else{
            offsetX=Math.abs(offsetX);//去绝对值
            if(offsetX>=Left_Max_Width){
                seatControlAngle2=90*2*Math.PI/360;
            }else{
                seatControlAngle2=Math.acos((Arc_R*Math.cos(seatControlAngle2)-offsetX)/Arc_R);
            }

        }
    }


    @Override
    public void onClick(View view) {
        final  double controlAngle=1.0*CHANGE_ANGLE/MAX_ROTATION_ANGLE*45*2*Math.PI/360;//靠背每次旋转5度对应控制按钮旋转的度数
        switch (view.getId()){
            case R.id.icon_plus_choose://增加角度
                if(backseatAngle+CHANGE_ANGLE>=MAX_ROTATION_ANGLE){
                    setBackseatAngle(backseatAngle,MAX_ROTATION_ANGLE);//设置靠背的旋转角度
                    setSeatControInitlPosition(Arc_R,Arc_R);//设置控制按钮的角度
                    seatControlAngle=90*2*Math.PI/360;
                }else{
                    setBackseatAngle(backseatAngle,backseatAngle+CHANGE_ANGLE);
                    setSeatControlPosition((float) (Arc_R*Math.abs(Math.cos(seatControlAngle)-Math.cos(seatControlAngle+controlAngle))));
                    changeSeatControlAngle((float) (Arc_R*Math.abs(Math.cos(seatControlAngle)-Math.cos(seatControlAngle+controlAngle))));
                }
                break;
            case R.id.icon_minus_choose://减小角度
                if(Math.abs(backseatAngle-CHANGE_ANGLE)>=MAX_ROTATION_ANGLE){
                    setBackseatAngle(backseatAngle,-1*MAX_ROTATION_ANGLE);
                    setSeatControInitlPosition(0,0);
                    seatControlAngle=0;
                }else{
                    setBackseatAngle(backseatAngle,backseatAngle-CHANGE_ANGLE);
                    setSeatControlPosition((float)( -1.0*Arc_R*Math.abs(Math.cos(seatControlAngle-controlAngle)-Math.cos(seatControlAngle))));
                    changeSeatControlAngle((float)( -1.0*Arc_R*Math.abs(Math.cos(seatControlAngle-controlAngle)-Math.cos(seatControlAngle))));
                }

                break;
        }
    }
}
