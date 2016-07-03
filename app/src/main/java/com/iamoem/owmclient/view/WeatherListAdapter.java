package com.iamoem.owmclient.view;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iamoem.owmclient.R;
import com.iamoem.owmclient.presenter.viewobjects.WeatherView;

import java.util.List;

/**
 * Created by AzamatMurzagalin on 03.07.2016.
 */
public class WeatherListAdapter extends RecyclerView.Adapter<WeatherListAdapter.ViewHolder> {

    private List<WeatherView> data;
    private Context context;

    public WeatherListAdapter(List<WeatherView> data, Context context) {
        this.data = data;
        this.context = context;
    }

    public List<WeatherView> getData() {
        return data;
    }

    public void setData(List<WeatherView> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_element, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        WeatherView weatherView = data.get(position);
        Resources res = context.getResources();
        holder.morningTempTv.setText(String.format(res.getString(R.string.morn_temp), weatherView.getMornTemp()));
        holder.dayTempTv.setText(String.format(res.getString(R.string.day_temp), weatherView.getDayTemp()));
        holder.eveningTempTv.setText(String.format(res.getString(R.string.evening_temp), weatherView.getEveTemp()));
        holder.nightTempTv.setText(String.format(res.getString(R.string.night_temp), weatherView.getNightTemp()));
        holder.humidityTv.setText(String.format(res.getString(R.string.humidity), weatherView.getHumidity()));
        holder.pressureTv.setText(String.format(res.getString(R.string.pressure), weatherView.getPressure()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView descriptionTv;
        TextView morningTempTv;
        TextView dayTempTv;
        TextView eveningTempTv;
        TextView nightTempTv;
        TextView humidityTv;
        TextView pressureTv;

        public ViewHolder(View itemView) {
            super(itemView);
            descriptionTv = (TextView) itemView.findViewById(R.id.description_tv);
            morningTempTv = (TextView) itemView.findViewById(R.id.morning_temp_tv);
            dayTempTv = (TextView) itemView.findViewById(R.id.day_temp_tv);
            eveningTempTv = (TextView) itemView.findViewById(R.id.eve_temp_tv);
            nightTempTv = (TextView) itemView.findViewById(R.id.nigh_temp_tv);
            humidityTv = (TextView) itemView.findViewById(R.id.humidity_tv);
            pressureTv = (TextView) itemView.findViewById(R.id.pressure_tv);
        }
    }
}