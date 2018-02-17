package com.victorskurchik.gifsearcher.data;

import com.google.gson.annotations.SerializedName;
import com.victorskurchik.gifsearcher.model.GifResult;

import java.util.List;

public class GifResponse {
    @SerializedName("data")
    private List<GifResult> gifsDate;

    public List<GifResult> getGifData() {
        return gifsDate;
    }

    public void setGifsDate(List<GifResult> gifsDate) {
        this.gifsDate = gifsDate;
    }
}
