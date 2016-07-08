package com.iamoem.owmclient.di;

import com.iamoem.owmclient.model.ModelTests;
import com.iamoem.owmclient.presenter.ListWeatherViewMapperTest;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by AzamatMurzagalin on 06.07.2016.
 */
@Singleton
@Component(modules = {TestModelModule.class, TestPresenterModule.class, TestViewModule.class})
public interface TestApplicationComponent extends ApplicationComponent {
    void inject(ModelTests test);
    void inject(ListWeatherViewMapperTest test);
    /*void inject(PresenterImpl presenter);
    void inject(MainActivity view);*/
}