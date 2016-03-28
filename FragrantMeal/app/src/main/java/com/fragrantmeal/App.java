package com.fragrantmeal;

import android.app.Application;

import com.fragrantmeal.net.FMealNet;
import com.fragrantmeal.util.Config;
import com.google.gson.Gson;
import com.mj.core.converter.CustomGsonConverter;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by CaoBin on 2016/3/21.
 */
public class App extends Application {
    private static FMealNet fMealNet;
    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static FMealNet getfMealNet() {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(30 * 1000, TimeUnit.SECONDS);
        okHttpClient.setReadTimeout(30 * 1000, TimeUnit.SECONDS);
        RestAdapter restAdapter = new RestAdapter
                .Builder()
                .setConverter(new CustomGsonConverter(new Gson()))
                .setEndpoint(Config.IP_HOST)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setClient(new OkClient(okHttpClient))
                .build();
        fMealNet = restAdapter.create(FMealNet.class);
        return fMealNet;
    }
}
