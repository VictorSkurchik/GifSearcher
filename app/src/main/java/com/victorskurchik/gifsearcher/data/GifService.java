package com.victorskurchik.gifsearcher.data;

import retrofit2.http.GET;
import retrofit2.http.Url;
import io.reactivex.Observable;

public interface GifService {
    @GET
    Observable<GifResponse> fetchGifs(@Url String url);
}
