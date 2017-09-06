package com.example.roma.translater.transl.HistoryFragment;

import com.example.roma.translater.data.TranslateItem;
import com.example.roma.translater.data.source.TranslateRepository;

import java.util.List;


public class HistoryFragmentPresenter implements HistoryFragmentContract.Presenter {

    private HistoryFragment historyView;

    private TranslateRepository repository;

    private List<TranslateItem> listDelete;

    public HistoryFragmentPresenter(HistoryFragment historyView, TranslateRepository repository) {
        this.historyView = historyView;
        this.repository = repository;
        historyView.setPresenter(this);
    }

    @Override
    public void start() {

    }


    @Override
    public void subscribe() {
//        repository.saveItem(new TranslateItem("cat","кошка","en","ru"));
//        repository.saveItem(new TranslateItem("dog","собака","en","ru"));
//        repository.saveItem(new TranslateItem("house","дом","en","ru"));
//        repository.saveItem(new TranslateItem("зависимость","dependency","ru","en"));
//        repository.saveItem(new TranslateItem("cloud","облако","en","ru"));
//        repository.saveItem(new TranslateItem("машина","car","ru","en"));
//        repository.saveItem(new TranslateItem("привет","hello","ru","en"));
//        repository.saveItem(new TranslateItem("run","бежать","en","ru"));

        loadData();
    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void loadData() {
        List<TranslateItem> list = repository.getAllItems();
        historyView.updateData(list);
    }

    @Override
    public void favoriteClick(TranslateItem item) {
        item.setFavorite(item.isFavorite() ? 0 : 1);
        repository.setFavorite(item);
        historyView.updateData();
    }


    @Override
    public void itemChoose(int id) {

    }

    @Override
    public void clcikTest() {
        historyView.showToast();
    }

    @Override
    public void searchEdit(String word) {
        List<TranslateItem> items = repository.searchHistory(word);
        historyView.updateData(items);
        if (word.length() != 0)
            historyView.showClearSearchImage();
        else {
            historyView.hideClearSearchImage();
            historyView.unFocusSearch();
            loadData();

        }
    }

    @Override
    public void clearSearch() {
        historyView.clearSearch();

    }

    @Override
    public void checkListDelete(List<TranslateItem> list) {
        this.listDelete = list;
        if (list.size() != 0) {
            historyView.showGarbage();
        } else historyView.hideGarbage();
    }

    @Override
    public void clickDeleteButtons() {
        historyView.showDialogDelete();
    }

    @Override
    public void deleteItems() {
        repository.deleteItems(listDelete);
        historyView.updateDataWithoutDelete();
        historyView.hideGarbage();
    }
}
