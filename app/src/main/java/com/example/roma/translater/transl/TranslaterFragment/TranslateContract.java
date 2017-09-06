package com.example.roma.translater.transl.TranslaterFragment;

import com.example.roma.translater.BasePresenter;
import com.example.roma.translater.BaseView;


public interface TranslateContract {
    interface View extends BaseView<Presenter> {

        void showAnimSwitch(boolean lang);

        void showClearButton();

        void hideClearButton();

        void clearSearch();

        void unFocusSearchEdit();

        void insertWord(String word);

        void setWordIn(String word);

        void setWordOut(String word);

        void showProgress();

        void hideProgress();

        void showWords();

        void hideWords();

        void showFavourite();

        void hideFavourite();

    }

    interface Presenter extends BasePresenter {

        void switcherClick();

        void deleteSearch();

        void searchTranslate(String word);

        void setFavorite();
    }
}
