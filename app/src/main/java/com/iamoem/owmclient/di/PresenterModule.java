package com.iamoem.owmclient.di;

import com.iamoem.owmclient.model.IModel;
import com.iamoem.owmclient.model.ModelImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by AzamatMurzagalin on 05.07.2016.
 */
@Module
public class PresenterModule {

    @Provides
    @Singleton
    IModel provideDataRepository() {
        return new ModelImpl();
    }

    @Provides
    CompositeSubscription provideCompositeSubscription() {
        return new CompositeSubscription();
    }
}
