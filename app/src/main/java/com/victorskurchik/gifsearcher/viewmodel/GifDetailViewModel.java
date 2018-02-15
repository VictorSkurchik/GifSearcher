package com.victorskurchik.gifsearcher.viewmodel;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.victorskurchik.gifsearcher.model.GifResult;
import com.victorskurchik.gifsearcher.model.GifUrlSet;

public class GifDetailViewModel {

    private GifResult gifResult;

    public GifDetailViewModel(GifResult gifResult) {
        this.gifResult = gifResult;
    }

    public String getId() {
        return gifResult.id;
    }

    public GifUrlSet getImages() {
        return gifResult.images;
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext()).load(imageUrl).into(view);
    }
}