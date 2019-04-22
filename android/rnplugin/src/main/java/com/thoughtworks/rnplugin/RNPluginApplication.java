package com.thoughtworks.rnplugin;

import android.support.v7.app.AppCompatActivity;

public interface RNPluginApplication {
    RNContainer getReactNativeContainer();
    void setCurrentActivity(AppCompatActivity currentActivity);
    AppCompatActivity getCurrentActivity();
}
