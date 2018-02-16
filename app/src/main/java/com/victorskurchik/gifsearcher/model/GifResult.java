package com.victorskurchik.gifsearcher.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GifResult implements Serializable {

    @SerializedName("id")
    public String id;

    @SerializedName("images")
    public GifUrlSet images;

    @SerializedName("title")
    public String title;
}
