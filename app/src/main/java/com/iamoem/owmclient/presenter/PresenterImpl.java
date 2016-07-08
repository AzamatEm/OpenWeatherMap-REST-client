package com.iamoem.owmclient.presenter;

import com.iamoem.owmclient.di.AppDI;
import com.iamoem.owmclient.model.IModel;
import com.iamoem.owmclient.presenter.mappers.ListWeatherViewMapper;
import com.iamoem.owmclient.utility.Constants;
import com.iamoem.owmclient.view.IView;

import javax.inject.Inject;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by AzamatMurzagalin on 02.07.2016.
 */
public class PresenterImpl implements IPresenter {

    IView view;

    @Inject
    CompositeSubscription compositeSubscription;
    @Inject
    IModel model;
    @Inject
    ListWeatherViewMapper listweathermapper;

    public PresenterImpl() {
        AppDI.getComponent().inject(this);
    }

    public void onCreate(IView view) {
        this.view = view;
    }

    @Override
    public void onGetWeatherClick(String cityName) {

        Subscription subscription = model.getCurrentWeather(cityName, Constants.APPID)
                .map(ListWeather -> ListWeather.getList())
                .map(listweathermapper)
                .subscribe(
                    weatherViews -> {
                        if(weatherViews.size() != 0) {
                            view.showWeather(weatherViews);
                        }
                    },
                    e -> view.showError(e.getMessage())
                );

        compositeSubscription.add(subscription);
    }

    @Override
    public void onStop() {
        compositeSubscription.clear();
    }
}
