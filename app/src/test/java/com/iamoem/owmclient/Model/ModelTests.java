package com.iamoem.owmclient.model;

import com.iamoem.owmclient.BuildConfig;
import com.iamoem.owmclient.di.TestApplicationComponent;
import com.iamoem.owmclient.di.TestAppDI;
import com.iamoem.owmclient.model.api.OWMService;
import com.iamoem.owmclient.model.modelobjects.DailyWeather;
import com.iamoem.owmclient.model.modelobjects.ListWeather;
import com.iamoem.owmclient.model.modelobjects.Temp;
import com.iamoem.owmclient.model.modelobjects.Weather;
import com.iamoem.owmclient.utility.Constants;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.Arrays;
import java.util.Collections;

import javax.inject.Inject;

import rx.Observable;
import rx.observers.TestSubscriber;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by AzamatMurzagalin on 06.07.2016.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(application = TestAppDI.class,
        sdk = 21,
        constants = BuildConfig.class)
public class ModelTests {


    public TestApplicationComponent component;

    @Inject
    OWMService owmService;

    IModel model;

    String testCity = "London";

    @Before
    public void setUp() {
        //inject OWMService
        component = (TestApplicationComponent) TestAppDI.getComponent();
        component.inject(this);
        model = new ModelImpl();

    }

    @Test
    public void testGetCurrentWeather() {
        //create test ListWeather
        Temp firstTemp = new Temp(10., 9., 15., 9., 12., 10.);
        Temp secondTemp = new Temp(11., 10., 16., 10., 13., 11.);
        Weather firstWeather = new Weather(0, "Clouds", "Scattered clouds", "00");
        Weather secondWeather = new Weather(0, "Rain", "rain", "01");
        DailyWeather firstElement = new DailyWeather(0, firstTemp, 11., 10,
                Collections.singletonList(firstWeather), 11., 12, 5, 6.);
        DailyWeather secondElement = new DailyWeather(1, secondTemp, 12., 11,
                Collections.singletonList(secondWeather), 12., 19, 8, 7.);
        ListWeather list = new ListWeather(null, "0", 0., 2, Arrays.asList(firstElement, secondElement));


        when(owmService.getCurrentWeather(testCity, Constants.APPID)).thenReturn(Observable.just(list));

        TestSubscriber<ListWeather> testSubscriber = new TestSubscriber<>();

        model.getCurrentWeather(testCity, Constants.APPID).subscribe(testSubscriber);


        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);

        ListWeather weatherList = testSubscriber.getOnNextEvents().get(0);

        assertEquals(weatherList.getList().size(), 2);
    }
}
