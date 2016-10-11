package com.marno.easystateview;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.marno.easystatelibrary.EasyStatusView;
import com.pacific.adapter.Adapter;
import com.pacific.adapter.AdapterHelper;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MockDataActivity extends AppCompatActivity {

    private EasyStatusView mEsvLayout;
    private ListView mLvContent;
    private Adapter<MockEntity> mAdapter;
    private Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mock_data);

        initView();
        setAdapter();
        loadData();
    }

    private void initView() {
        mLvContent = (ListView) findViewById(R.id.lv_content);
        mEsvLayout = (EasyStatusView) findViewById(R.id.esvlayout);
        mEsvLayout.loading();//显示加载中视图
    }

    private void setAdapter() {
        mAdapter = new Adapter<MockEntity>(this, R.layout.item_main) {
            @Override
            protected void convert(AdapterHelper helper, MockEntity item) {
                helper.setText(R.id.tv_name, item.name);
                helper.getItemView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDeleteDialog();
                    }
                });
            }
        };
        mLvContent.setAdapter(mAdapter);
    }

    private void showDeleteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        AlertDialog dialog = builder.setMessage("确认删除所有数据？")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        mAdapter.clear();
                        mEsvLayout.empty();
                    }
                }).create();
        dialog.show();
    }

    private void loadData() {
        final ArrayList<MockEntity> entities = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            entities.add(new MockEntity("EasyStatusView" + i));
        }
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.addAll(entities);
                        mEsvLayout.content();
                    }
                });
            }
        }, 3000);
    }
}
