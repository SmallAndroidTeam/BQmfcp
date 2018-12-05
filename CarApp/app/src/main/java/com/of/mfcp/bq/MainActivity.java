package com.of.mfcp.bq;

import android.content.Context;
import android.content.Intent;
import android.hardware.display.DisplayManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.of.mfcp.bq.DifferentDislay;
import com.of.mfcp.bq.R;

public class MainActivity extends AppCompatActivity {

    private DifferentDislay mPresentation;
    public static FragmentManager fragmentManager;
    Button btnMajorTest;
    TextView tvMajorTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test);

        btnMajorTest = (Button) findViewById(R.id.btn_majortest);
        tvMajorTest = (TextView) findViewById(R.id.tv_majortest);
        fragmentManager = getSupportFragmentManager();

        if (Build.VERSION.SDK_INT >= 23) {
            if (! Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent,10);
            }
        }

        btnMajorTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"you clicked major screen", Toast.LENGTH_SHORT).show();
            }
        });

        DisplayManager manager = (DisplayManager) getSystemService(Context.DISPLAY_SERVICE);
        Display[] displays = manager.getDisplays();
        // displays[0] 主屏
        // displays[1] 副屏
        DifferentDislay differentDislay = new DifferentDislay(this,displays[1]);
        differentDislay.getWindow().setType(
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
        //DifferentDislay.myViewPager.addOnPageChangeListener(new DifferentDislay(DifferentDislay.mcontext,displays[1]).MyPageChangeListener());
        differentDislay.show();
    }
/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 10) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (!Settings.canDrawOverlays(this)) {
                    // SYSTEM_ALERT_WINDOW permission not granted...
                    Toast.makeText(LoginActivity.this,"not granted",Toast.LENGTH_SHORT);
                }
            }
        }
*/
}