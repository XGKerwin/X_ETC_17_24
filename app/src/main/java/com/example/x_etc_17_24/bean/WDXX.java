package com.example.x_etc_17_24.bean;

import org.litepal.crud.LitePalSupport;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/2 19:42
 */
public class WDXX extends LitePalSupport {

    private String leixing;
    private int yuzhi;
    private int dangqian;

    public WDXX(String leixing, int yuzhi, int dangqian) {
        this.leixing = leixing;
        this.yuzhi = yuzhi;
        this.dangqian = dangqian;
    }

    public String getLeixing() {
        return leixing;
    }

    public void setLeixing(String leixing) {
        this.leixing = leixing;
    }

    public int getYuzhi() {
        return yuzhi;
    }

    public void setYuzhi(int yuzhi) {
        this.yuzhi = yuzhi;
    }

    public int getDangqian() {
        return dangqian;
    }

    public void setDangqian(int dangqian) {
        this.dangqian = dangqian;
    }
}
