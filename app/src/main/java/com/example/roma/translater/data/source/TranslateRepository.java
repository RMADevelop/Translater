package com.example.roma.translater.data.source;

import com.example.roma.translater.data.TranslateItem;
import com.example.roma.translater.data.source.local.TranslateLocalDataSource;
import com.example.roma.translater.data.source.remote.TranslateRemoteDataSource;
import com.example.roma.translater.transl.TranslaterFragment.Translate;

import java.util.List;

/**
 * Created by Roma on 13.08.2017.
 */

public class TranslateRepository implements TranslateDataSource {

    public static TranslateRepository INSTANCE = null;

    private TranslateLocalDataSource localDataSource;
    private TranslateRemoteDataSource remoteDataSource;

    private TranslateRepository(TranslateLocalDataSource localDataSource, TranslateRemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    public static TranslateRepository getRepository(TranslateLocalDataSource localDataSource, TranslateRemoteDataSource remoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new TranslateRepository(localDataSource, remoteDataSource);
        }
        return INSTANCE;
    }

    public static void destroyRepository() {
        INSTANCE = null;
    }

    @Override
    public void getItem(String id) {

    }

    @Override
    public List<TranslateItem> getAllItems() {
        return localDataSource.getAllItems();
    }

    @Override
    public void getFavoriteItem(String id) {

    }

    @Override
    public List<TranslateItem> getAllFavorites() {
        return localDataSource.getAllFavorites();
    }

    @Override
    public void saveItem(TranslateItem item) {
        localDataSource.saveItem(item);
    }

    @Override
    public void delete(TranslateItem item) {

    }

    @Override
    public void deleteItems(List<TranslateItem> list) {
        localDataSource.deleteItems(list);
    }

    @Override
    public String searchFroomServer(String word, String lang, TranslateDataSource.TranslateLoaded callback) {
        return remoteDataSource.searchFroomServer(word, lang, callback);
    }

    @Override
    public List<TranslateItem> searchHistory(String word) {
        return localDataSource.searchHistory(word);
    }

    @Override
    public TranslateItem checkItem(String word) {
        return localDataSource.checkItem(word);
    }

    @Override
    public List<TranslateItem> searchFavorite(String word) {
        return localDataSource.searchFavorite(word);
    }

    @Override
    public void setFavorite(TranslateItem item) {
        localDataSource.setFavorite(item);
    }
}
