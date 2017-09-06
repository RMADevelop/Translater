package com.example.roma.translater.data.source;

import com.example.roma.translater.data.TranslateItem;

import java.util.List;


public interface TranslateDataSource {

    interface TranslateLoaded {

        void complete(String word);
    }

    void getItem(String id);

    List<TranslateItem> getAllItems();

    void getFavoriteItem(String id);

    List<TranslateItem> getAllFavorites();

    void saveItem(TranslateItem item);

    void delete(TranslateItem item);

    void deleteItems(List<TranslateItem> list);

    String searchFroomServer(String word, String lang, TranslateLoaded callback);

    List<TranslateItem> searchHistory(String word);

    TranslateItem checkItem(String word);

    List<TranslateItem> searchFavorite(String word);

    void setFavorite(TranslateItem item);


}
