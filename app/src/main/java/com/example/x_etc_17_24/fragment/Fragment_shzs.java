package com.example.x_etc_17_24.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_etc_17_24.R;
import com.example.x_etc_17_24.adapter.X_SHZS_adapter;
import com.example.x_etc_17_24.bean.SHZS;
import com.example.x_etc_17_24.net.OkHttpLo;
import com.example.x_etc_17_24.net.OkHttpTo;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Fragment_shzs extends Fragment {

    private GridView gridview;
    private List<SHZS> shzsList;
    private X_SHZS_adapter adapter;
    private FragmentTransaction fragmentTransaction;
    private TextView title;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_shzs, null);
        initView(view);
        title.setText("生活指数");
        shzsList = new ArrayList<>();

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fragment1(new Fragment_wdxx(shzsList));
            }
        });

        getOkHttp();


        return view;
    }

    private void Fragment1(Fragment fragment) {
        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment,fragment).commit();
    }

    private void getOkHttp() {
        new OkHttpTo()
                .setUrl("get_all_sense")
                .setJsonObject("UserName","user1")
                .setIsLoop(true)
                .setTime(3000)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        shzsList.add(new Gson().fromJson(jsonObject.toString(),SHZS.class));
                        if (shzsList.size()==21){
                            shzsList.remove(0);
                        }
                        showList();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void showList() {

        if (adapter == null){
            adapter = new X_SHZS_adapter(shzsList);
            gridview.setAdapter(adapter);
        }else {
            adapter.notifyDataSetChanged();
        }

    }

    private void initView(View view) {
        title = getActivity().findViewById(R.id.titlt);
        gridview = view.findViewById(R.id.gridview);
    }
}
