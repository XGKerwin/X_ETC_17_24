package com.example.x_etc_17_24.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.x_etc_17_24.R;
import com.example.x_etc_17_24.bean.SJFX;
import com.example.x_etc_17_24.net.OkHttpLo;
import com.example.x_etc_17_24.net.OkHttpTo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/3 8:23
 */
public class Fragment_sjfx extends Fragment {

    private ViewPager ViewPager;
    private List<Fragment> fragments;
    private LinearLayout Linear;
    private TextView title;
    private List<SJFX> sjfxList,sjfxyes,sjfxno;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_sjfx, null);
        initView(view);
        title.setText("数据分析");
        fragments = new ArrayList<>();


        getyouweizhang();


        return view;
    }

    private void getyouweizhang() {
        if (sjfxList == null){
            sjfxList = new ArrayList<>();
            sjfxyes = new ArrayList<>();
            sjfxno = new ArrayList<>();
        }else {
            sjfxno.clear();
            sjfxyes.clear();
            sjfxList.clear();
        }
        new OkHttpTo()
                .setUrl("get_peccancy")
                .setJsonObject("UserName","user1")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        sjfxList.addAll((Collection<? extends SJFX>) new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<SJFX>>(){}.getType()));
                        getdata();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private Map<String,String> mapyes;
    private Map<String,String> mapno;

    private void getdata() {
        Fragment1data();
        Fragment2data();
        getfragment();
    }

    private Map<String,Integer> map2;
    private List<Map.Entry<String,Integer>> maps = new ArrayList<>();
    private void Fragment2data() {
        map2 = new HashMap<>();
        for (int i=0;i<sjfxyes.size();i++){
            SJFX sjfx = sjfxyes.get(i);
            if (map2.get(sjfx.getPaddr())==null){
                map2.put(sjfx.getPaddr(),1);
            }else {
                map2.put(sjfx.getPaddr(),map2.get(sjfx.getPaddr())+1);
            }
        }
        maps.addAll(map2.entrySet());
        Collections.sort(maps, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        });
    }

    private int yesweizhang,noweizhang;
    private void Fragment1data() {
        mapyes = new HashMap<>();
        mapno = new HashMap<>();
        for (int i=0;i<sjfxList.size();i++){
            SJFX sjfx = sjfxList.get(i);
            if (sjfx.getPaddr().equals("")){
                sjfxno.add(sjfx);
            }else {
                sjfxyes.add(sjfx);
            }
        }
        for (int i=0;i<sjfxyes.size();i++){
            SJFX sjfx = sjfxyes.get(i);
            mapyes.put(sjfx.getCarnumber(),"1");
        }
        for (int i=0;i<sjfxno.size();i++){
            SJFX sjfx = sjfxno.get(i);
            mapno.put(sjfx.getCarnumber(),"2");
        }
        yesweizhang = mapyes.size();
        noweizhang = mapno.size();

    }

    private void getfragment() {
        fragments.add(new Fragment_sjfx_1(yesweizhang,noweizhang));
        fragments.add(new Fragment_sjfx_2(maps,sjfxyes.size()));

        ViewPager.setAdapter(new Pageradapter(getActivity().getSupportFragmentManager()));
        ViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int j = 0; j < Linear.getChildCount(); j++) {
                    TextView textView = (TextView) Linear.getChildAt(j);
                    if (position == j) {
                        textView.setBackgroundResource(R.drawable.bg_xian);            //线条滑动的位置
                    } else {
                        textView.setBackgroundResource(R.drawable.bg_wu);            //其他的
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        for (int i = 0; i < fragments.size(); i++) {
            TextView textView = new TextView(getContext());
            textView.setTextSize(25f);
            textView.setTextColor(Color.BLACK);
            textView.setWidth(70);
            if (i == 0) {
                textView.setBackgroundResource(R.drawable.bg_xian);        //一开始边框位置
            }else {
                textView.setBackgroundResource(R.drawable.bg_wu);
            }
            Linear.addView(textView);                                //将设置号的textview传给linearlayout
        }

    }

    private void initView(View view) {
        ViewPager = view.findViewById(R.id.ViewPager);
        Linear = view.findViewById(R.id.Linear);
        title = getActivity().findViewById(R.id.titlt);
    }


    class Pageradapter extends FragmentPagerAdapter {

        public Pageradapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
