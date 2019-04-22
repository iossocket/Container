package com.thoughtworks.rnplugin;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.infer.annotation.Assertions;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactRootView;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.devsupport.DoubleTapReloadRecognizer;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.modules.core.PermissionListener;
import com.swmansion.gesturehandler.react.RNGestureHandlerEnabledRootView;

public class ReactFragment extends Fragment implements PermissionAwareActivity {

    public static final String COMPONENT_NAME = "COMPONENT_NAME";
    public static final String LAUNCH_OPTIONS = "LAUNCH_OPTIONS";

    private static ReactFragment newInstance(@NonNull String componentName, Bundle launchOptions) {
        ReactFragment fragment = new ReactFragment();
        Bundle args = new Bundle();
        args.putString(COMPONENT_NAME, componentName);
        args.putBundle(LAUNCH_OPTIONS, launchOptions);
        fragment.setArguments(args);
        return fragment;
    }

    private AppCompatActivity activity;
    private String componentName;
    private Bundle launchOptions;

    private ReactRootView reactRootView;
    private DoubleTapReloadRecognizer doubleTapReloadRecognizer;
    private PermissionListener permissionListener;

    public static class Builder {

        private final String componentName;
        private Bundle launchOptions;

        public Builder(String componentName) {
            this.componentName = componentName;
        }

        public Builder setLaunchOptions(Bundle launchOptions) {
            this.launchOptions = launchOptions;
            return this;
        }

        public ReactFragment build() {
            return ReactFragment.newInstance(componentName, launchOptions);
        }
    }

    private ReactNativeHost getReactNativeHost() {
        RNPluginApplication application = (RNPluginApplication)activity.getApplication();
        return application.getReactNativeContainer().getReactNativeHost();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (AppCompatActivity)context;
        if (getArguments() != null) {
            componentName = getArguments().getString(COMPONENT_NAME);
            launchOptions = getArguments().getBundle(LAUNCH_OPTIONS);
        }
        if (componentName == null) {
            throw new IllegalStateException("Cannot loadApp if component name is null");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doubleTapReloadRecognizer = new DoubleTapReloadRecognizer();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (reactRootView == null) {
            reactRootView = new RNGestureHandlerEnabledRootView(getActivity());
            reactRootView.startReactApplication(
                    getReactNativeHost().getReactInstanceManager(),
                    componentName,
                    launchOptions);
        }
        return reactRootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getReactNativeHost().hasInstance()) {
            getReactNativeHost().getReactInstanceManager()
                    .onHostResume(activity, (DefaultHardwareBackBtnHandler)activity);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getReactNativeHost().hasInstance()) {
            getReactNativeHost().getReactInstanceManager().onHostPause(activity);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (reactRootView != null) {
            reactRootView.unmountReactApplication();
            reactRootView = null;
        }
        if (getReactNativeHost().hasInstance()) {
            ReactInstanceManager reactInstanceMgr = getReactNativeHost().getReactInstanceManager();

            // onDestroy may be called on a ReactFragment after another ReactFragment has been
            // created and resumed with the same React Instance Manager. Make sure we only clean up
            // host's React Instance Manager if no other React Fragment is actively using it.
            if (reactInstanceMgr.getLifecycleState() != LifecycleState.RESUMED &&
                    reactInstanceMgr.getLifecycleState() != LifecycleState.BEFORE_RESUME) {
                reactInstanceMgr.onHostDestroy(getActivity());
                getReactNativeHost().clear();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (this.getReactNativeHost().hasInstance()) {
            this.getReactNativeHost().getReactInstanceManager().onActivityResult(activity, requestCode, resultCode, data);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissionListener != null &&
                permissionListener.onRequestPermissionsResult(requestCode, permissions, grantResults)) {
            permissionListener = null;
        }
    }

    @Override
    public int checkPermission(String permission, int pid, int uid) {
        return activity.checkPermission(permission, pid, uid);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public int checkSelfPermission(String permission) {
        return activity.checkSelfPermission(permission);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void requestPermissions(String[] permissions, int requestCode, PermissionListener listener) {
        permissionListener = listener;
        requestPermissions(permissions, requestCode);
    }

    public void onBackPressed() {
        if (getReactNativeHost().hasInstance()) {
            getReactNativeHost().getReactInstanceManager().onBackPressed();
        }
    }

    @SuppressWarnings("unused")
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        boolean handled = false;
        if (getReactNativeHost().getUseDeveloperSupport() && getReactNativeHost().hasInstance()) {
            if (keyCode == KeyEvent.KEYCODE_MENU) {
                getReactNativeHost().getReactInstanceManager().showDevOptionsDialog();
                handled = true;
            }
            boolean didDoubleTapR = Assertions.assertNotNull(doubleTapReloadRecognizer).didDoubleTapR(keyCode, getActivity().getCurrentFocus());
            if (didDoubleTapR) {
                getReactNativeHost().getReactInstanceManager().getDevSupportManager().handleReloadJS();
                handled = true;
            }
        }
        return handled;
    }
}
