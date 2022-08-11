package com.and.sauna.model;

import com.google.gson.annotations.SerializedName;

import java.sql.Time;

public class MeasurementDTO {
    private Integer id;
    private String saunaName;
    private Integer humidity;
    private Integer temperature;
    private Integer co2;
    private String time;

    public MeasurementDTO(Integer id, String saunaName, Integer humidity, Integer temperature, Integer co2, String time) {
        this.id = id;
        this.saunaName = saunaName;
        this.humidity = humidity;
        this.temperature = temperature;
        this.co2 = co2;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSaunaName() {
        return saunaName;
    }

    public void setSaunaName(String saunaName) {
        this.saunaName = saunaName;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Integer getCo2() {
        return co2;
    }

    public void setCo2(Integer co2) {
        this.co2 = co2;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
