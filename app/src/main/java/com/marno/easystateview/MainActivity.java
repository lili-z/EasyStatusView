package com.marno.easystateview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.marno.easystatelibrary.EasyStatusView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private EasyStatusView mEsvLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEsvLayout = (EasyStatusView) findViewById(R.id.esvlayout);
        View emptyView = LayoutInflater.from(this).inflate(R.layout.layout_empty_changed, null);
        mEsvLayout.setEmptyView(emptyView);
        mEsvLayout.setErrorLayoutId(R.layout.layout_error);
        mEsvLayout.empty();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mEsvLayout.error();
                    }
                });
            }
        }, 2000);


        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mEsvLayout.empty();
                    }
                });
            }
        }, 3000);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mEsvLayout.loading();
                    }
                });
            }
        }, 4000);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mEsvLayout.content();
                    }
                });
            }
        }, 5000);


    }
}
