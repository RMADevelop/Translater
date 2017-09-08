package com.example.roma.translater.transl.TranslaterFragment;


import android.util.Log;

import com.example.roma.translater.data.TranslateItem;
import com.example.roma.translater.data.source.TranslateDataSource.TranslateLoaded;
import com.example.roma.translater.data.source.TranslateRepository;


public class TranslatePresenter implements TranslateContract.Presenter {
    TranslateContract.View translateView;

    TranslateRepository repository;

    private boolean lang_ru_en = true;

    public TranslatePresenter(TranslateContract.View translateView, TranslateRepository repository) {
        this.translateView = translateView;
        this.repository = repository;
    }

    @Override
    public void start() {

    }

    @Override
    public void switcherClick() {
        if (lang_ru_en) {
            translateView.showAnimSwitch(true);
            lang_ru_en = false;
        } else {
            translateView.showAnimSwitch(false);
            lang_ru_en = true;
        }
    }

    @Override
    public void deleteSearch() {
        translateView.clearSearch();
        translateView.hideClearButton();
        translateView.unFocusSearchEdit();
    }


    @Override
    public void searchTranslate(final String wordIn) {
        Log.v("gfskdfbg", "search begin");
        switchViewsState(false);

        if (wordIn.length() == 0) {
            translateView.hideProgress();
            return;
        }

        translateView.showClearButton();
        translateView.setWordIn(wordIn);

        TranslateItem itemFromLocal = repository.checkItem(wordIn);
        if (itemFromLocal != null) {
            switchViewsState(true);
            translateView.setWordOut(itemFromLocal.getWordOut());
            return;
        }
        Log.v("gfskdfbg", "after local search");

        repository.searchFroomServer(wordIn, lang_ru_en ? "en-ru" : "ru-en", new TranslateLoaded() {
            @Override
            public void complete(String word) {
                TranslateItem item = new TranslateItem(wordIn, word, lang_ru_en ? "en-ru" : "ru-en");
                Log.v("gfskdfbg", "item");

                switchViewsState(true);
                translateView.setWordOut(word);
                repository.saveItem(item);
            }
        });

    }

    private void switchViewsState(boolean stateShow) {
        if (stateShow) {
            translateView.hideProgress();
            translateView.showWords();
            translateView.showFavourite();
        } else {
            translateView.showProgress();
            translateView.hideFavourite();
            translateView.hideWords();
        }

    }

    @Override
    public void setFavorite() {

    }
}
