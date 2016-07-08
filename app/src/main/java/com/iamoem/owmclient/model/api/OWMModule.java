package com.iamoem.owmclient.model.api;

import com.iamoem.owmclient.utility.Constants;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by AzamatMurzagalin on 02.07.2016.
 */
public class OWMModule {

    public static OWMService getService(String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build();

        OWMService weatherService = retrofit.create(OWMService.class);

        return weatherService;
    }
}
