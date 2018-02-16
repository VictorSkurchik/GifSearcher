package com.victorskurchik.gifsearcher.view;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.victorskurchik.gifsearcher.R;
import com.victorskurchik.gifsearcher.databinding.ItemGifBinding;
import com.victorskurchik.gifsearcher.model.GifResult;
import com.victorskurchik.gifsearcher.viewmodel.ItemGifViewModel;

import java.util.Collections;
import java.util.List;

public class GifAdapter extends RecyclerView.Adapter<GifAdapter.GifAdapterViewHolder> {

    private List<GifResult> gifList;

    GifAdapter() {
        this.gifList = Collections.emptyList();
    }

    @Override
    public GifAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemGifBinding itemGifBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_gif,
                        parent, false);
        return new GifAdapterViewHolder(itemGifBinding);
    }

    @Override
    public void onBindViewHolder(GifAdapterViewHolder holder, int position) {
        holder.bindGif(gifList.get(position));
    }

    @Override
    public int getItemCount() {
        return gifList.size();
    }

    void setGifList(List<GifResult> gifList) {
        this.gifList = gifList;
        notifyDataSetChanged();
    }

    static class GifAdapterViewHolder extends RecyclerView.ViewHolder {
        ItemGifBinding mItemGifBinding;

        GifAdapterViewHolder(ItemGifBinding itemGifBinding) {
            super(itemGifBinding.itemGif);
            this.mItemGifBinding = itemGifBinding;
        }

        void bindGif(GifResult gifResult) {
            if (mItemGifBinding.getGifViewModel() == null) {
                mItemGifBinding.setGifViewModel(
                        new ItemGifViewModel(gifResult, itemView.getContext()));
            } else {
                mItemGifBinding.getGifViewModel().setGifResult(gifResult);
            }
        }
    }
}