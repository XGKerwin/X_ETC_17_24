package com.example.x_etc_17_24.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_etc_17_24.R;
import com.example.x_etc_17_24.bean.SHZS;
import com.example.x_etc_17_24.bean.Wendu;
import com.example.x_etc_17_24.net.OkHttpLo;
import com.example.x_etc_17_24.net.OkHttpTo;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/2 19:39
 */
public class Fragment_wdxx extends Fragment {

    private List<SHZS> shzsList;
    private TextView txtChaxun;
    private TextView txtFenxi;

    public Fragment_wdxx(List<SHZS> shzsList) {
        this.shzsList = shzsList;
    }


    private FragmentTransaction fragmentTransaction;
    private List<Wendu> wendus;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_xxcx, null);
        initView(view);
        wendus = new ArrayList<>();

        btn();

        Fragment1(new Fragment_xxcx());

        getOkHttp();


        return view;
    }

    private void getOkHttp() {
        new OkHttpTo()
                .setUrl("get_all_sense")
                .setJsonObject("UserName","user1")
                .setTime(3000)
                .setIsLoop(true)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        wendus.add(new Gson().fromJson(jsonObject.toString(),Wendu.class));
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void btn() {

        txtChaxun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtChaxun.setBackgroundResource(R.drawable.bg_hui);
                txtFenxi.setBackgroundResource(R.drawable.bg_xian1);
                Fragment1(new Fragment_xxcx());
            }
        });

        txtFenxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtFenxi.setBackgroundResource(R.drawable.bg_hui);
                txtChaxun.setBackgroundResource(R.drawable.bg_xian1);
                Fragment1(new Fragment_xxfx(wendus));
            }
        });

    }

    private void Fragment1(Fragment fragment) {
        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment18, fragment).commit();
    }

    private void initView(View view) {
        txtChaxun = view.findViewById(R.id.txt_chaxun);
        txtFenxi = view.findViewById(R.id.txt_fenxi);
    }
}
