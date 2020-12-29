package com.example.x_etc_17_24.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.x_etc_17_24.R;
import com.example.x_etc_17_24.bean.HLD;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/3 11:13
 */
public class X_List_21_adapter extends BaseAdapter {
    private List<HLD> hldList;

    public X_List_21_adapter(List<HLD> hldList) {
        this.hldList = hldList;
    }

    @Override
    public int getCount() {
        return hldList.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_hld, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        HLD hld = hldList.get(position);
        holder.number.setText(hld.getNumber()+"");
        holder.hong.setText(hld.getRed()+"");
        holder.huang.setText(hld.getYellow()+"");
        holder.lv.setText(hld.getGreen()+"");


        return convertView;
    }


    class ViewHolder {
        private TextView number;
        private TextView hong;
        private TextView huang;
        private TextView lv;

        public ViewHolder(View view) {
            number = view.findViewById(R.id.number);
            hong = view.findViewById(R.id.hong);
            huang = view.findViewById(R.id.huang);
            lv = view.findViewById(R.id.lv);
        }
    }
}
