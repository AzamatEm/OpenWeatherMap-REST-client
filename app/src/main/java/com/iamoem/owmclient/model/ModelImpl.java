package com.iamoem.owmclient.model;

import com.iamoem.owmclient.di.ComponentProvider;
import com.iamoem.owmclient.model.api.OWMService;
import com.iamoem.owmclient.model.modelobjects.ListWeather;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by AzamatMurzagalin on 02.07.2016.
 */
public class ModelImpl implements IModel {

    @Inject
    OWMService apiService;

    public ModelImpl() {
        ComponentProvider.getComponent().inject(this);
    }

    @Override
    public Observable<ListWeather> getCurrentWeather(String cityName, String id) {
        return apiService.getCurrentWeather(cityName, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
