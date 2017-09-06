package com.example.roma.translater;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.roma.translater.transl.HistoryFragment.HistoryFragment;
import com.example.roma.translater.transl.TranslaterFragment.Translate;


public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNav;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();

        initFragment();

        initBottomNavigation();
    }

    private void initFragment() {
        fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.container);
        if (fragment == null) {
            setFragmentInContainer(new Translate());
        }
    }

    private void initToolbar() {
    }

    private void initBottomNavigation() {
        bottomNav = (BottomNavigationView) findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.translater:
                        setFragmentInContainer(new Translate());
                        break;
                    case R.id.history:
                        setFragmentInContainer(new HistoryFragment());
                        break;
                }
                return true;
            }
        });
    }

    private void setFragmentInContainer(Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }


}
