package com.example.x_etc_17_24.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.x_etc_17_24.R;
import com.example.x_etc_17_24.bean.CXJL;

import org.litepal.LitePal;

import java.util.List;

public class Activity_zhgl extends AppCompatActivity {

    private ImageView caidan;
    private TextView titlt;
    private ListView listview;
    private X_List_22_czjl_adapter adapter;
    private List<CXJL> cxjlList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhgl);
        initView();
        cxjlList = LitePal.findAll(CXJL.class);

        caidan.setImageResource(R.drawable.back);
        caidan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        titlt.setText("充值记录");

        adapter = new X_List_22_czjl_adapter(cxjlList);
        listview.setAdapter(adapter);

    }

    private void initView() {
        caidan = findViewById(R.id.caidan);
        titlt = findViewById(R.id.titlt);
        listview = findViewById(R.id.listview);
    }
}