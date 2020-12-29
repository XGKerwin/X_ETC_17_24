package com.example.x_etc_17_24.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.x_etc_17_24.R;
import com.example.x_etc_17_24.bean.CL;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/3 10:21
 */
public class X_List_adapter20 extends BaseAdapter {
    private List<CL> clList;

    public X_List_adapter20(List<CL> clList) {
        this.clList = clList;
    }

    @Override
    public int getCount() {
        if (clList.size() == 0) return 0;
        return clList.size();
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_rezx, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        CL cl = clList.get(position);
        holder.txtCph.setText(cl.getCph());
        holder.txtYue.setText("余额："+cl.getYue());
        switch (cl.getCph()){
            case "鲁A10001":
                holder.img.setImageResource(R.drawable.benci);
                break;
            case "鲁A10002":
                holder.img.setImageResource(R.drawable.bmw);
                break;
            case "鲁A10003":
                holder.img.setImageResource(R.drawable.zhonghua);
                break;
            case "鲁A10004":
                holder.img.setImageResource(R.drawable.audi);
                break;
        }

        return convertView;
    }

    class ViewHolder {
        private ImageView img;
        private TextView txtCph;
        private TextView txtYue;

        public ViewHolder(View view) {
            img = view.findViewById(R.id.img);
            txtCph = view.findViewById(R.id.txt_cph);
            txtYue = view.findViewById(R.id.txt_yue);
        }
    }
}
