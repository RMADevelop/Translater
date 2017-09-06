package com.example.roma.translater.transl.FavoriteFragment;

import com.example.roma.translater.data.TranslateItem;
import com.example.roma.translater.data.source.TranslateRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roma on 15.08.2017.
 */

public class FavoritePresenter implements FavoriteContract.Presenter {

    private List<TranslateItem> list = new ArrayList<>();

    private List<TranslateItem> listDelete;

    TranslateRepository repository;
    FavoriteContract.View favoriteView;

    public FavoritePresenter(TranslateRepository repository, FavoriteContract.View favoriteView) {
        this.repository = repository;
        this.favoriteView = favoriteView;

        favoriteView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void subscribe() {
        loadData();
    }

    @Override
    public void loadData() {
        list = repository.getAllFavorites();
        favoriteView.updateData(list);
    }

    @Override
    public void setFavorite(TranslateItem item) {
        repository.setFavorite(item);
    }

    @Override
    public void searchEdit(String word) {
        List<TranslateItem> list = repository.searchFavorite(word);
        if (word.length() != 0) {
            favoriteView.showSearchClear();
            favoriteView.updateData(list);
        } else {
            favoriteView.hideSearchClear();
            loadData();
        }


    }

    @Override
    public void clearSearch() {

    }

    @Override
    public void searchClearClick() {
        favoriteView.searchDelete();
    }

    @Override
    public void checkDelete(List<TranslateItem> item) {
        this.listDelete = item;
        if (item.size() != 0) {
            favoriteView.showGarbage();
        } else favoriteView.hideGarbage();
    }

    @Override
    public void clickDelete() {
        favoriteView.showDialog();
    }

    @Override
    public void deleteItems() {
        repository.deleteItems(listDelete);
        favoriteView.updateWithoutDelete();
        favoriteView.hideGarbage();
    }
}
