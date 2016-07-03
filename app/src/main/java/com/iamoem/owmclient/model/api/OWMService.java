package com.iamoem.owmclient.model.api;


import com.iamoem.owmclient.model.modelobjects.ListWeather;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by AzamatMurzagalin on 30.06.2016.
 */
public interface OWMService {

    @GET("daily?")
    Observable<ListWeather> getCurrentWeather(@Query("q") String cityName,
                                              @Query("APPID") String appId);

}
