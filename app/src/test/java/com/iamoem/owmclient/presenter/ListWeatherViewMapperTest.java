package com.iamoem.owmclient.presenter;

import com.iamoem.owmclient.BuildConfig;
import com.iamoem.owmclient.di.TestAppDI;
import com.iamoem.owmclient.di.TestApplicationComponent;
import com.iamoem.owmclient.model.modelobjects.DailyWeather;
import com.iamoem.owmclient.model.modelobjects.ListWeather;
import com.iamoem.owmclient.model.modelobjects.Temp;
import com.iamoem.owmclient.model.modelobjects.Weather;
import com.iamoem.owmclient.presenter.mappers.ListWeatherViewMapper;
import com.iamoem.owmclient.presenter.viewobjects.WeatherView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

/**
 * Created by AzamatMurzagalin on 08.07.2016.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(application = TestAppDI.class,
        sdk = 21,
        constants = BuildConfig.class)
public class ListWeatherViewMapperTest {
    @Inject
    ListWeatherViewMapper listweathermapper;

    ListWeather list;

    @Before
    public void setUp() {
        TestApplicationComponent component = (TestApplicationComponent) TestAppDI.getComponent();
        component.inject(this);

        //create test ListWeather
        Temp firstTemp = new Temp(10., 9., 15., 9., 12., 10.);
        Temp secondTemp = new Temp(11., 10., 16., 10., 13., 11.);
        Weather firstWeather = new Weather(0, "Clouds", "Scattered clouds", "00");
        Weather secondWeather = new Weather(0, "Rain", "rain", "01");
        DailyWeather firstElement = new DailyWeather(0, firstTemp, 11., 10,
                Collections.singletonList(firstWeather), 11., 12, 5, 6.);
        DailyWeather secondElement = new DailyWeather(1, secondTemp, 12., 11,
                Collections.singletonList(secondWeather), 12., 19, 8, 7.);
        list = new ListWeather(null, "0", 0., 2, Arrays.asList(firstElement, secondElement));
    }

    @Test
    public void testListWeatherViewMapper() {
        List<WeatherView> weatherViews = listweathermapper.call(list.getList());

        assertEquals(weatherViews.size(), 2);

        WeatherView first = weatherViews.get(0);
        WeatherView second = weatherViews.get(1);

        assertEquals(first.getDescription(), "Scattered clouds");
        assertEquals(first.getDayTemp(), 10., 0.001);
        assertEquals(first.getNightTemp(), 9., 0.001);
        assertEquals(first.getEveTemp(), 12., 0.001);
        assertEquals(first.getMornTemp(), 10., 0.001);
        assertEquals(first.getPressure(), 11., 0.001);
        assertEquals(first.getHumidity().intValue(), 10);

        assertEquals(second.getDescription(), "rain");
        assertEquals(second.getDayTemp(), 11., 0.001);
        assertEquals(second.getNightTemp(), 10., 0.001);
        assertEquals(second.getEveTemp(), 13., 0.001);
        assertEquals(second.getMornTemp(), 11., 0.001);
        assertEquals(second.getPressure(), 12., 0.001);
        assertEquals(second.getHumidity().intValue(), 11);
    }
}
