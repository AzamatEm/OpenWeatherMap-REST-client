package com.iamoem.owmclient.di;

import com.iamoem.owmclient.model.IModel;
import com.iamoem.owmclient.model.ModelImpl;

import static org.mockito.Mockito.mock;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by AzamatMurzagalin on 06.07.2016.
 */
@Module
public class TestPresenterModule {

    @Provides
    @Singleton
    IModel provideDataRepository() {
        return mock(IModel.class);
    }

    @Provides
    CompositeSubscription provideCompositeSubscription() {
        return new CompositeSubscription();
    }
}