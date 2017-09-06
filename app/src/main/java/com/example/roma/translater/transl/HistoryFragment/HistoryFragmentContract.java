package com.example.roma.translater.transl.HistoryFragment;

import com.example.roma.translater.BasePresenter;
import com.example.roma.translater.BaseView;
import com.example.roma.translater.data.TranslateItem;

import java.util.List;


public interface HistoryFragmentContract {
    interface View extends BaseView<Presenter> {

        void showToast();

        void updateData();

        void updateDataWithoutDelete();

        void updateData(List<TranslateItem> list);

        void showClearSearchImage();

        void hideClearSearchImage();

        void clearSearch();

        void clearSearchUnFocusable();

        void showGarbage();

        void hideGarbage();

        void showDialogDelete();

        void unFocusSearch();


    }

    interface Presenter extends BasePresenter {

        void subscribe();

        void unsubscribe();

        void loadData();

        void favoriteClick(TranslateItem item);

        void itemChoose(int id);

        void clcikTest();

        void searchEdit(String word);

        void clearSearch();

        void checkListDelete(List<TranslateItem> list);

        void clickDeleteButtons();

        void deleteItems();


    }

    interface TranslateItemListener {

        void onTranslateItemClick(TranslateItem item);

        void onTranslateFavoriteClick(TranslateItem item);

        void sendListDelete(List<TranslateItem> listDelete);
    }
}
