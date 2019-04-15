package com.thoughtworks.container.holder;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

    public static final String COMPONENT_NAME = "component_name";
    public static final String LAUNCH_OPTIONS = "launch_options";

    private static ReactFragment newInstance(@NonNull String componentName, Bundle launchOptions) {
        ReactFragment fragment = new ReactFragment();
        Bundle args = new Bundle();
        args.putString(COMPONENT_NAME, componentName);
        args.putBundle(LAUNCH_OPTIONS, launchOptions);
        fragment.setArguments(args);
        return fragment;
    }

    private String mComponentName;
    private Bundle mLaunchOptions;

    private ReactRootView mReactRootView;

    @Nullable
    private DoubleTapReloadRecognizer mDoubleTapReloadRecognizer;

    @Nullable
    private PermissionListener mPermissionListener;

    private ReactNativeHost getReactNativeHost() {
        return ((ReactApplication) getActivity().getApplication()).getReactNativeHost();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mComponentName = getArguments().getString(COMPONENT_NAME);
            mLaunchOptions = getArguments().getBundle(LAUNCH_OPTIONS);
        }
        if (mComponentName == null) {
            throw new IllegalStateException("Cannot loadApp if component name is null");
        }
        mDoubleTapReloadRecognizer = new DoubleTapReloadRecognizer();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mReactRootView == null) {
//            mReactRootView = new ReactRootView(getContext());
            mReactRootView = new RNGestureHandlerEnabledRootView(getActivity());
            mReactRootView.startReactApplication(
                    getReactNativeHost().getReactInstanceManager(),
                    mComponentName,
                    mLaunchOptions);
        }
        return mReactRootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getReactNativeHost().hasInstance()) {
            getReactNativeHost().getReactInstanceManager().onHostResume(getActivity(), (DefaultHardwareBackBtnHandler) getActivity());
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getReactNativeHost().hasInstance()) {
            getReactNativeHost().getReactInstanceManager().onHostPause(getActivity());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mReactRootView != null) {
            mReactRootView.unmountReactApplication();
            mReactRootView = null;
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

    // region PermissionAwareActivity

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (mPermissionListener != null &&
                mPermissionListener.onRequestPermissionsResult(requestCode, permissions, grantResults)) {
            mPermissionListener = null;
        }
    }

    @Override
    public int checkPermission(String permission, int pid, int uid) {
        return getActivity().checkPermission(permission, pid, uid);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public int checkSelfPermission(String permission) {
        return getActivity().checkSelfPermission(permission);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void requestPermissions(String[] permissions, int requestCode, PermissionListener listener) {
        mPermissionListener = listener;
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
            boolean didDoubleTapR = Assertions.assertNotNull(mDoubleTapReloadRecognizer).didDoubleTapR(keyCode, getActivity().getCurrentFocus());
            if (didDoubleTapR) {
                getReactNativeHost().getReactInstanceManager().getDevSupportManager().handleReloadJS();
                handled = true;
            }
        }
        return handled;
    }

    public static class Builder {

        private final String mComponentName;
        private Bundle mLaunchOptions;

        public Builder(String componentName) {
            mComponentName = componentName;
        }

        public Builder setLaunchOptions(Bundle launchOptions) {
            mLaunchOptions = launchOptions;
            return this;
        }

        public ReactFragment build() {
            return ReactFragment.newInstance(mComponentName, mLaunchOptions);
        }
    }
}
