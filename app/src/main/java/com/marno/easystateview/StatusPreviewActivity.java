package com.marno.easystateview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.marno.easystatelibrary.EasyStatusView;

public class StatusPreviewActivity extends AppCompatActivity {


    private EasyStatusView mEsvLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_preview);
        mEsvLayout = (EasyStatusView) findViewById(R.id.esvlayout);
        mEsvLayout.loading();

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_content:
                mEsvLayout.content();
                break;
            case R.id.tv_empty:
                mEsvLayout.empty();
                break;
            case R.id.tv_error:
                mEsvLayout.error();
                break;
            case R.id.tv_loading:
                mEsvLayout.loading();
                break;
            case R.id.tv_noNet:
                mEsvLayout.noNet();
                break;
        }
    }
}
