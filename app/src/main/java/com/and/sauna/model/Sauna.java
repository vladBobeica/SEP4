package com.and.sauna.model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Sauna {
    public ArrayList<Sauna> saunas;
    public String saunaName;
    public long time;
    public float temperature;
    public float co2;
    public float humidity;

    public ArrayList<Sauna> getSaunas() {
        return saunas;
    }

    public void setSaunas(ArrayList<Sauna> saunas) {
        this.saunas = saunas;
    }

    public String getSaunaName() {
        return saunaName;
    }

    public void setSaunaName(String saunaName) {
        this.saunaName = saunaName;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getCo2() {
        return co2;
    }

    public void setCo2(float co2) {
        this.co2 = co2;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }
}
