package com.example.x_etc_17_24.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.x_etc_17_24.R;
import com.example.x_etc_17_24.bean.WDXX;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/2 20:05
 */
public class X_List_adapter extends BaseAdapter {
    private List<WDXX> wdxxList;

    public X_List_adapter(List<WDXX> wdxxList) {
        this.wdxxList = wdxxList;
    }

    @Override
    public int getCount() {
        if (wdxxList.size() == 0) return 0;
        return wdxxList.size();
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
        ViewHolder hOlder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_xxcx, null);
            hOlder = new ViewHolder(convertView);
            convertView.setTag(hOlder);
        } else {
            hOlder = (ViewHolder) convertView.getTag();
        }
        WDXX wdxx =wdxxList.get(position);

        hOlder.number.setText(position+1+"");
        hOlder.leixing.setText(wdxx.getLeixing());
        hOlder.yuzhi.setText(wdxx.getYuzhi()+"");
        hOlder.danqian.setText(wdxx.getDangqian()+"");

        return convertView;
    }

    class ViewHolder {
        private TextView number;
        private TextView leixing;
        private TextView yuzhi;
        private TextView danqian;

        public ViewHolder(View view) {
            number = view.findViewById(R.id.number);
            leixing = view.findViewById(R.id.leixing);
            yuzhi = view.findViewById(R.id.yuzhi);
            danqian = view.findViewById(R.id.danqian);
        }
    }
}
