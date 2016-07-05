package com.iamoem.owmclient.model;


import com.iamoem.owmclient.model.modelobjects.ListWeather;

import rx.Observable;

/**
 * Created by AzamatMurzagalin on 02.07.2016.
 */
public interface IModel {
    Observable<ListWeather> getCurrentWeather(String cityName, String id);
}
