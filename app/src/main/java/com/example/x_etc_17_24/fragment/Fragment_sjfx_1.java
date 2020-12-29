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
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/3 8:30
 */
public class Fragment_sjfx_1 extends Fragment {

    private int weizhang, meiweizhang;
    private PieChart piechart;

    public Fragment_sjfx_1(int yesweizhang, int noweizhang) {
        this.weizhang = yesweizhang;
        this.meiweizhang = noweizhang;
    }

    private List<Integer> colors;
    private List<PieEntry> pieEntries;
    private List<String> strings;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_sjfx_1, null);
        initView(view);

        getData();

        return view;
    }

    private void getData() {
        if (pieEntries == null){
            pieEntries = new ArrayList<>();
            strings = new ArrayList<>();
            colors = new ArrayList<>();
        }else {
            pieEntries.clear();
            strings.clear();
            colors.clear();
        }
        pieEntries.add(new PieEntry(weizhang,"有违章"));
        pieEntries.add(new PieEntry(meiweizhang,"无违章"));
        Log.i("ccccccccccc", "getData:weizhang "+weizhang);
        Log.i("ccccccccccc", "getData:meiweizhang "+meiweizhang);
        PieDataSet dataSet = new PieDataSet(pieEntries,"");
        colors.add(Color.parseColor("#4573A6"));
        colors.add(Color.parseColor("#AA4644"));
        dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setValueLinePart1Length(1f);
        dataSet.setValueLinePart2Length(0.1f);
        dataSet.setValueLinePart1OffsetPercentage(100);
        dataSet.setValueLineColor(Color.BLACK);
        dataSet.setColors(colors);
        dataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                DecimalFormat format = new DecimalFormat("0.0");
                return format.format(value)+"%";
            }
        });
        dataSet.setSliceSpace(3);
        dataSet.setValueTextSize(25);
        PieData data = new PieData(dataSet);
        Legend legend = piechart.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setTextSize(25);
        legend.setFormSize(25);
        piechart.setData(data);
        piechart.getDescription().setEnabled(false);
        piechart.setDrawHoleEnabled(false);
        piechart.setEntryLabelTextSize(25);
        piechart.setEntryLabelColor(Color.BLACK);
        piechart.setUsePercentValues(true);
        piechart.setTouchEnabled(false);


    }

    private void initView(View view) {
        piechart = view.findViewById(R.id.piechart);
    }
}
