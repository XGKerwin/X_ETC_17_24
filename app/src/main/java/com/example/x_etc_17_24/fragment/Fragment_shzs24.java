package com.example.x_etc_17_24.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.x_etc_17_24.R;
import com.example.x_etc_17_24.bean.SHZS24;
import com.example.x_etc_17_24.net.OkHttpLo;
import com.example.x_etc_17_24.net.OkHttpTo;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/3 17:19
 */
public class Fragment_shzs24 extends Fragment {

    private TextView title;
    private TextView txtWendu;
    private ImageView btnShuaxin;
    private TextView txtJintian;
    private LineChart Linechart;
    private List<SHZS24> shzs24List;
    private String[] arr = {"周天", "周一", "周二", "周三", "周四", "周五", "周六", "周天", "周一", "周二", "周三"};
    private TextView txt1;
    private TextView txt2;
    private TextView txt3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_shzs24, null);
        initView(view);
        title.setText("生活助手");

        getTime();

        btnShuaxin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOkHttp();
            }
        });

        getOkHttp();


        return view;
    }

    private void getTime() {
        Calendar calendar = Calendar.getInstance();
        Log.i("ZZZZZZZ", "getTime: " + calendar);
        int day = calendar.get(Calendar.DAY_OF_WEEK);


        int day1 = day + 2;
        int day2 = day + 3;
        int day3 = day + 4;
        txt1.setText(arr[day1]);
        txt2.setText(arr[day2]);
        txt3.setText(arr[day3]);

    }

    private void getOkHttp() {
        if (shzs24List == null) {
            shzs24List = new ArrayList<>();
        } else {
            shzs24List.clear();
        }
        //{"UserName":"user1"}
        new OkHttpTo()
                .setUrl("get_weather_info")
                .setJsonObject("UserName", "user1")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        txtWendu.setText(jsonObject.optString("temperature"));
                        shzs24List.addAll((Collection<? extends SHZS24>) new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<SHZS24>>() {
                                }.getType()));
                        show();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void show() {
        String s = shzs24List.get(1).getInterval();
        txtJintian.setText("今天：" + s.replace("~", " - ") + "℃");

        gethuatu();


    }

    private void gethuatu() {

        List<Entry> maxEntry = new ArrayList<>();
        List<Entry> minEntry = new ArrayList<>();
        for (int i = 0; i < shzs24List.size(); i++) {
            String[] arr = shzs24List.get(i).getInterval().split("~");
            maxEntry.add(new Entry(i, Integer.parseInt(arr[1])));
            minEntry.add(new Entry(i, Integer.parseInt(arr[0])));
        }
        LineDataSet dataSet = new LineDataSet(maxEntry, "");
        LineDataSet dataSet1 = new LineDataSet(minEntry, "");
        dataSet.setCircleColor(Color.RED);
        dataSet.setColor(Color.RED);
        dataSet.setDrawCircleHole(false);
        dataSet.setCircleHoleRadius(5);
        dataSet.setLineWidth(4);
        dataSet1.setCircleColor(Color.BLUE);
        dataSet1.setColor(Color.BLUE);
        dataSet1.setDrawCircleHole(false);
        dataSet1.setCircleHoleRadius(5);
        dataSet1.setLineWidth(4);
        List<ILineDataSet> iLineDataSets = new ArrayList<>();
        iLineDataSets.add(dataSet);
        iLineDataSets.add(dataSet1);
        LineData data = new LineData(iLineDataSets);
        Linechart.setData(data);
        Linechart.getAxisRight().setEnabled(false);
        YAxis yAxis = Linechart.getAxisLeft();
        yAxis.setDrawAxisLine(false);
        yAxis.setTextColor(Color.TRANSPARENT);
        Linechart.animateXY(1500, 1000);
        Linechart.getXAxis().setEnabled(false);
        Linechart.getLegend().setEnabled(false);
        Linechart.getDescription().setEnabled(false);
        Linechart.setTouchEnabled(false);
        Linechart.invalidate();

    }


    private void initView(View view) {
        title = getActivity().findViewById(R.id.titlt);
        txtWendu = view.findViewById(R.id.txt_wendu);
        btnShuaxin = view.findViewById(R.id.btn_shuaxin);
        txtJintian = view.findViewById(R.id.txt_jintian);
        Linechart = view.findViewById(R.id.Linechart);
        txt1 = view.findViewById(R.id.txt_1);
        txt2 = view.findViewById(R.id.txt_2);
        txt3 = view.findViewById(R.id.txt_3);
    }
}
