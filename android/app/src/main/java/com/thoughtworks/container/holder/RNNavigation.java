package com.thoughtworks.container.holder;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.thoughtworks.container.application.MainApplication;

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
        MainApplication application = (MainApplication)mContext.getApplicationContext();
        ContainerActivity activity = application.getCurrentRNActivity();
        if (activity != null) {
            activity.finish();
        }
    }
}
