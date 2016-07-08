package com.iamoem.owmclient.di;


import android.app.Application;

/**
 * Created by AzamatMurzagalin on 05.07.2016.
 */
public class AppDI extends Application {

    private static ApplicationComponent component;
    public static ApplicationComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = buildComponent();
    }

    protected ApplicationComponent buildComponent() {
        return DaggerApplicationComponent
                .builder()
                .build();
    }

}
