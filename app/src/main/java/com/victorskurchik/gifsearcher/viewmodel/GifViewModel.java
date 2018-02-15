package com.victorskurchik.gifsearcher.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.view.View;

import com.victorskurchik.gifsearcher.GifApplication;
import com.victorskurchik.gifsearcher.R;
import com.victorskurchik.gifsearcher.data.GifFactory;
import com.victorskurchik.gifsearcher.data.GifResponse;
import com.victorskurchik.gifsearcher.data.GifService;
import com.victorskurchik.gifsearcher.model.GifResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class GifViewModel extends Observable {
    public ObservableInt gifProgress;
    public ObservableInt gifRecycler;
    public ObservableInt gifLabel;
    public ObservableInt gifFab;
    public ObservableField<String> messageLabel;

    private List<GifResult> gifList;
    private Context context;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public GifViewModel(@NonNull Context context) {
        this.context = context;
        this.gifList = new ArrayList<>();
        gifProgress = new ObservableInt(View.GONE);
        gifRecycler = new ObservableInt(View.GONE);
        gifLabel = new ObservableInt(View.VISIBLE);
        gifFab = new ObservableInt(View.VISIBLE);
        messageLabel = new ObservableField<>(context.getString(R.string.default_loading_gif));
    }

    public void onClickFabLoad(View view) {
        initializeViews();
        fetchGifList(GifFactory.getTrendingGifsQueryUrl());
    }

    public void initializeViews() {
        gifLabel.set(View.GONE);
        gifRecycler.set(View.GONE);
        gifProgress.set(View.VISIBLE);
        gifFab.set(View.GONE);
    }

    public void fetchGifList(String queryUrl) {

        GifApplication gifApplication = GifApplication.create(context);
        GifService gifService = gifApplication.getGifService();

        Disposable disposable = gifService.fetchGifs(queryUrl)
                .subscribeOn(gifApplication.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GifResponse>() {
                    @Override
                    public void accept(GifResponse gifResponse) throws Exception {
                        changeGifsDataSet(gifResponse.getGifData());
                        gifProgress.set(View.GONE);
                        gifLabel.set(View.GONE);
                        gifRecycler.set(View.VISIBLE);
                        gifFab.set(View.VISIBLE);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        messageLabel.set(context.getString(R.string.error_loading_gif));
                        gifProgress.set(View.GONE);
                        gifLabel.set(View.VISIBLE);
                        gifRecycler.set(View.GONE);
                        gifFab.set(View.VISIBLE);
                    }
                });

        compositeDisposable.add(disposable);
    }

    private void changeGifsDataSet(List<GifResult> gifResults) {
        gifList.addAll(gifResults);
        setChanged();
        notifyObservers();
    }

    public void clearGifsDataSet() {
        gifList.clear();
        setChanged();
        notifyObservers();
    }

    public List<GifResult> getGifList() {
        return gifList;
    }

    private void unSubscribeFromObservable() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    public void reset() {
        unSubscribeFromObservable();
        compositeDisposable = null;
        context = null;
    }

}
