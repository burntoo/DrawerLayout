package com.example.hello.Network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleyRequestSingleton {

    private static VolleyRequestSingleton instance;
    private RequestQueue requestQueue;
    private static Context ctx;

    private VolleyRequestSingleton (Context context) {
        //avoid memory leak in context
        ctx = context.getApplicationContext();
        requestQueue = getRequestQueue();
    }

    public static synchronized VolleyRequestSingleton getInstance(Context context){
        if(instance == null){
            instance = new VolleyRequestSingleton(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue(){
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void  addToRequestQueue(Request<T> req){
        getRequestQueue().add(req);
    }
}
