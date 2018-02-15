package com.victorskurchik.gifsearcher.data;

import com.victorskurchik.gifsearcher.BuildConfig;

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GifFactory {

    public final static String TRENDING_GIFS_URL = "https://api.giphy.com/v1/gifs/trending?api_key=1sjNHhsOqDJ775HGPP7SzXVxpRyf40V3&limit=10&rating=G";

    public static GifService create() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BuildConfig.API_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(GifService.class);
    }
}
