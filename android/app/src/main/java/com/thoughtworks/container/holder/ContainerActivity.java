package com.thoughtworks.container.holder;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;
import com.facebook.react.ReactRootView;
import com.swmansion.gesturehandler.react.RNGestureHandlerEnabledRootView;
import com.thoughtworks.container.application.MainApplication;


public class ContainerActivity extends ReactActivity {

    private MainApplication mainApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainApplication = (MainApplication)this.getApplication();
    }

    protected void onResume() {
        super.onResume();
        mainApplication.setCurrentRNActivity(this);
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
        ContainerActivity currActivity = mainApplication.getCurrentRNActivity();
        if (this.equals(currActivity))
            mainApplication.setCurrentRNActivity(null);
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
