package com.example.x_etc_17_24.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.x_etc_17_24.R;
import com.example.x_etc_17_24.adapter.X_List_adapter20;
import com.example.x_etc_17_24.bean.CL;
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
 * date   : 2020/12/3 9:55
 */
public class Fragment_grzx extends Fragment {

    private TextView txtUser1;
    private TextView txtName;
    private TextView txtXingbie;
    private TextView txtTel;
    private TextView txtShenfenzheng;
    private TextView txtTime;
    private ListView listview;
    private X_List_adapter20 adapter;
    private List<String> strings;
    private List<CL> clList;
    private TextView title;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_grzx, null);
        initView(view);
        title.setText("个人中心");


        strings = new ArrayList<>();

        getOkHttp();


        return view;
    }

    private void getOkHttp() {
        new OkHttpTo()
                .setUrl("get_root")
                .setJsonObject("UserName","user1")
                .setJsonObject("Password","1234")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        txtUser1.setText("用户名："+jsonObject.optString("username"));
                        txtName.setText("姓名："+jsonObject.optString("name"));
                        txtXingbie.setText("性别："+jsonObject.optString("sex"));
                        txtTel.setText("手机："+jsonObject.optString("tel"));
                        txtShenfenzheng.setText("身份证号："+jsonObject.optString("idnumber"));
                        txtTime.setText("注册时间："+jsonObject.optString("registered_time"));

                        strings = new Gson().fromJson(jsonObject.optJSONArray("plate").toString(),
                                new TypeToken<List<String>>(){}.getType());

                        getyue();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getyue() {
        for (int i=0;i<strings.size();i++){
            String s = strings.get(i);
            getokhttpyue(s);
        }

    }

    private void getokhttpyue(final String s) {
        if (clList == null){
            clList = new ArrayList<>();
        }else {
            clList.clear();
        }
        //{"UserName":"user1","plate":"鲁A10001"}
        new OkHttpTo()
                .setUrl("get_balance_c")
                .setJsonObject("UserName","user1")
                .setJsonObject("plate",s)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        CL cl = new CL(s,jsonObject.optString("balance"));
                        clList.add(cl);
                        if (clList.size()==strings.size()){
                            showlist();
                        }
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();


    }

    private void showlist() {
        adapter = new X_List_adapter20(clList);
        listview.setAdapter(adapter);
    }

    private void initView(View view) {
        title = getActivity().findViewById(R.id.titlt);
        txtUser1 = view.findViewById(R.id.txt_user1);
        txtName = view.findViewById(R.id.txt_name);
        txtXingbie = view.findViewById(R.id.txt_xingbie);
        txtTel = view.findViewById(R.id.txt_tel);
        txtShenfenzheng = view.findViewById(R.id.txt_shenfenzheng);
        txtTime = view.findViewById(R.id.txt_time);
        listview = view.findViewById(R.id.listview);
    }
}
