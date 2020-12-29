package com.example.x_etc_17_24.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.x_etc_17_24.R;
import com.example.x_etc_17_24.bean.CXJL;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/3 16:22
 */
public class X_List_22_czjl_adapter extends BaseAdapter {
    private List<CXJL> cxjlList;

    public X_List_22_czjl_adapter(List<CXJL> cxjlList) {
        this.cxjlList = cxjlList;
    }

    @Override
    public int getCount() {
        if (cxjlList.size()==0) return 0;
        return cxjlList.size();
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_czjl, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        CXJL cxjl = cxjlList.get(position);
        holder.number.setText(cxjl.getNumber());
        holder.txtCzr.setText("充值人："+cxjl.getCzr());
        holder.txtHou.setText("余额："+cxjl.getHouyue());
        holder.txtJine.setText("充值金额："+cxjl.getJine());
        holder.time.setText("充值时间："+cxjl.getTime());

        return convertView;
    }

    class ViewHolder {
        private TextView number;
        private TextView txtCzr;
        private TextView txtJine;
        private TextView txtHou;
        private TextView time;

        public ViewHolder(View view) {
            number = view.findViewById(R.id.number);
            txtCzr = view.findViewById(R.id.txt_czr);
            txtJine = view.findViewById(R.id.txt_jine);
            txtHou = view.findViewById(R.id.txt_hou);
            time = view.findViewById(R.id.time);
        }
    }

}
