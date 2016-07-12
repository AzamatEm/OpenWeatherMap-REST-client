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

    @Inject
    ListWeather list;

    IModel model;

    String testCity = "London";

    @Before
    public void preparation() {
        //inject OWMService
        component = (TestApplicationComponent) TestAppDI.getComponent();
        component.inject(this);
        model = new ModelImpl();

    }

    @Test
    public void testGetCurrentWeather() {
        //create test ListWeather
        when(owmService.getCurrentWeather(testCity, Constants.APPID)).thenReturn(Observable.just(list));

        TestSubscriber<ListWeather> testSubscriber = new TestSubscriber<>();

        model.getCurrentWeather(testCity, Constants.APPID).subscribe(testSubscriber);


        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);

        ListWeather weatherList = testSubscriber.getOnNextEvents().get(0);

        assertEquals(weatherList.getList().size(), 2);
    }
}
