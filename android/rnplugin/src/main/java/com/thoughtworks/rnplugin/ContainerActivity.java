package com.thoughtworks.rnplugin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;
import com.facebook.react.ReactRootView;
import com.swmansion.gesturehandler.react.RNGestureHandlerEnabledRootView;

public class ContainerActivity extends ReactActivity {

    private RNPluginApplication mainApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainApplication = (RNPluginApplication)this.getApplication();
    }

    protected void onResume() {
        super.onResume();
        mainApplication.setCurrentActivity(this);
    }
    protected void onPause() {
        clearReferences();
        super.onPause();
    }
    protected void onDestroy() {
        clearReferences();
        super.onDestroy();
    }

    private void clearReferences(){
        AppCompatActivity currActivity = mainApplication.getCurrentActivity();
        if (this.equals(currActivity))
            mainApplication.setCurrentActivity(null);
    }

    @Override
    protected String getMainComponentName() {
        return "App";
    }

    @Override
    protected ReactActivityDelegate createReactActivityDelegate() {
        return new ReactActivityDelegate(this, getMainComponentName()) {
            @Override
            protected ReactRootView createRootView() {
                return new RNGestureHandlerEnabledRootView(ContainerActivity.this);
            }
        };
    }
}
