package com.example.x_etc_17_24.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.x_etc_17_24.R;
import com.example.x_etc_17_24.net.OkHttpLo;
import com.example.x_etc_17_24.net.OkHttpTo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/3 16:49
 */
public class Fragment_zhsz extends Fragment {
    private TextView txtYuzhi;
    private EditText editYuzhi;
    private Button btnShezhi;
    private TextView title;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_zhsz, null);
        initView(view);
        title.setText("账户设置");


        getOkHttp();

        editYuzhi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("0")){
                    editYuzhi.setText("");
                    Toast.makeText(getContext(),"不能输入0",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnShezhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = editYuzhi.getText().toString();
                if (s.equals("")){
                    Toast.makeText(getContext(),"金额不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    setOkHttp(s);
                }

            }
        });


        return view;
    }

    private void setOkHttp(String s) {
        //set_car_threshold	设置小车账户阈值	{"UserName":"user1","threshold":"200"}
        new OkHttpTo()
                .setUrl("set_car_threshold")
                .setJsonObject("UserName","user1")
                .setJsonObject("threshold",s)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Toast.makeText(getContext(),"设置成功",Toast.LENGTH_SHORT).show();
                        editYuzhi.setText("");
                        getOkHttp();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();


    }

    private void getOkHttp() {
        new OkHttpTo()
                .setUrl("get_car_threshold")
                .setJsonObject("UserName","user1")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        JSONObject jsonObject1 = jsonArray.optJSONObject(0);
                        txtYuzhi.setText("我的1-4号车账户余额告警阈值为 "+jsonObject1.optString("threshold")+" 元");
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void initView(View view) {
        title = getActivity().findViewById(R.id.titlt);
        txtYuzhi = view.findViewById(R.id.txt_yuzhi);
        editYuzhi = view.findViewById(R.id.edit_yuzhi);
        btnShezhi = view.findViewById(R.id.btn_shezhi);
    }
}
