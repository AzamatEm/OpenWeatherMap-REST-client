package com.iamoem.owmclient.di;

import com.iamoem.owmclient.model.modelobjects.DailyWeather;
import com.iamoem.owmclient.model.modelobjects.ListWeather;
import com.iamoem.owmclient.model.modelobjects.Temp;
import com.iamoem.owmclient.model.modelobjects.Weather;

import java.util.Arrays;
import java.util.Collections;

import dagger.Module;
import dagger.Provides;

/**
 * Created by AzamatMurzagalin on 12.07.2016.
 */
@Module
public class TestDataModule {

    @Provides
    ListWeather provideListWeather() {
        Temp firstTemp = new Temp(10., 9., 15., 9., 12., 10.);
        Temp secondTemp = new Temp(11., 10., 16., 10., 13., 11.);
        Weather firstWeather = new Weather(0, "Clouds", "Scattered clouds", "00");
        Weather secondWeather = new Weather(0, "Rain", "rain", "01");
        DailyWeather firstElement = new DailyWeather(0, firstTemp, 11., 10,
                Collections.singletonList(firstWeather), 11., 12, 5, 6.);
        DailyWeather secondElement = new DailyWeather(1, secondTemp, 12., 11,
                Collections.singletonList(secondWeather), 12., 19, 8, 7.);

        return new ListWeather(null, "0", 0., 2, Arrays.asList(firstElement, secondElement));
    }
}
