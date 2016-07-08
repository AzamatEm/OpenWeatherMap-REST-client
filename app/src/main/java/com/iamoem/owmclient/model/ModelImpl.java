package com.iamoem.owmclient.model;

import com.iamoem.owmclient.di.AppDI;
import com.iamoem.owmclient.model.api.OWMService;
import com.iamoem.owmclient.model.modelobjects.ListWeather;
import com.iamoem.owmclient.utility.Constants;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by AzamatMurzagalin on 02.07.2016.
 */
public class ModelImpl implements IModel {

    @Inject
    OWMService apiService;

    @Inject
    @Named(Constants.ioThreadName)
    Scheduler ioScheduler;

    @Inject
    @Named(Constants.mainThreadName)
    Scheduler mainScheduler;

    public ModelImpl() {
        AppDI.getComponent().inject(this);
    }

    @Override
    public Observable<ListWeather> getCurrentWeather(String cityName, String id) {
        return apiService.getCurrentWeather(cityName, id)
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
                .unsubscribeOn(ioScheduler);
    }
}
