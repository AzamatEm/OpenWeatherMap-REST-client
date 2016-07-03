package com.iamoem.owmclient.presenter.viewobjects;

import com.iamoem.owmclient.model.modelobjects.DailyWeather;
import com.iamoem.owmclient.model.modelobjects.Temp;

/**
 * Created by AzamatMurzagalin on 03.07.2016.
 */
public class WeatherView {


    private Integer dt;

    private Double dayTemp;

    private Double nightTemp;

    private Double eveTemp;

    private Double mornTemp;

    private Double pressure;

    private Integer humidity;

    private String description;

    private Double speed;

    private Integer deg;

    private Integer clouds;

    private Double rain;


    public WeatherView(DailyWeather dailyWeather) {
        dt = dailyWeather.getDt();
        if (dailyWeather.getTemp() != null) {
            Temp temp = dailyWeather.getTemp();
            dayTemp = temp.getDay();
            mornTemp = temp.getMorn();
            nightTemp = temp.getNight();
            eveTemp = temp.getEve();
        }
        pressure = dailyWeather.getPressure();
        humidity = dailyWeather.getHumidity();
        speed = dailyWeather.getSpeed();
        deg = dailyWeather.getDeg();
        clouds = dailyWeather.getClouds();
        rain = dailyWeather.getRain();

        if(dailyWeather.getWeather() != null &&
                dailyWeather.getWeather().size() > 0) {
            description = dailyWeather.getWeather().get(0).getDescription();
        }
    }

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public Double getDayTemp() {
        return dayTemp;
    }

    public void setDayTemp(Double dayTemp) {
        this.dayTemp = dayTemp;
    }

    public Double getNightTemp() {
        return nightTemp;
    }

    public void setNightTemp(Double nightTemp) {
        this.nightTemp = nightTemp;
    }

    public Double getEveTemp() {
        return eveTemp;
    }

    public void setEveTemp(Double eveTemp) {
        this.eveTemp = eveTemp;
    }

    public Double getMornTemp() {
        return mornTemp;
    }

    public void setMornTemp(Double mornTemp) {
        this.mornTemp = mornTemp;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Integer getDeg() {
        return deg;
    }

    public void setDeg(Integer deg) {
        this.deg = deg;
    }

    public Integer getClouds() {
        return clouds;
    }

    public void setClouds(Integer clouds) {
        this.clouds = clouds;
    }

    public Double getRain() {
        return rain;
    }

    public void setRain(Double rain) {
        this.rain = rain;
    }
}
