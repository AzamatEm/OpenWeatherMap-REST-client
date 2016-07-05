package com.iamoem.owmclient.di;

import com.iamoem.owmclient.model.ModelImpl;
import com.iamoem.owmclient.presenter.PresenterImpl;
import com.iamoem.owmclient.view.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by AzamatMurzagalin on 05.07.2016.
 */
@Singleton
@Component(modules = {ModelModule.class, PresenterModule.class, ViewModule.class})
public interface ApplicationComponent {
    void inject(ModelImpl model);
    void inject(PresenterImpl presenter);
    void inject(MainActivity view);
}
