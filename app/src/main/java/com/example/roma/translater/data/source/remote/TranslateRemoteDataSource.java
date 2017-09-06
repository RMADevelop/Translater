package com.example.roma.translater.data.source.remote;

import android.content.Context;
import android.util.Log;

import com.example.roma.translater.data.TranslateItem;
import com.example.roma.translater.data.source.TranslateDataSource;
import com.example.roma.translater.pojo.Translate;
import com.example.roma.translater.retrofit.Server;
import com.example.roma.translater.util.Const;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Roma on 13.08.2017.
 */

public class TranslateRemoteDataSource implements TranslateDataSource {
    public static TranslateRemoteDataSource INSTANCE = null;
    Context context;

    private TranslateRemoteDataSource(Context context) {
        this.context = context;
    }

    public static TranslateRemoteDataSource getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new TranslateRemoteDataSource(context);
        }
        return INSTANCE;
    }

    @Override
    public void getItem(String id) {

    }

    @Override
    public List<TranslateItem> getAllItems() {
        return null;
    }

    @Override
    public void getFavoriteItem(String id) {

    }

    @Override
    public List<TranslateItem> getAllFavorites() {
        return null;
    }

    @Override
    public void saveItem(TranslateItem item) {

    }

    @Override
    public void delete(TranslateItem item) {

    }

    @Override
    public void deleteItems(List<TranslateItem> list) {

    }

    @Override
    public String searchFroomServer(final String word,String lang, final TranslateDataSource.TranslateLoaded callback) {
        final String[] translate = new String[1];
        OkHttpClient.Builder okhttp = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofit.create(Server.class).getTranslateWord(Const.API_KEY_TRANSLATE, word, "en-ru").enqueue(new Callback<Translate>() {

            @Override
            public void onResponse(Call<Translate> call, Response<Translate> response) {
                if (response.isSuccessful()) {
                    translate[0] = response.body().getText().get(0);
                    Log.v("TESTTRANSLATE", translate[0] + " trranslate" + word);
                    callback.complete(translate[0]);
                }


            }

            @Override
            public void onFailure(Call<Translate> call, Throwable t) {

            }

        });
        return translate[0];

    }

    @Override
    public List<TranslateItem> searchHistory(String word) {

        return null;
    }

    @Override
    public TranslateItem checkItem(String word) {
        return null;
    }

    @Override
    public List<TranslateItem> searchFavorite(String word) {
        return null;
    }

    @Override
    public void setFavorite(TranslateItem item) {

    }
}
