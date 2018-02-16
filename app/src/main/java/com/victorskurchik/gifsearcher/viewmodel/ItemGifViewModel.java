package com.victorskurchik.gifsearcher.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.victorskurchik.gifsearcher.model.GifResult;
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

    public String getImageUrl() {
        return gifResult.images.fixed_height.url;
    }

    @BindingAdapter({"imageUrl", "progressbar"})
    public static void setImageUrl(final ImageView imageView, String url, final ProgressBar progressBar) {
        Glide.with(imageView.getContext()).load(url).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                progressBar.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                progressBar.setVisibility(View.GONE);
                return false;
            }
        }).into(imageView);
    }

    public void onItemClick(View view) {
        context.startActivity(GifDetailActivity.launchDetail(view.getContext(), gifResult));
    }

    public void setGifResult(GifResult gifResult) {
        this.gifResult = gifResult;
        notifyChange();
    }
}