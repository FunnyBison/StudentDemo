package com.chenzhi.shop.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.chenzhi.shop.R;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {
    private Timer timer;
    private int i = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //使用timer实现的秒跳转界面取消timer
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        i--;
                        if (i == 0) {
                            timer.cancel();
                            //进入主界面
                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            }
        }, 0, 1000);
    }
}
