package com.victorskurchik.gifsearcher.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.victorskurchik.gifsearcher.R;
import com.victorskurchik.gifsearcher.model.GifResult;

public class GifDetailViewModel {

    private GifResult gifResult;
    private Context context;

    public GifDetailViewModel(GifResult gifResult, Context context) {
        this.gifResult = gifResult;
        this.context = context;
    }

    public String getId() {
        return gifResult.id;
    }

    public String getGifUrl() {
        return gifResult.images.original.url;
    }

    public String getGifWidth() {
        return "Width " + gifResult.images.original.width;
    }

    public String getGifHeight() {
        return "Height " + gifResult.images.original.height;
    }

    public String getGifSize() {
        return String.format("Size %.2f Mb", convertToMb(gifResult.images.original.size));
    }

    private double convertToMb(int bytes) {
        return (double) bytes / (1024L * 1024L);
    }

    @BindingAdapter({"imageUrl"})
    public static void setImageUrl(ImageView view, String imageUrl) {
        Glide.with(view.getContext()).load(imageUrl).into(view);
    }

    public void onShareFabClicked(View view) {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, gifResult.images.original.url);
            shareIntent.setType("text/plain");
            context.startActivity(shareIntent);
        } catch (Exception e) {
            Snackbar.make(view, R.string.error_message, Snackbar.LENGTH_LONG).show();
        }
    }
}