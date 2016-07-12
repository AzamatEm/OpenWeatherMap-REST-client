package com.iamoem.owmclient.di;

import com.iamoem.owmclient.presenter.IPresenter;
import com.iamoem.owmclient.presenter.PresenterImpl;

import javax.inject.Singleton;

import static org.mockito.Mockito.mock;

import dagger.Module;
import dagger.Provides;

/**
 * Created by AzamatMurzagalin on 06.07.2016.
 */
@Module
public class TestViewModule {

    @Provides
    @Singleton
    IPresenter providePresenter() {
        return mock(IPresenter.class);
    }
}
