package com.thoughtworks.container.application;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;

import com.thoughtworks.rnplugin.RNContainer;
import com.thoughtworks.rnplugin.RNPluginApplication;

public class MainApplication extends Application implements RNPluginApplication {

    private AppCompatActivity currentActivity = null;
    private RNContainer rnContainer;

    @Override
    public void onCreate() {
        super.onCreate();
        rnContainer = new RNContainer(this);
    }

    @Override
    public AppCompatActivity getCurrentActivity() {
        return currentActivity;
    }

    @Override
    public void setCurrentActivity(AppCompatActivity currentActivity) {
        this.currentActivity = currentActivity;
    }

    @Override
    public RNContainer getReactNativeContainer() {
        return rnContainer;
    }
}
