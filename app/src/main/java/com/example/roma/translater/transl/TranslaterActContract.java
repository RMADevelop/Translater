package com.example.roma.translater.transl;

import com.example.roma.translater.BasePresenter;
import com.example.roma.translater.BaseView;

/**
 * Created by Roma on 12.08.2017.
 */

public interface TranslaterActContract {
    interface View extends BaseView<Presenter> {

        void showTranslater();

        void showHistory();

        void showFavorite();

    }

    interface Presenter extends BasePresenter {
        void setTranslaterFragment();

        void setHistoryFragment();

        void setFavoriteFragment();

    }
}
