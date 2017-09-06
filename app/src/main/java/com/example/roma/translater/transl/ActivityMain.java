package com.example.roma.translater.transl;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.roma.translater.R;
import com.example.roma.translater.transl.FavoriteFragment.FavoriteFragment;
import com.example.roma.translater.transl.HistoryFragment.HistoryFragment;
import com.example.roma.translater.transl.TranslaterFragment.Translate;
import com.example.roma.translater.util.ActivityUtils;

public class ActivityMain extends AppCompatActivity implements TranslaterActContract.View {

    private TranslaterActContract.Presenter presenterTranslater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenterTranslater = new TranslaterActPresenter(this);
        initNavigationBottomView();
        initFragment();
    }


    private void initFragment() {
        Translate translateFragment = (Translate) getSupportFragmentManager().findFragmentById(R.id.container);
        if (translateFragment == null) {
            translateFragment = new Translate();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), translateFragment, R.id.container);
        }
    }

    private void initNavigationBottomView() {

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_nav);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.translater:
                        presenterTranslater.setTranslaterFragment();
                        break;
                    case R.id.history:
                        presenterTranslater.setHistoryFragment();
                        break;
                    case R.id.favorite:
                        presenterTranslater.setFavoriteFragment();
                        break;
                }
                return true;
            }
        });

    }

    @Override
    public void setPresenter(TranslaterActContract.Presenter presenter) {
        this.presenterTranslater = presenter;
    }

    @Override
    public void showTranslater() {
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), new Translate(), R.id.container);
    }

    @Override
    public void showHistory() {
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), new HistoryFragment(), R.id.container);

    }

    @Override
    public void showFavorite() {
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),new FavoriteFragment(),R.id.container);
    }

}
