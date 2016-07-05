package com.iamoem.owmclient.di;

import com.iamoem.owmclient.model.api.OWMModule;
import com.iamoem.owmclient.model.api.OWMService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by AzamatMurzagalin on 05.07.2016.
 */
@Module
public class ModelModule {

    @Provides
    @Singleton
    OWMService provideApiInterface() {
        return OWMModule.getService();
    }


}
