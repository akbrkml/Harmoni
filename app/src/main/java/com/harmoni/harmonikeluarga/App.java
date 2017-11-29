package com.harmoni.harmonikeluarga;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * Created by akbar on 06/10/17.
 */

public class App extends MultiDexApplication {

    private static App instance;
    private Gson gson;
    private static Context context;

    public App() {
        instance = this;
    }

    public static App getInstance() {
        return instance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        createGson();
    }

    private void createGson() {
        gson = new GsonBuilder().create();
    }

    public Gson getGson() {
        return gson;
    }

    public static Context getContext(){
        return context;
    }
}
