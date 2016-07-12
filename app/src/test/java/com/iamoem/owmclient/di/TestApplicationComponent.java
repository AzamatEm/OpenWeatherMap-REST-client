package com.iamoem.owmclient.di;

import com.iamoem.owmclient.model.ModelTests;
import com.iamoem.owmclient.presenter.ListWeatherViewMapperTest;
import com.iamoem.owmclient.presenter.PresenterImpl;
import com.iamoem.owmclient.presenter.PresenterImplTest;
import com.iamoem.owmclient.view.ViewTest;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by AzamatMurzagalin on 06.07.2016.
 */
@Singleton
@Component(modules = {TestModelModule.class, TestPresenterModule.class, TestViewModule.class,
                        TestDataModule.class})
public interface TestApplicationComponent extends ApplicationComponent {
    void inject(ModelTests test);
    void inject(ListWeatherViewMapperTest test);
    void inject(PresenterImplTest presenter);
    void inject(ViewTest view);
}