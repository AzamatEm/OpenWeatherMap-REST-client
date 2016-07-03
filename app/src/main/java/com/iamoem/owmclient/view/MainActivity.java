package com.iamoem.owmclient.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.iamoem.owmclient.R;
import com.iamoem.owmclient.presenter.Presenter;
import com.iamoem.owmclient.presenter.PresenterImpl;
import com.iamoem.owmclient.presenter.viewobjects.WeatherView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View {

    private EditText cityNameEt;
    private Presenter presenter;
    private WeatherListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new PresenterImpl(this);

        cityNameEt = (EditText) findViewById(R.id.city_name_et);
        Button getWeatherBtn = (Button) findViewById(R.id.get_weather_btn);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.weather_recycler_view);
        List<WeatherView> data = new ArrayList<>();
        adapter = new WeatherListAdapter(data, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        getWeatherBtn.setOnClickListener(
                v -> presenter.onGetWeatherClick(cityNameEt.getText().toString())
        );
    }


    @Override
    public void showWeather(List<WeatherView> weather) {
        adapter.setData(weather);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String error){
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showEmptyWeather() {
        Toast.makeText(this, getString(R.string.empty_list_toast), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(presenter != null) {
            presenter.onStop();
        }
    }
}
