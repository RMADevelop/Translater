package com.example.roma.translater.transl.HistoryFragment;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.roma.translater.R;
import com.example.roma.translater.data.TranslateItem;
import com.example.roma.translater.data.source.TranslateRepository;
import com.example.roma.translater.data.source.local.TranslateLocalDataSource;
import com.example.roma.translater.data.source.remote.TranslateRemoteDataSource;
import com.example.roma.translater.util.AppExecutors;

import java.util.Collections;
import java.util.List;


public class HistoryFragment extends Fragment implements HistoryFragmentContract.View {

    MenuItem garbage;

    private HistoryFragmentContract.Presenter presenter;

    private HistoryFragmentContract.TranslateItemListener itemListener = new HistoryFragmentContract.TranslateItemListener() {
        @Override
        public void onTranslateItemClick(TranslateItem item) {

        }

        @Override
        public void onTranslateFavoriteClick(TranslateItem item) {
            presenter.favoriteClick(item);
        }

        @Override
        public void sendListDelete(List<TranslateItem> listDelete) {
            presenter.checkListDelete(listDelete);
        }
    };

    private Toolbar toolbar;

    private EditText search;

    private RecyclerView recyclerView;

    private ImageView clearSearch;

    private ImageButton garbageButton;


    AdapterRecycler adapter;


    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        View view = inflater.inflate(R.layout.fragment_history_recycler, container, false);
        presenter = new HistoryFragmentPresenter(this,
                TranslateRepository.getRepository(
                        TranslateLocalDataSource.getInstance(getContext(), new AppExecutors()),
                        TranslateRemoteDataSource.getInstance(getContext())));

        adapter = new AdapterRecycler(itemListener, Collections.<TranslateItem>emptyList(), getContext());
        initToolbar(view);
        initSearchField(view);
        initRecyclerView(view);

        return view;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onResume() {
        super.onResume();
        presenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.unsubscribe();
    }

    private void initRecyclerView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_history);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void initSearchField(View view) {

        clearSearch = (ImageView) view.findViewById(R.id.clear_search_translate);
        clearSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.clearSearch();
            }
        });
        search = (EditText) view.findViewById(R.id.search_edit_history);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                presenter.searchEdit(s.toString());
            }
        });
    }

    private void initToolbar(View view) {
        garbageButton = (ImageButton) view.findViewById(R.id.garbage_btn);
        garbageButton.setEnabled(false);
        garbageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.clickDeleteButtons();
            }
        });
    }


    @Override

    public void setPresenter(HistoryFragmentContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_toolbar_history, menu);
        garbage = menu.findItem(R.id.garbage);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.garbage:
                presenter.clickDeleteButtons();
        }
        return true;
    }

    @Override
    public void showToast() {
        Toast.makeText(getContext(), "Click!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateData() {
        adapter.updateAdapter();
    }

    @Override
    public void updateDataWithoutDelete() {
        adapter.deleteFromList();
    }


    @Override
    public void updateData(List<TranslateItem> list) {
        adapter.updateAdapter(list);
    }

    @Override
    public void showClearSearchImage() {
        clearSearch.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideClearSearchImage() {
        clearSearch.setVisibility(View.INVISIBLE);
    }

    @Override
    public void clearSearch() {
        search.setText("");
        presenter.loadData();
    }

    @Override
    public void clearSearchUnFocusable() {
        search.setFocusable(false);
    }

    @Override
    public void showGarbage() {
        garbage.setEnabled(true);
    }

    @Override
    public void hideGarbage() {
        garbage.setEnabled(false);

    }

    @Override
    public void showDialogDelete() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(R.string.isDelete).setNegativeButton("НЕТ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).setPositiveButton("да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                presenter.deleteItems();
            }
        }).show();
    }

    @Override
    public void unFocusSearch() {
        search.clearFocus();
        search.setEnabled(false);
        search.setEnabled(true);
    }
}
