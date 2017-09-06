package com.example.roma.translater.transl.TranslaterFragment;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.roma.translater.R;
import com.example.roma.translater.data.source.TranslateRepository;
import com.example.roma.translater.data.source.local.TranslateLocalDataSource;
import com.example.roma.translater.data.source.remote.TranslateRemoteDataSource;

import org.w3c.dom.Text;


public class Translate extends Fragment implements TranslateContract.View {

    TranslateContract.Presenter presenter;

    TextView headerTextOut;

    TextView headerTextIn;

    TextView translateWordIn;

    TextView translateWordOut;

    private ImageView favorite;

    private EditText searchEditText;

    private ImageButton clearSearch;

    private ProgressBar progressBar;


    public Translate() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_translate, container, false);
//        initHeaderSwitch(view);

        presenter = new TranslatePresenter(this, TranslateRepository.getRepository(
                TranslateLocalDataSource.getInstance(getContext()),
                TranslateRemoteDataSource.getInstance(getContext())
        ));

        initToolbar(view);
        initSearchField(view);
        initTranslateField(view);


        return view;
    }

    private void initTranslateField(View view) {
        translateWordIn = (TextView) view.findViewById(R.id.translate_word_in);
        translateWordOut = (TextView) view.findViewById(R.id.translate_word_out);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        favorite = (ImageView) view.findViewById(R.id.favorite_word_translate);
    }

    private void initSearchField(View view) {
        clearSearch = (ImageButton) view.findViewById(R.id.clear_search_translateF);

        searchEditText = (EditText) view.findViewById(R.id.searchTranslate);
        searchEditText.addTextChangedListener(new TextWatcher() {
            CountDownTimer timer;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(final Editable s) {

                if (timer != null)
                    timer.cancel();
                timer = new CountDownTimer(1000, 1) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        presenter.searchTranslate(s.toString());

                    }
                }.start();
            }
        });

        clearSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.deleteSearch();
            }
        });
    }

    private void initToolbar(View view) {
        headerTextIn = (TextView) view.findViewById(R.id.header_text_in);
        headerTextOut = (TextView) view.findViewById(R.id.header_text_out);
        ImageButton imageSwitch = (ImageButton) view.findViewById(R.id.image_switch);

        imageSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.switcherClick();
            }
        });
    }

    @Override
    public void setPresenter(TranslateContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showAnimSwitch(boolean lang) {
        if (lang) {
            headerTextIn.animate().alpha(0).withStartAction(new Runnable() {
                @Override
                public void run() {
                    headerTextIn.animate().translationX(50);

                }
            }).withEndAction(new Runnable() {
                @Override
                public void run() {
                    headerTextIn.setText("Английский");
                    headerTextIn.animate().translationX(0).alpha(1);
                }
            });
            headerTextOut.animate().alpha(0).withStartAction(new Runnable() {
                @Override
                public void run() {
                    headerTextOut.animate().translationX(-50);

                }
            }).withEndAction(new Runnable() {
                @Override
                public void run() {
                    headerTextOut.setText("Русский");
                    headerTextOut.animate().translationX(0).alpha(1);
                }
            });
        } else {
            headerTextIn.animate().alpha(0).withStartAction(new Runnable() {
                @Override
                public void run() {
                    headerTextIn.animate().translationX(50);

                }
            }).withEndAction(new Runnable() {
                @Override
                public void run() {
                    headerTextIn.setText("Русский");
                    headerTextIn.animate().translationX(0).alpha(1);
                }
            });
            headerTextOut.animate().alpha(0).withStartAction(new Runnable() {
                @Override
                public void run() {
                    headerTextOut.animate().translationX(-50);

                }
            }).withEndAction(new Runnable() {
                @Override
                public void run() {
                    headerTextOut.setText("Английский");
                    headerTextOut.animate().translationX(0).alpha(1);
                }
            });
        }

    }

    @Override
    public void showClearButton() {
        clearSearch.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideClearButton() {
        clearSearch.setVisibility(View.INVISIBLE);
    }

    @Override
    public void clearSearch() {
        searchEditText.getText().clear();
    }

    @Override
    public void unFocusSearchEdit() {
        searchEditText.clearFocus();
        searchEditText.setEnabled(false);
        searchEditText.setEnabled(true);
    }

    @Override
    public void insertWord(String word) {
        translateWordOut.setText(word);
    }

    @Override
    public void setWordIn(String word) {
        translateWordIn.setText(word);
    }

    @Override
    public void setWordOut(String word) {
        translateWordOut.setText(word);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showWords() {
        translateWordIn.setVisibility(View.VISIBLE);
        translateWordOut.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideWords() {
        translateWordIn.setVisibility(View.INVISIBLE);
        translateWordOut.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showFavourite() {
        favorite.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideFavourite() {
        favorite.setVisibility(View.INVISIBLE);
    }
}
