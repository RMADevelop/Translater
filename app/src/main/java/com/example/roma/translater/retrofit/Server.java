package com.example.roma.translater.retrofit;


import com.example.roma.translater.pojo.Translate;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Server {

    @GET("translate")
    Call<Translate> getTranslateWord(@Query("key") String key,
                                     @Query("text") String text,
                                     @Query("lang") String lang);


}
