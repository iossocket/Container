package com.thoughtworks.rnplugin;

import android.support.v7.app.AppCompatActivity;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import javax.annotation.Nonnull;

public class RNNavigation extends ReactContextBaseJavaModule {

    private final ReactApplicationContext mContext;

    public RNNavigation(@Nonnull ReactApplicationContext reactContext) {
        super(reactContext);
        mContext = reactContext;
    }

    @Nonnull
    @Override
    public String getName() {
        return "RNNavigation";
    }

    @ReactMethod
    public void popToRoot() {
        RNPluginApplication application = (RNPluginApplication)mContext.getApplicationContext();
        AppCompatActivity activity = application.getCurrentActivity();
        if (activity != null) {
            activity.finish();
        }
    }
}
