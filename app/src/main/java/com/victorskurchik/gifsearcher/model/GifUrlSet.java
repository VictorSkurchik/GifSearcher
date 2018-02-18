package com.victorskurchik.gifsearcher.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GifUrlSet implements Serializable {

    @SerializedName("original")
    public GifImage original;

    @SerializedName("fixed_width")
    public GifImage fixed_width;

    @SerializedName("fixed_height")
    public GifImage fixed_height;
}
