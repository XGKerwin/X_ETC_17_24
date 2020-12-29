package com.example.x_etc_17_24.bean;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/2 20:09
 */
public class Wendu {

    /**
     *     "temperature": 12,
     *     "humidity": 23,
     *     "illumination": 3801,
     *     "co2": 1649,
     *     "pm25": 53,
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
