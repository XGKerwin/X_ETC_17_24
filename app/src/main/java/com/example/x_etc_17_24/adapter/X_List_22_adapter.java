package com.example.x_etc_17_24.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.x_etc_17_24.R;
import com.example.x_etc_17_24.bean.ZHGL;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/3 11:46
 */
public class X_List_22_adapter extends BaseAdapter {
    private List<ZHGL> zhglList;
    private Myzhgl myzhgl;

    public interface Myzhgl{

        void setzhgl(int position, boolean isChecked, int i);
    }

    public void setMyzhgl(Myzhgl myzhgl){
        this.myzhgl = myzhgl;
    }

    public X_List_22_adapter(List<ZHGL> zhglList) {
        this.zhglList = zhglList;
    }

    @Override
    public int getCount() {
        return zhglList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_zhgl, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ZHGL zhgl = zhglList.get(position);
        holder.number.setText(zhgl.getNumber()+"");
        holder.yue.setText(zhgl.getBalance()+"元");

        holder.chaechbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                myzhgl.setzhgl(position,isChecked,1);
            }
        });

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myzhgl.setzhgl(position,false,2);
            }
        });

        return convertView;
    }


    class ViewHolder {
        private TextView number;
        private TextView yue;
        private CheckBox chaechbox;
        private Button button;

        public ViewHolder(View view) {
            number = view.findViewById(R.id.number);
            yue = view.findViewById(R.id.yue);
            chaechbox = view.findViewById(R.id.chaechbox);
            button = view.findViewById(R.id.button);
        }
    }
}
