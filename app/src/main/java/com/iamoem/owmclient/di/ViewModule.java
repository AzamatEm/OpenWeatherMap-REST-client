package com.iamoem.owmclient.di;

import com.iamoem.owmclient.presenter.IPresenter;
import com.iamoem.owmclient.presenter.PresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by AzamatMurzagalin on 05.07.2016.
 */
@Module
public class ViewModule {

    @Provides
    IPresenter providePresenter() {
        return new PresenterImpl();
    }
}
