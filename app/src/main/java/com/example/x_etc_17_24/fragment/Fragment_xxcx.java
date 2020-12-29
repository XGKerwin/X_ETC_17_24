package com.example.x_etc_17_24.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.x_etc_17_24.R;
import com.example.x_etc_17_24.adapter.X_List_adapter;
import com.example.x_etc_17_24.adapter.X_spinner_adapter;
import com.example.x_etc_17_24.bean.WDXX;
import com.example.x_etc_17_24.bean.Wendu;
import com.example.x_etc_17_24.bean.YZ;
import com.example.x_etc_17_24.net.OkHttpLo;
import com.example.x_etc_17_24.net.OkHttpTo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/2 19:52
 */
public class Fragment_xxcx extends Fragment {

    private Spinner spinner;
    private ListView listview;
    private X_spinner_adapter Sadapter;
    private X_List_adapter Ladapter;
    private String[] arr = {"全部","温度","湿度","光照","PM2.5","CO2"};
    private List<YZ> yzList;
    private List<WDXX> wdxxList;
    private List<Wendu> wendus;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_wdxx, null);
        initView(view);
        yzList = new ArrayList<>();
        wdxxList = new ArrayList<>();

        spin();

        getYuzhi();


        return view;
    }

    private void spin() {
        Sadapter = new X_spinner_adapter(arr);
        spinner.setAdapter(Sadapter);
    }

    private void getYuzhi() {
        new OkHttpTo()
                .setUrl("get_threshold")
                .setJsonObject("UserName","user1")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        yzList.addAll((List<YZ>)new Gson().fromJson  (jsonObject.optJSONArray("ROWS_DETAIL").toString()
                                ,new TypeToken<List<YZ>>(){}.getType()));
                        setleixing();
                        getchuangan();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getchuangan() {
        if (wendus == null){
            wendus = new ArrayList<>();
        }
        new OkHttpTo()
                .setUrl("get_all_sense")
                .setJsonObject("UserName","user1")
                .setTime(3000)
                .setIsLoop(true)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        wendus.clear();
                        wendus.add(new Gson().fromJson(jsonObject.toString(),Wendu.class));
                        show();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void show() {
        Wendu wen = wendus.get(0);
        if (wen.getTemperature()>wendu){
            WDXX wdxx = new WDXX("【温度】报警",wendu,wen.getTemperature());
            wdxx.save();
            wdxxList.add(wdxx);
        }
        if (wen.getHumidity()>shidu){
            WDXX wdxx = new WDXX("【湿度】报警",shidu,wen.getHumidity());
            wdxx.save();
            wdxxList.add(wdxx);
        }
        if (wen.getIllumination()>guangzhao){
            WDXX wdxx = new WDXX("【光照】报警",guangzhao,wen.getIllumination());
            wdxx.save();
            wdxxList.add(wdxx);
        }
        if (wen.getCo2()>co2){
           WDXX wdxx = new WDXX("【CO2】报警",co2,wen.getCo2());
           wdxx.save();
           wdxxList.add(wdxx);
        }
        if (wen.getPm25()>pm25){
            WDXX wdxx = new WDXX("【PM2.5】报警",pm25,wen.getPm25());
            wdxx.save();
            wdxxList.add(wdxx);
        }
        showList();

    }

    private void showList() {
        if (Ladapter == null){
            Ladapter = new X_List_adapter(wdxxList);
            listview.setAdapter(Ladapter);
        }else {
            Ladapter.notifyDataSetChanged();
        }

    }

    private int wendu,shidu,guangzhao,co2,pm25;
    private void setleixing() {
        YZ yz = yzList.get(0);
        wendu = yz.getTemperature();
        shidu = yz.getHumidity();
        guangzhao = yz.getIllumination();
        co2 = yz.getCo2();
        pm25 = yz.getPm25();
    }


    private void initView(View view) {
        spinner = view.findViewById(R.id.spinner);
        listview = view.findViewById(R.id.listview);
    }
}
