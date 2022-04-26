package com.example.hello.Network.Services;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.hello.Network.VolleyRequestSingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DataServices {

    public  static  final  String QUERY_FOR_CITY_ID = "https://www.metaweather.com/api/location/search/?query=";

    public static final String QUERY_FOR_CITY_WEATHER_BY_ID = "https://www.metaweather.com/api/location/";
    Context context;

    String cityId = "";

    public DataServices(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener{
        void onError(String message);

        void onResponse(String cityID);
    }

    public interface VolleyForeCastByIDResponseListener{
        void onError(String message);

        void onResponse(JSONArray reportModel);
    }

    public void getCityId(String cityName, VolleyResponseListener volleyResponseListener){
        String url = QUERY_FOR_CITY_ID + cityName;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            JSONObject jsonObject = response.getJSONObject(0);
                            cityId = jsonObject.getString("woeid");
                            String title = jsonObject.getString("title");
                            String location_type = jsonObject.getString("location_type");
                            String latt_long = jsonObject.getString("latt_long");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        volleyResponseListener.onResponse(cityId);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponseListener.onError(error.toString());
            }
        });
        VolleyRequestSingleton.getInstance(context).addToRequestQueue(jsonArrayRequest);
    }

    public void getCityForecastByID(String cityID, VolleyForeCastByIDResponseListener foreCastByIDResponseListener){
        //List<WeatherReportModel> report = new ArrayList<>();

        String url = QUERY_FOR_CITY_WEATHER_BY_ID + cityID;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONArray consolidated_weather_list = response.getJSONArray("consolidated_weather");


                    foreCastByIDResponseListener.onResponse(consolidated_weather_list);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                foreCastByIDResponseListener.onError(error.toString());
            }
        });

        VolleyRequestSingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }
//
//    public List<WeatherReportModel> getCityForecastByName(String cityName){}


}
