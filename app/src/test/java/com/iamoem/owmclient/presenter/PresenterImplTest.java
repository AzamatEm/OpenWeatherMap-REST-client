package com.iamoem.owmclient.presenter;

import android.widget.ListView;

import com.iamoem.owmclient.BuildConfig;
import com.iamoem.owmclient.di.AppDI;
import com.iamoem.owmclient.di.TestAppDI;
import com.iamoem.owmclient.di.TestApplicationComponent;
import com.iamoem.owmclient.model.IModel;
import com.iamoem.owmclient.model.modelobjects.ListWeather;
import com.iamoem.owmclient.presenter.viewobjects.WeatherView;
import com.iamoem.owmclient.utility.Constants;
import com.iamoem.owmclient.view.IView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;


import java.util.ArrayList;
import java.util.Collections;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by AzamatMurzagalin on 08.07.2016.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(application = TestAppDI.class,
        sdk = 21,
        constants = BuildConfig.class)
public class PresenterImplTest {
    @Inject
    IModel model;

    @Inject
    ListWeather list;

    IView view;

    PresenterImpl presenter;

    String testCity = "London";

    @Before
    public void preparation() {
        view = mock(IView.class);
        presenter = new PresenterImpl();
        TestApplicationComponent component = (TestApplicationComponent) TestAppDI.getComponent();
        component.inject(this);


        presenter.onCreate(view);
    }

    @Test
    public void testLoadingData() {
        doAnswer(invocation -> Observable.just(list)).
                when(model).getCurrentWeather(testCity, Constants.APPID);
        presenter.onGetWeatherClick(testCity);
        presenter.onStop();

        verify(view).showWeather(anyList());
    }

    @Test
    public void testLoadingIndicator() {
        doAnswer(invocation -> Observable.just(list)).
                when(model).getCurrentWeather(testCity, Constants.APPID);
        presenter.onGetWeatherClick(testCity);
        presenter.onStop();

        verify(view).showLoading();
        verify(view).hideLoading();
    }

    @Test
    public void testLoadingNull() {

        doAnswer(invocation -> Observable.just(null)).
                when(model).getCurrentWeather(testCity, Constants.APPID);

        presenter.onGetWeatherClick(testCity);

        verify(view).showEmptyWeather();
    }

    @Test
    public void testOnError() {
        doAnswer(invocation -> Observable.error(new Throwable("0")))
                .when(model)
                .getCurrentWeather(testCity, Constants.APPID);

        presenter.onGetWeatherClick(testCity);

        verify(view).showError("0");
    }

}
