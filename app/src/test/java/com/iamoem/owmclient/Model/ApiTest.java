package com.iamoem.owmclient.model;

import com.iamoem.owmclient.BuildConfig;
import com.iamoem.owmclient.model.api.OWMModule;
import com.iamoem.owmclient.model.api.OWMService;
import com.iamoem.owmclient.model.modelobjects.DailyWeather;
import com.iamoem.owmclient.model.modelobjects.ListWeather;
import com.iamoem.owmclient.utility.Constants;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.io.IOException;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import rx.observers.TestSubscriber;

import static org.junit.Assert.assertEquals;

/**
 * Created by AzamatMurzagalin on 06.07.2016.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(sdk = 21,
        constants = BuildConfig.class)
public class ApiTest {

    private MockWebServer server;
    private OWMService owmService;
    private final String testCityName = "London";
    private final String testResponse = "{\"city\":{\"id\":2643743,\"name\":\"London\",\"coord\":{\"lon\":-0.12574,\"lat\":51.50853},\"country\":\"GB\",\"population\":0},\"cod\":\"200\",\"message\":0.0092,\"cnt\":7,\"list\":[{\"dt\":1467806400,\"temp\":{\"day\":293.5,\"min\":289.4,\"max\":294.82,\"night\":289.4,\"eve\":294.6,\"morn\":291.02},\"pressure\":1029.23,\"humidity\":81,\"weather\":[{\"id\":802,\"main\":\"Clouds\",\"description\":\"scattered clouds\",\"icon\":\"03d\"}],\"speed\":3.11,\"deg\":291,\"clouds\":32}]}";

    /**
     * sets up mockwebserver to make http response to request with
     * cityname = testCityName and response with testResponse
     * @throws IOException
     */
    @Before
    public void setUp() throws IOException {

        server = new MockWebServer();
        server.start();

        final Dispatcher dispatcher = new Dispatcher() {
            @Override
            public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
                if (request.getPath().equals("/daily?&q=" + testCityName + "&APPID=" + Constants.APPID)) {
                    return new MockResponse().setResponseCode(200)
                            .setBody(testResponse);
                }

                return new MockResponse().setResponseCode(404);
            }
        };

        server.setDispatcher(dispatcher);
        HttpUrl baseUrl = server.url("/");
        owmService = OWMModule.getService(baseUrl.toString());
    }

    @Test
    public void testGetForecast() {
        TestSubscriber<ListWeather> testSubscriber = new TestSubscriber<>();
        owmService.getCurrentWeather(testCityName, Constants.APPID).subscribe(testSubscriber);

        testSubscriber.assertNoErrors();

        ListWeather listWeather = testSubscriber.getOnNextEvents().get(0);

        List<DailyWeather> daily = listWeather.getList();

        assertEquals(listWeather.getCity().getName(), testCityName);
        assertEquals(listWeather.getMessage(), 0.0092, 0.00001);
        assertEquals(daily.size(), 1);
        assertEquals(daily.get(0).getWeather().size(), 1);
        assertEquals(daily.get(0).getWeather().get(0).getDescription(), "scattered clouds");
    }

    @After
    public void finish() throws IOException {
        server.shutdown();
    }
}
