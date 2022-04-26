package com.example.hello;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hello.Adapters.WeatherAdapter;
import com.example.hello.Models.WeatherReportModel;
import com.example.hello.Network.Services.DataServices;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Weather extends AppCompatActivity implements WeatherAdapter.OnWeatherListener {

    //Java Android App using REST API - Network Data in Android Course 1:54:52
    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    protected List<WeatherReportModel> weatherData;

    RecyclerView weather;

    WeatherAdapter adapter;

    DataServices dataServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        dataServices = new DataServices(this);

        Button cityId = findViewById(R.id.btn_getCityId);
        Button weatherByCityId = findViewById(R.id.btn_getWeatherByCityId);
        Button weatherByCityName = findViewById(R.id.btn_getWeatherByCityName);

        EditText city = findViewById(R.id.txt_cty);

        weather = findViewById(R.id.rv_weatherReports);

        weatherData = new ArrayList<>();
        adapter = new WeatherAdapter(getApplicationContext(), weatherData, this);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(weather.getContext(), linearLayoutManager.getOrientation());

        weather.setHasFixedSize(true);
        weather.setLayoutManager(linearLayoutManager);
        weather.addItemDecoration(dividerItemDecoration);
        weather.setAdapter(adapter);

        cityId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCityId(city.getText().toString());
            }
        });

        weatherByCityId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWeatherByCityId();
            }
        });

        weatherByCityName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWeatherByCityName();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void getCityId(String city){
        dataServices.getCityId(city, new DataServices.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                Log.d("** GET CITY ID ERROR **", message);
            }

            @Override
            public void onResponse(String cityID) {
                Log.d("***** GET CITY ID *****", cityID);
            }
        });
    }

    public void getWeatherByCityId(){
        dataServices.getCityForecastByID("44418", new DataServices.VolleyForeCastByIDResponseListener() {
            @Override
            public void onError(String message) {
                Log.d("** GET CITY ID ERROR **", message);
            }

            @Override
            public void onResponse(JSONArray reportModel) {

                try {

                    WeatherReportModel first_day = new WeatherReportModel();

                    for(int i = 0; i < reportModel.length(); i++) {
                        JSONObject first_day_from_api = reportModel.getJSONObject(i);

                        first_day.setId(first_day_from_api.getInt("id"));
                        first_day.setWeather_state_name(first_day_from_api.getString("weather_state_name"));
                        first_day.setWeather_state_abbr(first_day_from_api.getString("weather_state_abbr"));
                        first_day.setWind_direction_compass(first_day_from_api.getString("wind_direction_compass"));
                        first_day.setCreated(first_day_from_api.getString("created"));
                        first_day.setApplicable_date(first_day_from_api.getString("applicable_date"));
                        first_day.setMin_temp(first_day_from_api.getLong("min_temp"));
                        first_day.setMax_temp(first_day_from_api.getLong("max_temp"));
                        first_day.setThe_temp(first_day_from_api.getLong("the_temp"));
                        first_day.setWind_speed(first_day_from_api.getLong("wind_speed"));
                        first_day.setWind_direction(first_day_from_api.getLong("wind_direction"));
                        first_day.setAir_pressure(first_day_from_api.getInt("air_pressure"));
                        first_day.setHumidity(first_day_from_api.getInt("humidity"));
                        first_day.setVisibility(first_day_from_api.getLong("visibility"));
                        first_day.setPredictability(first_day_from_api.getInt("predictability"));

                        weatherData.add(first_day);
                    }

                    adapter.notifyDataSetChanged();

                } catch (Throwable t) {
                    Log.e("My App", "Could not parse malformed JSON: ");
                }
            }
        });
    }

    public void getWeatherByCityName(){}

    @Override
    public void onWeatherClick(int position) {
        final float max = weatherData.get(position).getMax_temp();
        final float min = weatherData.get(position).getMin_temp();

        Toast.makeText(this, max+" "+min, Toast.LENGTH_SHORT).show();
    }
}