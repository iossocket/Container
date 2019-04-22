package com.thoughtworks.container.application;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;
import com.swmansion.gesturehandler.react.RNGestureHandlerPackage;
import com.thoughtworks.container.BuildConfig;
import com.thoughtworks.rnplugin.ContainerPackage;
import com.thoughtworks.rnplugin.RNContainer;
import com.thoughtworks.rnplugin.RNPluginApplication;

import java.util.Arrays;
import java.util.List;

public class MainApplication extends Application implements ReactApplication, RNPluginApplication {

    private AppCompatActivity currentActivity = null;

    private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
        @Override
        public boolean getUseDeveloperSupport() {
            return BuildConfig.DEBUG;
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

    @Override
    public ReactNativeHost getReactNativeHost() {
        return mReactNativeHost;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SoLoader.init(this, /* native exopackage */ false);
    }

    public AppCompatActivity getCurrentActivity() {
        return currentActivity;
    }

    @Override
    public RNContainer getReactNativeContainer() {
        return null;
    }

    public void setCurrentActivity(AppCompatActivity currentActivity) {
        this.currentActivity = currentActivity;
    }
}
