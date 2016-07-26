# OpenWeatherMap-REST-client
This is simple android client for Open Weather Map using Retrofit2 + RxAndroid + Dagger2.
For tests I used Mockito and Mockwebserver

## Structure
This is uml class diagram:
![uml](https://github.com/AzamatEm/OpenWeatherMap-REST-client/blob/master/owm_uml2.png)

Data that gets by retrofit is written in model objects that was created by [json2pojo](http://www.jsonschema2pojo.org/).
You can find them in package com.iamoem.owmclient.model.modelobjects

But these classes are model classes. And there is also class that is named WeatherView inside com.iamoem.owmclient.presenter.viewobjects. It contains all data that must be shown. So data flow looks like this: REST API -> modelobjects -> viewobjects -> view. Converting modelobjects to viewobjects is needed to separate work with model and view (in order to prevent some problems after changing api or sth like this). This converting are done by mapper ListWeatherViewMapper in com.iamoem.owmclient.presenter.mappers

## Tests

All tests were done using Mockito and MockWebServer.
And changed component in Dagger2 to change injecting objects to mocks
