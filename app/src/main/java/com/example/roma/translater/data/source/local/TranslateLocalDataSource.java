package com.example.roma.translater.data.source.local;

import android.content.Context;

import com.example.roma.translater.data.TranslateItem;
import com.example.roma.translater.data.source.TranslateDataSource;
import com.example.roma.translater.transl.TranslaterFragment.Translate;
import com.example.roma.translater.util.AppExecutors;

import java.util.List;

import io.reactivex.Observable;


public class TranslateLocalDataSource implements TranslateDataSource {

    private static TranslateLocalDataSource INSTANCE;

    private Backend backend;

    private AppExecutors executor;

    private TranslateLocalDataSource(Context context, AppExecutors executor) {
        this.executor = executor;
        backend = new Backend(context);
    }

    public static TranslateLocalDataSource getInstance(Context context, AppExecutors executor) {
        if (INSTANCE == null)
            INSTANCE = new TranslateLocalDataSource(context, executor);
        return INSTANCE;
    }

    @Override
    public void getItem(String id) {

    }

    @Override
    public List<TranslateItem> getAllItems() {
        return backend.getAllItems();
    }

    @Override
    public void getFavoriteItem(String id) {

    }

    @Override
    public List<TranslateItem> getAllFavorites() {
        return backend.getAllFavorite();
    }

    @Override
    public void saveItem(final TranslateItem item) {
        Runnable save  = new Runnable() {
            @Override
            public void run() {
                backend.addItem(item);
            }
        };
        executor.getDiskIO().execute(save);
    }

    @Override
    public void delete(TranslateItem item) {

    }

    @Override
    public void deleteItems(final List<TranslateItem> list) {
        Runnable delete = new Runnable() {
            @Override
            public void run() {
                backend.deleteItems(list);
            }
        };
        executor.getDiskIO().execute(delete);
    }

    @Override
    public String searchFroomServer(String word, String lang, TranslateLoaded callback) {
        return null;
    }


    @Override
    public List<TranslateItem> searchHistory(String word) {
        return backend.searchHistory(word);
    }

    @Override
    public TranslateItem checkItem(String word) {
        return backend.checkItem(word);
    }

    @Override
    public List<TranslateItem> searchFavorite(String word) {
        return backend.searchFavorite(word);
    }

    @Override
    public void setFavorite(final TranslateItem item) {
        Runnable delete = new Runnable() {
            @Override
            public void run() {
                backend.setFavorite(item);
            }
        };
        executor.getDiskIO().execute(delete);
    }
}
