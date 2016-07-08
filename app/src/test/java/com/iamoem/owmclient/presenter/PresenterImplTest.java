package com.iamoem.owmclient.presenter;

import com.iamoem.owmclient.BuildConfig;
import com.iamoem.owmclient.di.TestAppDI;

import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

/**
 * Created by AzamatMurzagalin on 08.07.2016.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(application = TestAppDI.class,
        sdk = 21,
        constants = BuildConfig.class)
public class PresenterImplTest {
}
