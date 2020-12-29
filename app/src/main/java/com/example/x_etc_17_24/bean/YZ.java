package com.example.x_etc_17_24.bean;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/2 20:08
 */
public class YZ {

    /**
     *     "ROWS_DETAIL": [
     *         {
     *             "temperature": 20,
     *             "humidity": 10,
     *             "illumination": 1000,
     *             "co2": 50,
     *             "pm25": 50,
     *             "path": 3
     *         }
     */

    private int temperature,humidity,illumination,co2,pm25;

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getIllumination() {
        return illumination;
    }

    public void setIllumination(int illumination) {
        this.illumination = illumination;
    }

    public int getCo2() {
        return co2;
    }

    public void setCo2(int co2) {
        this.co2 = co2;
    }

    public int getPm25() {
        return pm25;
    }

    public void setPm25(int pm25) {
        this.pm25 = pm25;
    }
}
