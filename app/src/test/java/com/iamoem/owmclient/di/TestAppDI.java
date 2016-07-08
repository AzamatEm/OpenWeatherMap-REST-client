package com.iamoem.owmclient.di;

/**
 * Created by AzamatMurzagalin on 06.07.2016.
 */
public class TestAppDI extends AppDI {

    @Override
    protected TestApplicationComponent buildComponent() {
        return DaggerTestApplicationComponent
                .builder()
                .build();
    }
}
