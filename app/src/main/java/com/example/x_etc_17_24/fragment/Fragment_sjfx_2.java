package com.example.x_etc_17_24.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.x_etc_17_24.R;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/3 8:30
 */
public class Fragment_sjfx_2 extends Fragment {

    private HorizontalBarChart barChart;
    private List<BarEntry> barEntries;
    private List<Integer> colors;
    private List<String> strings;
    private List<Map.Entry<String,Integer>> ma;
    private int size;

    public Fragment_sjfx_2(List<Map.Entry<String, Integer>> maps, int size) {
        this.ma = maps;
        this.size = size;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_sjfx_2, null);
        initView(view);

        getData();


        return view;
    }

    private void getData() {
        if (barEntries == null){
            barEntries = new ArrayList<>();
            strings = new ArrayList<>();
            colors = new ArrayList<>();
        }else {
            colors.clear();
            strings.clear();
            barEntries.clear();
        }

        for (int i=0;i<ma.size();i++){
            float a1 = ma.get(i).getValue();
            float aa = a1/size*100;
            barEntries.add(new BarEntry(i,aa));
        }
        colors.add(Color.parseColor("#9CB3C8"));
        colors.add(Color.parseColor("#B9ABCF"));
        colors.add(Color.parseColor("#FFB16A"));
        colors.add(Color.parseColor("#BAD189"));
        colors.add(Color.parseColor("#FFC606"));
        colors.add(Color.parseColor("#634A7E"));
        colors.add(Color.parseColor("#ED6D06"));
        colors.add(Color.parseColor("#79943E"));
        colors.add(Color.parseColor("#4E83C0"));
        colors.add(Color.parseColor("#C50201"));

        BarDataSet dataSet = new BarDataSet(barEntries,"");
        dataSet.setColors(colors);
        dataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                DecimalFormat decimalFormat = new DecimalFormat("0.0");
                return decimalFormat.format(value*1)+"%";
            }
        });

        BarData data = new BarData(dataSet);
        data.setBarWidth(0.6f);
        data.setValueTextSize(25);
        data.setValueTextColor(Color.RED);

        YAxis yAxis = barChart.getAxisLeft();
        yAxis.setTextSize(25f);
        yAxis.setStartAtZero(true);
        yAxis.setEnabled(false);
        yAxis.setLabelCount(10);
        yAxis.setTextSize(10f);
        yAxis.setAxisMaximum(100);
        YAxis yAxis1 = barChart.getAxisRight();
        yAxis1.setStartAtZero(true);
        yAxis1.setValueFormatter(new PercentFormatter());
        yAxis1.setLabelCount(10);
        yAxis1.setAxisMaximum(100);
        yAxis1.setTextSize(10f);

        for (int i=0;i<ma.size();i++){
            strings.add(ma.get(i).getKey());
        }

        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(strings));
        xAxis.setTextSize(20f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setLabelCount(10);

        barChart.setData(data);
        barChart.setTouchEnabled(false);
        barChart.getLegend().setEnabled(false);
        barChart.invalidate();
        barChart.getDescription().setEnabled(false);


    }

    private void initView(View view) {
        barChart = view.findViewById(R.id.barchart);
    }
}
