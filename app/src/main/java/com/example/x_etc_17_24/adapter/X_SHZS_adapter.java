package com.example.x_etc_17_24.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.x_etc_17_24.R;
import com.example.x_etc_17_24.bean.SHZS;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/2 19:00
 */
public class X_SHZS_adapter extends BaseAdapter {
    private List<SHZS> shzsList;

    public X_SHZS_adapter(List<SHZS> shzsList) {
        this.shzsList = shzsList;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("ResourceType")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_shzs, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        SHZS shzs = shzsList.get(shzsList.size() - 1);
        switch (position) {
            case 0:
                holder.img.setImageResource(R.drawable.zhiwaixianzhishu);
                if (shzs.getIllumination() < 1000) {
                    holder.lin.setBackgroundColor(Color.parseColor("#DAEDFF"));
                    holder.txtTit.setText("弱(" + shzs.getIllumination() + ")");
                    holder.txtMsg.setText("辐射较弱，涂擦SPF12~15、PA+护肤品");
                } else if (shzs.getIllumination() > 3000) {
                    holder.lin.setBackgroundColor(Color.parseColor("#FF1100"));
                    holder.txtTit.setText("强(" + shzs.getIllumination() + ")");
                    holder.txtMsg.setText("尽量减少外出，需要涂抹高倍数防晒霜");
                } else {
                    holder.lin.setBackgroundColor(Color.parseColor("#DAEDFF"));
                    holder.txtTit.setText("中等(" + shzs.getIllumination() + ")");
                    holder.txtMsg.setText("涂擦SPF大于15、PA+防晒护肤品");
                }
                break;
            case 1:
                holder.img.setImageResource(R.drawable.ganmaozhisu);
                if (shzs.getTemperature() < 8) {
                    holder.lin.setBackgroundColor(Color.parseColor("#DAEDFF"));
                    holder.txtTit.setText("较易发(" + shzs.getTemperature() + ")");
                    holder.txtMsg.setText("温度低，风较大，较易发生感冒，注意防护");
                }else {
                    holder.lin.setBackgroundColor(Color.parseColor("#DAEDFF"));
                    holder.txtTit.setText("少发(" + shzs.getTemperature() + ")");
                    holder.txtMsg.setText("无明显降温，感冒机率较低");
                }
            break;
            case 2:
                holder.img.setImageResource(R.drawable.chuanyizhisu);
                if (shzs.getTemperature() < 12) {
                    holder.lin.setBackgroundColor(Color.parseColor("#DAEDFF"));
                    holder.txtTit.setText("冷(" + shzs.getTemperature() + ")");
                    holder.txtMsg.setText("建议穿长袖衬衫、单裤等服装");
                } else if (shzs.getTemperature() > 21) {
                    holder.lin.setBackgroundColor(Color.parseColor("#FF1100"));
                    holder.txtTit.setText("热(" + shzs.getTemperature() + ")");
                    holder.txtMsg.setText("适合穿T恤、短薄外套等夏季服装");
                } else {
                    holder.lin.setBackgroundColor(Color.parseColor("#DAEDFF"));
                    holder.txtTit.setText("舒适(" + shzs.getTemperature() + ")");
                    holder.txtMsg.setText("建议穿短袖衬衫、单裤等服装");
                }
                break;
            case 3:
                holder.img.setImageResource(R.drawable.yundongzhisu);
                if (shzs.getCo2() < 3000) {
                    holder.lin.setBackgroundColor(Color.parseColor("#DAEDFF"));
                    holder.txtTit.setText("适宜(" + shzs.getCo2() + ")");
                    holder.txtMsg.setText("提示信息气候适宜，推荐您进行户外运动");
                } else if (shzs.getCo2() > 6000) {
                    holder.lin.setBackgroundColor(Color.parseColor("#FF1100"));
                    holder.txtTit.setText("较不宜(" + shzs.getCo2() + ")");
                    holder.txtMsg.setText("空气氧气含量低，请在室内进行休闲运动");
                } else {
                    holder.lin.setBackgroundColor(Color.parseColor("#DAEDFF"));
                    holder.txtTit.setText("中(" + shzs.getCo2() + ")");
                    holder.txtMsg.setText("易感人群应适当减少室外活动");
                }
                break;
            case 4:
                holder.img.setImageResource(R.drawable.kongqiwurankuoanzhisu);
                if (shzs.getPm25() < 30) {
                    holder.lin.setBackgroundColor(Color.parseColor("#DAEDFF"));
                    holder.txtTit.setText("优(" + shzs.getPm25() + ")");
                    holder.txtMsg.setText("空气质量非常好，非常适合户外活动，趁机出去多呼吸新鲜空气");
                } else if (shzs.getPm25() > 100) {
                    holder.lin.setBackgroundColor(Color.parseColor("#FF1100"));
                    holder.txtTit.setText("污染(" + shzs.getPm25() + ")");
                    holder.txtMsg.setText("空气质量差，不适合户外活动");
                } else {
                    holder.lin.setBackgroundColor(Color.parseColor("#DAEDFF"));
                    holder.txtTit.setText("良(" + shzs.getPm25() + ")");
                    holder.txtMsg.setText("易感人群应适当减少室外活动");
                }
                break;
        }


        return convertView;
    }


    class ViewHolder {
        private ImageView img;
        private TextView txtTit;
        private TextView txtMsg;
        private LinearLayout lin;

        public ViewHolder(View view) {
            lin = view.findViewById(R.id.lin);
            img = view.findViewById(R.id.img);
            txtTit = view.findViewById(R.id.txt_tit);
            txtMsg = view.findViewById(R.id.txt_msg);
        }
    }
}
