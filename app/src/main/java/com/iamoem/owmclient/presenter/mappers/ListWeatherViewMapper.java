package com.iamoem.owmclient.presenter.mappers;


import com.iamoem.owmclient.model.modelobjects.DailyWeather;
import com.iamoem.owmclient.presenter.viewobjects.WeatherView;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by AzamatMurzagalin on 03.07.2016.
 */
public class ListWeatherViewMapper implements Func1<List<DailyWeather>, List<WeatherView>> {

    @Inject
    public ListWeatherViewMapper() {
    }

    @Override
    public List<WeatherView> call(List<DailyWeather> dailyWeathers) {
        List<WeatherView> weatherViewList = Observable.from(dailyWeathers)
                .map(dailyWeather -> new WeatherView(dailyWeather))
                .toList()
                .toBlocking()
                .first();
        return weatherViewList;
    }
}
