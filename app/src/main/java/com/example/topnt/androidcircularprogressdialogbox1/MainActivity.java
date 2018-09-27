package com.example.topnt.androidcircularprogressdialogbox1;

import android.app.ProgressDialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Handler handler;
    Timer timer;
    Runnable runnable;

    int i=0;

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setIndeterminate(false);
        dialog.setTitle("Downloading");
        dialog.setMessage("Please wait while your content is downloading!");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        dialog.setMax(50);

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                i=i+2;
                if (i<=50){
                    dialog.setProgress(i);
                }else {
                    timer.cancel();
                    dialog.dismiss();
                    i=0;
                }
            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        }, 5000,200);
    }
}
