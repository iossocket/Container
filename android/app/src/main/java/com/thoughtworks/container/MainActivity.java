package com.thoughtworks.container;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.thoughtworks.container.holder.ReactFragment;

import io.flutter.facade.Flutter;
import io.flutter.facade.FlutterFragment;

public class MainActivity extends AppCompatActivity implements DefaultHardwareBackBtnHandler {

    private DashboardFragment dashboardFragment;
    private ReactFragment inboxFragment;
    private MapFragment mapFragment;
    private FlutterFragment flutterFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                switch (itemId) {
                    case R.id.navigation_dashboard:
                        openFragment(getDashboardFragment());
                        return true;
                    case R.id.navigation_inbox:
                        openFragment(getInboxFragment());
                        return true;
                    case R.id.navigation_map:
                        openFragment(getFlutterFragment());
                        return true;
                }
                return false;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.navigation_dashboard);
    }

    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public DashboardFragment getDashboardFragment() {
        if (dashboardFragment == null) {
            dashboardFragment = new DashboardFragment();
        }
        return dashboardFragment;
    }

    public ReactFragment getInboxFragment() {
        if (inboxFragment == null) {
            inboxFragment = new ReactFragment.Builder("Home").build();
        }
        return inboxFragment;
    }

    public MapFragment getMapFragment() {
        if (mapFragment == null) {
            mapFragment = new MapFragment();
        }
        return mapFragment;
    }

    public FlutterFragment getFlutterFragment() {
        if (flutterFragment == null) {
            flutterFragment = Flutter.createFragment(null);
        }
        return flutterFragment;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        boolean handled = false;
        Fragment activeFragment = getSupportFragmentManager().findFragmentById(R.id.container);
        if (activeFragment instanceof ReactFragment) {
            handled = ((ReactFragment) activeFragment).onKeyUp(keyCode, event);
        }
        return handled || super.onKeyUp(keyCode, event);
    }

    @Override
    public void invokeDefaultOnBackPressed() {

    }
}
