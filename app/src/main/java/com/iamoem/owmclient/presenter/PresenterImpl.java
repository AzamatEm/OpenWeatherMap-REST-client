package com.iamoem.owmclient.presenter;

import com.iamoem.owmclient.model.Model;
import com.iamoem.owmclient.model.ModelImpl;
import com.iamoem.owmclient.presenter.mappers.ListWeatherViewMapper;
import com.iamoem.owmclient.view.View;

import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by AzamatMurzagalin on 02.07.2016.
 */
public class PresenterImpl implements Presenter{

    private Model model = new ModelImpl();
    private View view;
    private Subscription subscription = Subscriptions.empty();

    public PresenterImpl(View view) {
        this.view = view;
    }

    @Override
    public void onGetWeatherClick(String cityName) {
        if(!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }

        subscription = model.getCurrentWeather(cityName, "fd5b9b9e7b0a3099869316588463f020")
                .map(ListWeather -> ListWeather.getList())
                .map(new ListWeatherViewMapper())
                .subscribe(
                    weatherViews -> {
                        if(weatherViews.size() != 0) {
                            view.showWeather(weatherViews);
                        } else {
                            view.showEmptyWeather();
                        }
                    },
                    e -> view.showError(e.getMessage())
                );
    }

    @Override
    public void onStop() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
