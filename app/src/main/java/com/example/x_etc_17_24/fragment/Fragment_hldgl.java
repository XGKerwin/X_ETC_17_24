package com.example.x_etc_17_24.fragment;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.x_etc_17_24.bean.HLD;
import com.example.x_etc_17_24.R;
import com.example.x_etc_17_24.adapter.X_List_21_adapter;
import com.example.x_etc_17_24.adapter.X_spinner_adapter;
import com.example.x_etc_17_24.net.OkHttpLo;
import com.example.x_etc_17_24.net.OkHttpTo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/3 10:48
 */
public class Fragment_hldgl extends Fragment {

    private Spinner spinner;
    private ListView listview;
    private X_spinner_adapter adapter;
    private String[] arr = {"路口升序", "路口降序", "红灯升序", "红灯降序", "黄灯升序", "黄灯降序", "绿灯升序", "绿灯降序",};
    private int pos = 0;
    private List<HLD> hldList;
    private X_List_21_adapter Ladapter;
    private ImageView img;
    private TextView title;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_hldgl, null);
        initView(view);
        title.setText("红绿灯管理");

        AnimationDrawable animationDrawable = (AnimationDrawable) img.getDrawable();
        animationDrawable.start();

        spin();

        getFor();

        return view;
    }

    private void getFor() {
        if (hldList == null) {
            hldList = new ArrayList<>();
        } else {
            hldList.clear();
        }
        for (int i = 1; i <= 5; i++) {
            getOkhttp(i);
        }
    }

    private void getOkhttp(int i) {
        // {"UserName":"user1","number":"1"}
        new OkHttpTo()
                .setUrl("get_traffic_light")
                .setJsonObject("UserName", "user1")
                .setJsonObject("number", i)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        hldList.addAll((Collection<? extends HLD>) new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<HLD>>() {
                                }.getType()));
                        if (hldList.size() == 5) {
                            showpaixu();
                        }
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void showpaixu() {
        Collections.sort(hldList, new Comparator<HLD>() {
            @Override
            public int compare(HLD o1, HLD o2) {
                switch (pos) {
                    case 0:
                        return o1.getNumber() - o2.getNumber();
                    case 1:
                        return o2.getNumber() - o1.getNumber();
                    case 2:
                        return o1.getRed() - o2.getRed();
                    case 3:
                        return o2.getRed() - o1.getRed();
                    case 4:
                        return o1.getYellow() - o2.getYellow();
                    case 5:
                        return o2.getYellow() - o1.getYellow();
                    case 6:
                        return o1.getGreen() - o2.getGreen();
                    case 7:
                        return o2.getGreen() - o1.getGreen();
                }
                return 0;
            }
        });
        showlist();
    }

    private void showlist() {
        Ladapter = new X_List_21_adapter(hldList);
        listview.setAdapter(Ladapter);
    }

    private void spin() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
                showpaixu();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        adapter = new X_spinner_adapter(arr);
        spinner.setAdapter(adapter);
    }

    private void initView(View view) {
        title = getActivity().findViewById(R.id.titlt);
        spinner = view.findViewById(R.id.spinner);
        listview = view.findViewById(R.id.listview);
        img = view.findViewById(R.id.img);
    }
}
