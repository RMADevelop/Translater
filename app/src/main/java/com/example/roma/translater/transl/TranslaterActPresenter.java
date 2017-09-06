package com.example.roma.translater.transl;

/**
 * Created by Roma on 12.08.2017.
 */

public class TranslaterActPresenter implements TranslaterActContract.Presenter {

    private final TranslaterActContract.View translaterView;

    private boolean firstLoad = true;

    public TranslaterActPresenter(TranslaterActContract.View translaterView) {
        this.translaterView = translaterView;
        translaterView.setPresenter(this);
    }


    @Override
    public void setTranslaterFragment() {
        translaterView.showTranslater();
    }

    @Override
    public void setHistoryFragment() {
        translaterView.showHistory();
    }

    @Override
    public void setFavoriteFragment() {
        translaterView.showFavorite();
    }

    @Override
    public void start() {
//        loadData();
    }

    private void loadData() {

    }
}
