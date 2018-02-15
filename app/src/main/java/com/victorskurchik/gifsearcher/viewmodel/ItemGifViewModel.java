package com.victorskurchik.gifsearcher.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.victorskurchik.gifsearcher.model.GifResult;
import com.victorskurchik.gifsearcher.model.GifUrlSet;
import com.victorskurchik.gifsearcher.view.GifDetailActivity;

public class ItemGifViewModel extends BaseObservable {

    private GifResult gifResult;
    private Context context;

    public ItemGifViewModel(GifResult gifResult, Context context) {
        this.gifResult = gifResult;
        this.context = context;
    }

    public String getId() {
        return gifResult.id;
    }

    public GifUrlSet getImages() {
        return gifResult.images;
    }

    public String getImageUrl() {
        return gifResult.images.original.url;
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    public void onItemClick(View view) {
        context.startActivity(GifDetailActivity.launchDetail(view.getContext(), gifResult));
    }

    public void setGifResult(GifResult gifResult) {
        this.gifResult = gifResult;
        notifyChange();
    }
}