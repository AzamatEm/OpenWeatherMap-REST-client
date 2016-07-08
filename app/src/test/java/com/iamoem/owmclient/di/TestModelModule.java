package com.iamoem.owmclient.di;

import com.iamoem.owmclient.model.api.OWMService;
import com.iamoem.owmclient.utility.Constants;

import static org.mockito.Mockito.mock;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by AzamatMurzagalin on 06.07.2016.
 */
@Module
public class TestModelModule {

    @Provides
    @Singleton
    OWMService provideApiInterface() {
        return mock(OWMService.class);
    }

    @Provides
    @Singleton
    @Named(Constants.ioThreadName)
    Scheduler provideIoScheduler() {
        return Schedulers.immediate();
    }

    @Provides
    @Singleton
    @Named(Constants.mainThreadName)
    Scheduler provideMainScheduler() {
        return Schedulers.immediate();
    }
}