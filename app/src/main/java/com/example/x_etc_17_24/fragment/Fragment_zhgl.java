package com.example.x_etc_17_24.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.x_etc_17_24.R;
import com.example.x_etc_17_24.activity.Activity_zhgl;
import com.example.x_etc_17_24.adapter.X_List_22_adapter;
import com.example.x_etc_17_24.bean.ZHGL;
import com.example.x_etc_17_24.bean.CXJL;
import com.example.x_etc_17_24.net.OkHttpLo;
import com.example.x_etc_17_24.net.OkHttpTo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/3 11:29
 */
public class Fragment_zhgl extends Fragment {

    private TextView czjl,plcz;
    private ListView listview;
    private List<ZHGL> zhglList;
    private X_List_22_adapter adapter;
    private String string;
    private ProgressDialog progressDialog;
    private TextView title;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_zhgl, null);
        initView(view);
        title.setText("账户管理");
        plcz.setText("批量充值");
        czjl.setText("充值记录");

        getOkHttp();

        czjl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Activity_zhgl.class);
                startActivity(intent);
            }
        });

        plcz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 string = "";
                for (int i =0;i<zhglList.size();i++){
                    ZHGL zhgl = zhglList.get(i);
                    if (zhgl.isXz()){
                        if (string.equals("")){
                            string = zhgl.getNumber();
                        } else {
                            string += ","+zhgl.getNumber();
                        }
                    }
                }
                Log.i("vvvvvvvvvccccccc", "onClick: "+string);
                if (string.equals("")){
                    dialog.dismiss();
                    Toast.makeText(getContext(),"请选择车辆",Toast.LENGTH_SHORT).show();
                } else {
                    dia(string,"1");
                }
            }
        });


        return view;
    }

    private void getOkHttp() {
        if (zhglList == null){
            zhglList = new ArrayList<>();
        }else {
            zhglList.clear();
        }
        //{"UserName":"user1"}
        new OkHttpTo()
                .setUrl("get_vehicle")
                .setJsonObject("UserName","user1")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        zhglList.clear();
                        zhglList.addAll((Collection<? extends ZHGL>) new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<ZHGL>>(){}.getType()));
                        showList();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();


    }

    private int pos;
    private String bianhao;
    private String cph;
    private void showList() {

        adapter = new X_List_22_adapter(zhglList);
        listview.setAdapter(adapter);

        adapter.setMyzhgl(new X_List_22_adapter.Myzhgl() {
            @Override
            public void setzhgl(int position, boolean isChecked, int i) {
                if (i==1){
                    zhglList.get(position).setXz(isChecked);
                }else if (i==2){
                    cph = zhglList.get(position).getPlate();
                    bianhao = zhglList.get(position).getNumber();
                    pos = position;
                    dia(bianhao,"0");
                }
            }
        });

    }

    private AlertDialog dialog;
    private void dia(String bianhao, final String t) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dia_zhgl,null);
        builder.setView(view);
        builder.setCancelable(false);
        TextView number = view.findViewById(R.id.number);
        final EditText ed_jine = view.findViewById(R.id.edit_jine);
        Button btn_cx = view.findViewById(R.id.btn_cz);
        Button btn_qx = view.findViewById(R.id.btn_qx);

        number.setText(bianhao);
        ed_jine.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("0")){
                    ed_jine.setText("");
                    Toast.makeText(getContext(),"请输入1-999之间的数",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_cx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jin = ed_jine.getText().toString();
                if (jin.equals("")){
                    Toast.makeText(getContext(),"充值金额不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    progressDialog = new ProgressDialog(getContext());
                    progressDialog.setTitle("请稍等···");
                    progressDialog.setMessage("获取数据中");
                    progressDialog.setCancelable(false);
                    progressDialog.show();

                    if (t.equals("0")){
                        String bianhao = zhglList.get(pos).getNumber();
                        int yue = zhglList.get(pos).getBalance();
                        setOkHttp(cph,jin,bianhao,yue);
                    }else if (t.equals("1")){
                        for (int i=0;i<zhglList.size();i++){
                            ZHGL zhgl = zhglList.get(i);
                            if (zhgl.isXz()){
                                String bianhao = zhgl.getNumber();
                                String cp = zhgl.getPlate();
                                int yue = zhgl.getBalance();
                                setOkHttp(cp,jin,bianhao,yue);
                            }
                        }
                    }
                }
            }
        });

        dialog = builder.show();
        btn_qx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    private void setOkHttp(String cp, final String jin, final String bianhao, final int yue) {
        /**
         * {"UserName":"user1","plate":"鲁A10001","balance":"100"}
         */



        new OkHttpTo()
                .setUrl("set_balance")
                .setJsonObject("UserName","user1")
                .setJsonObject("plate",cp)
                .setJsonObject("balance",jin)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        progressDialog.dismiss();
                        int i = yue;
                        int q = Integer.parseInt(jin);
                        Log.i("xxxxxxxxs", "setOkHttp: "+i);
                        Log.i("xxxxxxxxs", "setOkHttp: "+q);
                        int j = i+q;
                        Log.i("xxxxxxxxs", "setOkHttp: "+j);

                        getTime();
                        CXJL cxjl = new CXJL();
                        cxjl.setCzr("管理员");
                        cxjl.setNumber(bianhao);
                        cxjl.setJine(jin);
                        cxjl.setHouyue(j+"");
                        cxjl.setTime(time1);

                        if (cxjl.save()){
                            Toast.makeText(getContext(),"充值成功",Toast.LENGTH_SHORT).show();
                            getOkHttp();
                        }else {
                            Toast.makeText(getContext(),"充值失败",Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    private String time1;
    private void getTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date(System.currentTimeMillis());
        time1 = format.format(date);
    }

    private void initView(View view) {
        title = getActivity().findViewById(R.id.titlt);
        czjl = getActivity().findViewById(R.id.czjl);
        plcz = getActivity().findViewById(R.id.plcz);
        listview = view.findViewById(R.id.listview);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        czjl.setText("");
        plcz.setText("");
    }
}
