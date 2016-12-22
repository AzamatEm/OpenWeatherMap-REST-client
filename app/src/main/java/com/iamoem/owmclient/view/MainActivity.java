package com.iamoem.owmclient.view;

import android.app.SearchManager;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.iamoem.owmclient.R;
import com.iamoem.owmclient.databinding.ActivityMainBinding;
import com.iamoem.owmclient.di.AppDI;
import com.iamoem.owmclient.presenter.IPresenter;
import com.iamoem.owmclient.presenter.viewobjects.WeatherView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IView {

    @Inject
    IPresenter presenter;

    private ActivityMainBinding binding;
    private WeatherListAdapter adapter;
    private String query;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        AppDI.getComponent().inject(this);

        presenter.onCreate(this);

        setToolbar();

        setRecyclerView();

        binding.swipeRefresh.setOnRefreshListener(() -> {
            if(query != null && !query.equals("")) {
                presenter.onGetWeatherClick(query);
            } else {
                binding.swipeRefresh.setRefreshing(false);
            }
        });
    }

    private void setRecyclerView() {
        //set parameters to recycler view
        List<WeatherView> data = new ArrayList<>();
        adapter = new WeatherListAdapter(data, this);
        binding.weatherRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.weatherRecyclerView.setAdapter(adapter);
    }

    private void setToolbar() {
        // Set up the toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setTitle(R.string.title_weather);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_forecast, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager) MainActivity.this.getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(MainActivity.this.getComponentName()));

            SearchView finalSearchView = searchView;
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    if(query != null && !query.equals("")) {
                        presenter.onGetWeatherClick(finalSearchView.getQuery().toString());
                    }
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    query = newText;
                    return false;
                }
            });
        }

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public void showWeather(List<WeatherView> weather) {
        adapter.setData(weather);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showEmptyWeather() {
        Toast.makeText(this, getString(R.string.empty_list_toast), Toast.LENGTH_LONG).show();
    }


    @Override
    public void showError(String error){
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @Override
    public void showLoading() {
        binding.swipeRefresh.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        binding.swipeRefresh.setRefreshing(false);
    }
}
