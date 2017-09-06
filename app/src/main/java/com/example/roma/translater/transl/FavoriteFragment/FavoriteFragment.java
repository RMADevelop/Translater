package com.example.roma.translater.transl.FavoriteFragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.roma.translater.R;
import com.example.roma.translater.data.TranslateItem;
import com.example.roma.translater.data.source.TranslateRepository;
import com.example.roma.translater.data.source.local.TranslateLocalDataSource;
import com.example.roma.translater.data.source.remote.TranslateRemoteDataSource;

import java.util.Collections;
import java.util.List;

public class FavoriteFragment extends Fragment implements FavoriteContract.View {

    private FavoriteContract.FavoriteRecyclerItemClickListener listener = new FavoriteContract.FavoriteRecyclerItemClickListener() {
        @Override
        public void clickFavorite(TranslateItem item) {
            presenter.setFavorite(item);
        }

        @Override
        public void clickItem(int id) {

        }

        @Override
        public void sendListDelete(List<TranslateItem> list) {
            presenter.checkDelete(list);
        }
    };

    private FavoriteContract.Presenter presenter;

    private EditText searchEditText;

    private ImageView clearSearch;

    private RecyclerView favoriteRecycler;

    private AdapterRecycler adapter;

    private ImageButton garbageButtoon;


    private Toolbar toolbar;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;


    public FavoriteFragment() {
        // Required empty public constructor
    }


    public static FavoriteFragment newInstance(String param1, String param2) {
        FavoriteFragment fragment = new FavoriteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_favorite_recycler, container, false);
        setHasOptionsMenu(true);
        presenter = new FavoritePresenter(
                TranslateRepository
                        .getRepository(
                                TranslateLocalDataSource
                                        .getInstance(getContext()),
                                TranslateRemoteDataSource
                                        .getInstance(getContext())),
                this);
        initToolbar(view);
        initSearchField(view);
        adapter = new AdapterRecycler(listener, Collections.<TranslateItem>emptyList(), getContext());
        initRecyclerView(view);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_toolbar_history,menu);
    }

    private void initToolbar(View view) {

        garbageButtoon = (ImageButton) view.findViewById(R.id.garbage_btn);
        garbageButtoon.setEnabled(false);
        garbageButtoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.clickDelete();
            }
        });

    }


    private void initRecyclerView(View view) {
        favoriteRecycler = (RecyclerView) view.findViewById(R.id.recycler_favorite);
        favoriteRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        favoriteRecycler.setAdapter(adapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.subscribe();
    }

    private void initSearchField(View view) {

        searchEditText = (EditText) view.findViewById(R.id.search_edit_favorite);
        searchEditText.addTextChangedListener(new TextWatcher() {
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
        clearSearch = (ImageView) view.findViewById(R.id.clear_search_translate);
        clearSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.searchClearClick();
            }
        });
    }

    @Override
    public void setPresenter(FavoriteContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void updateData() {
        adapter.updateDataInRecycler();
    }

    @Override
    public void updateWithoutDelete() {
        adapter.updateWithoutDelete();
    }

    @Override
    public void updateData(List<TranslateItem> list) {
        adapter.updateDataInRecycler(list);
    }

    @Override
    public void showSearchClear() {
        clearSearch.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideSearchClear() {
        clearSearch.setVisibility(View.INVISIBLE);
    }

    @Override
    public void searchDelete() {
        searchEditText.setText("");
        searchEditText.setEnabled(false);
        searchEditText.setEnabled(true);
    }

    @Override
    public void showGarbage() {
        garbageButtoon.setEnabled(true);
    }

    @Override
    public void hideGarbage() {
        garbageButtoon.setEnabled(false);
    }

    @Override
    public void showDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.isDelete).setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                presenter.deleteItems();
            }
        }).setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).show();
    }


}
