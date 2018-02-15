package com.victorskurchik.gifsearcher.data;

import com.victorskurchik.gifsearcher.BuildConfig;

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GifFactory {

    private final static String TRENDING_GIFS_ENDPOINT = "gifs/trending";
    private final static String SEARCH_GIFS_ENDPOINT = "gifs/search";

    private static int offset = 0;
    private static int gifsLimit = 10;

    public static GifService create() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BuildConfig.API_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(GifService.class);
    }

    public static String getTrendingGifsQueryUrl() {
        String queryUrl = BuildConfig.API_ENDPOINT + TRENDING_GIFS_ENDPOINT + "?api_key=" + BuildConfig.API_KEY + "&limit=" + gifsLimit +
                "&offset=" + offset + "&rating=G";
        updateOffset();
        return queryUrl;
    }

    public static String getSearchGifsQueryUrl(String query) {
        resetOffset();
        return BuildConfig.API_ENDPOINT + SEARCH_GIFS_ENDPOINT + "?api_key=" + BuildConfig.API_KEY + "&q=" + query + "&limit=" + gifsLimit + "&rating=G&lang=en";
    }

    private static void updateOffset() {
        offset += gifsLimit;
    }

    private static void resetOffset() {
        offset = 0;
    }

    public static int getGifsLimit() {
        return gifsLimit;
    }

    public static void setGifsLimit(int gifsLimit) {
        GifFactory.gifsLimit = gifsLimit;
    }
}
