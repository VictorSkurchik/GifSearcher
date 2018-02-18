package com.victorskurchik.gifsearcher.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GifImage implements Serializable {

    @SerializedName("url")
    public String url;

    @SerializedName("width")
    public int width;

    @SerializedName("height")
    public int height;

    @SerializedName("size")
    public int size;
}
