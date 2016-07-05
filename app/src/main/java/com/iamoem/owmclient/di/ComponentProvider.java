package com.iamoem.owmclient.di;



/**
 * Created by AzamatMurzagalin on 05.07.2016.
 */
public class ComponentProvider {
    private static ApplicationComponent component;
    public static ApplicationComponent getComponent() {
        if(component == null) {
            component = buildComponent();
        }
        return component;
    }

    protected static ApplicationComponent buildComponent() {
        return DaggerApplicationComponent
                .builder()
                .build();
    }

}
