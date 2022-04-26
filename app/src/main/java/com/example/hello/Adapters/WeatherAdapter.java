package com.example.hello.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hello.Models.WeatherReportModel;
import com.example.hello.R;

import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder>{

    private Context context;
    private List<WeatherReportModel> list;
    private OnWeatherListener onWeatherListener;

    public WeatherAdapter(Context context, List<WeatherReportModel> list, OnWeatherListener onWeatherListener) {
        this.context = context;
        this.list = list;
        this.onWeatherListener = onWeatherListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.weather_items, parent, false);
        return new ViewHolder(v, onWeatherListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WeatherReportModel reportModel = list.get(position);

        holder.state_name.setText(reportModel.getWeather_state_name());
        holder.minTemp.setText(""+ reportModel.getMin_temp());
        holder.maxTemp.setText(""+ reportModel.getMax_temp());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        OnWeatherListener onWeatherListener;

        TextView state_name;
        TextView minTemp;
        TextView maxTemp;

        public ViewHolder(@NonNull View itemView, OnWeatherListener onWeatherListener) {
            super(itemView);

            state_name = itemView.findViewById(R.id.weather_state);
            minTemp = itemView.findViewById(R.id.min_temp);
            maxTemp = itemView.findViewById(R.id.max_temp);

            this.onWeatherListener = onWeatherListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onWeatherListener.onWeatherClick(getAdapterPosition());
        }
    }

    public interface OnWeatherListener{
        void onWeatherClick(int position);
    }
}
