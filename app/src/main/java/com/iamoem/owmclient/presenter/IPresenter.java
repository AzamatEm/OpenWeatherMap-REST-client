package com.iamoem.owmclient.presenter;

import com.iamoem.owmclient.view.IView;

/**
 * Created by AzamatMurzagalin on 02.07.2016.
 */
public interface IPresenter {
    void onCreate(IView view);
    void onGetWeatherClick(String cityName);
    void onStop();
}
