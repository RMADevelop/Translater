package com.example.roma.translater.transl.FavoriteFragment;

import com.example.roma.translater.BasePresenter;
import com.example.roma.translater.BaseView;
import com.example.roma.translater.data.TranslateItem;

import java.util.List;


public interface FavoriteContract {

    interface View extends BaseView<Presenter> {

        void updateData();

        void updateWithoutDelete();

        void updateData(List<TranslateItem> list);

        void showSearchClear();

        void hideSearchClear();

        void searchDelete();

        void showGarbage();

        void hideGarbage();

        void showDialog();


    }

    interface Presenter extends BasePresenter {

        void subscribe();

        void loadData();

        void setFavorite(TranslateItem item);

        void searchEdit(String word);

        void clearSearch();

        void searchClearClick();

        void checkDelete(List<TranslateItem> item);

        void clickDelete();

        void deleteItems();



    }


    interface FavoriteRecyclerItemClickListener {

        void clickFavorite(TranslateItem item);

        void clickItem(int id);

        void sendListDelete(List<TranslateItem> list);
    }

}
