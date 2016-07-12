package com.iamoem.owmclient.view;

import android.os.Bundle;

import com.iamoem.owmclient.BuildConfig;
import com.iamoem.owmclient.di.TestAppDI;
import com.iamoem.owmclient.di.TestApplicationComponent;
import com.iamoem.owmclient.presenter.IPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import javax.inject.Inject;

/**
 * Created by AzamatMurzagalin on 12.07.2016.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(application = TestAppDI.class,
        sdk = 21,
        constants = BuildConfig.class)
public class ViewTest {

    @Inject
    IPresenter presenter;

    MainActivity activity;

    @Before
    public void preparation() {
        TestApplicationComponent component = (TestApplicationComponent) TestAppDI.getComponent();
        component.inject(this);

        activity = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void onCreateCallingTest() {
        verify(presenter).onCreate(activity);
    }

    @Test
    public void onStopCallingTest() {
        activity.onStop();
        verify(presenter).onStop();
    }
}
