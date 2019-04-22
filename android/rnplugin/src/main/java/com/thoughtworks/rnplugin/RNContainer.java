package com.thoughtworks.rnplugin;

import android.app.Application;

import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;
import com.swmansion.gesturehandler.react.RNGestureHandlerPackage;

import java.util.Arrays;
import java.util.List;

public class RNContainer {

    private Application application;
    private ReactNativeHost reactNativeHost;

    public ReactNativeHost getReactNativeHost() {
        if (reactNativeHost == null) {
            reactNativeHost = new ReactNativeHost(application) {
                @Override
                public boolean getUseDeveloperSupport() {
                    return true;
                }

                @Override
                protected List<ReactPackage> getPackages() {
                    return Arrays.<ReactPackage>asList(
                            new MainReactPackage(),
                            new RNGestureHandlerPackage(),
                            new ContainerPackage()
                    );
                }

                @Override
                protected String getJSMainModuleName() {
                    return "index";
                }
            };
        }
        return reactNativeHost;
    }

    public RNContainer(Application application) {
        this.application = application;
        SoLoader.init(application, false);
    }
}
