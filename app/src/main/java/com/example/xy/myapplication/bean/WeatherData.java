package com.example.xy.myapplication.bean;

import android.widget.TextView;

/**
 * Created by xy on 2016/11/14.
 */
public class WeatherData {
    /** 我们需要的属性
     * "date":"周一 11月14日 (实时：19℃)",
     "weather":"多云",
     "wind":"北风微风",
    "temperature":"19 ~ 10℃"
     */
    private String currentCity;
    private String pm25;
    private String date;
    private String weather;
    private String wind;
    private String temperature;

    public WeatherData(String currentCity, String pm25, String date, String weather, String wind, String temperature) {
        this.currentCity = currentCity;
        this.pm25 = pm25;
        this.date = date;
        this.weather = weather;
        this.wind = wind;
        this.temperature = temperature;
    }

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}
