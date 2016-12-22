package com.iamoem.owmclient.view;

import com.iamoem.owmclient.presenter.viewobjects.WeatherView;

import java.util.List;

/**
 * Created by AzamatMurzagalin on 02.07.2016.
 */
public interface IView {
    void showWeather(List<WeatherView> weather);
    void showError(String error);
    void showEmptyWeather();
    void showLoading();
    void hideLoading();
}
