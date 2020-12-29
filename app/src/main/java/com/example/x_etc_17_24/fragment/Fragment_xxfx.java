package com.example.x_etc_17_24.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.x_etc_17_24.R;
import com.example.x_etc_17_24.bean.WDXX;
import com.example.x_etc_17_24.bean.Wendu;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/2 20:49
 */
public class Fragment_xxfx extends Fragment {
    private List<Wendu> wendus;
    private PieChart piechart;
    private List<WDXX> wdxxes;
    private TextView txtpm;
    private TextView txtGuangzhao;
    private TextView txtWendu;
    private TextView txtShidu;
    private TextView txtCo2;

    public Fragment_xxfx(List<Wendu> wendus) {
        this.wendus = wendus;
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            wdxxes = LitePal.findAll(WDXX.class);
            getData();
            return false;
        }
    });

    private List<Integer> colors;
    private List<String> strings;
    private List<PieEntry> pieEntries;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_xxfx, null);
        initView(view);


        new Thread(new Runnable() {
            @Override
            public void run() {
                do {
                    handler.sendEmptyMessage(0);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } while (true);
            }
        }).start();

        return view;
    }


    private void getData() {
        if (pieEntries == null) {
            pieEntries = new ArrayList<>();
            strings = new ArrayList<>();
            colors = new ArrayList<>();
        }
        pieEntries.clear();
        Log.i("vvvvvvvvvsize", "onCreateView: " + wdxxes.size());
        int size = 1, wendu = 1, guang = 1, pm = 1, co2 = 1, shidu = 1;
        for (int i = 0; i < wdxxes.size(); i++) {
            WDXX wdxx = wdxxes.get(i);
            size = wdxxes.size();
            switch (wdxx.getLeixing()) {
                case "【温度】报警":
                    wendu++;
                    break;
                case "【湿度】报警":
                    shidu++;
                    break;
                case "【光照】报警":
                    guang++;
                    break;
                case "【CO2】报警":
                    co2++;
                    break;
                case "【PM2.5】报警":
                    pm++;
                    break;
            }
        }
        txtpm.setText("PM2.5 "+pm);
        txtGuangzhao.setText("光照  "+guang);
        txtWendu.setText("温度  "+wendu);
        txtShidu.setText("湿度  "+shidu);
        txtCo2.setText("CO2  "+co2);

        Log.i("vvvvvvvvvvvvv", "getData: " + wdxxes.size());

        int wendu1 = wendu * 100 / size;
        int shidu1 = shidu * 100 / size;
        int guang1 = guang * 100 / size;
        int pm1 = pm + 100 / size;
        int co21 = co2 + 100 / size;

        Log.i("vvvvvvvvvvvsssssssvv", "getData: wendu:" + wendu1);
        Log.i("vvvvvvvvvvvsssssssvv", "getData: shidu:" + shidu1);
        Log.i("vvvvvvvvvvvsssssssvv", "getData: guang:" + guang1);
        Log.i("vvvvvvvvvvvsssssssvv", "getData: pm:" + pm1);
        Log.i("vvvvvvvvvvvsssssssvv", "getData: co2:" + co21);

        pieEntries.add(new PieEntry(wendu1, ""));
        pieEntries.add(new PieEntry(shidu1, ""));
        pieEntries.add(new PieEntry(guang1, ""));
        pieEntries.add(new PieEntry(pm1, ""));
        pieEntries.add(new PieEntry(co21, ""));
        colors.add(Color.parseColor("#78EA5A"));
        colors.add(Color.parseColor("#EE80B6"));
        colors.add(Color.parseColor("#E2DA9E"));
        colors.add(Color.parseColor("#C7DF8F"));
        colors.add(Color.parseColor("#FD23B3"));
        Log.i("cccccccc", "getData: " + pieEntries.size());
        PieDataSet dataSet = new PieDataSet(pieEntries, "");
        dataSet.setColors(colors);
        dataSet.setSliceSpace(3f);
        dataSet.setValueTextSize(0);
        PieData data = new PieData(dataSet);
        Legend legend = piechart.getLegend();
        legend.setEnabled(false);
        piechart.setData(data);
        piechart.getDescription().setEnabled(false);
        piechart.setDrawHoleEnabled(false);
        piechart.setEntryLabelTextSize(25);
        piechart.setEntryLabelColor(Color.BLACK);
        piechart.setUsePercentValues(true);
        piechart.setTouchEnabled(false);
        piechart.invalidate();

    }

    private void initView(View view) {
        piechart = view.findViewById(R.id.piechart);
        txtpm = view.findViewById(R.id.txtpm);
        txtGuangzhao = view.findViewById(R.id.txt_guangzhao);
        txtWendu = view.findViewById(R.id.txt_wendu);
        txtShidu = view.findViewById(R.id.txt_shidu);
        txtCo2 = view.findViewById(R.id.txt_co2);
    }
}
