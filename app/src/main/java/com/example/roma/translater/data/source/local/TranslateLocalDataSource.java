package com.example.roma.translater.data.source.local;

import android.content.Context;

import com.example.roma.translater.data.TranslateItem;
import com.example.roma.translater.data.source.TranslateDataSource;
import com.example.roma.translater.transl.TranslaterFragment.Translate;

import java.util.List;

import io.reactivex.Observable;


public class TranslateLocalDataSource implements TranslateDataSource {

    private static TranslateLocalDataSource INSTANCE;

    private Backend backend;

    private TranslateLocalDataSource(Context context) {
        backend = new Backend(context);
    }

    public static TranslateLocalDataSource getInstance(Context context) {
        if (INSTANCE == null)
            INSTANCE = new TranslateLocalDataSource(context);
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
    public void saveItem(TranslateItem item) {
        backend.addItem(item);
    }

    @Override
    public void delete(TranslateItem item) {

    }

    @Override
    public void deleteItems(List<TranslateItem> list) {
        backend.deleteItems(list);
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
    public void setFavorite(TranslateItem item) {
        backend.setFavorite(item);
    }
}
