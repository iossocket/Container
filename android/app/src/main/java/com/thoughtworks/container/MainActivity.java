package com.thoughtworks.container;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import com.thoughtworks.rnplugin.ReactFragment;
import com.thoughtworks.samplelibrary.DemoService;

import io.flutter.facade.Flutter;
import io.flutter.facade.FlutterFragment;

public class MainActivity extends AppCompatActivity {

    private DashboardFragment dashboardFragment;
    private ReactFragment inboxFragment;
    private MapFragment mapFragment;
    private FlutterFragment flutterFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DemoService service = new DemoService();
        Log.d("======>", service.fetchSthFromDB());

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
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
            inboxFragment = new ReactFragment();
            Bundle bundle = new Bundle();
            bundle.putString(ReactFragment.COMPONENT_NAME, "Home");
            inboxFragment.setArguments(bundle);
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
}
