package com.victorskurchik.gifsearcher.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
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
    private List<GifResult> oldGifList;
    private Context context;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public GifViewModel(@NonNull Context context) {
        this.context = context;
        this.gifList = new ArrayList<>();
        this.oldGifList = new ArrayList<>();
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

        if (!isNetworkConnected()) {
            Snackbar.make(((Activity) context).getWindow().getDecorView(), R.string.no_inet_connection, Snackbar.LENGTH_LONG).show();
        }

        GifApplication gifApplication = GifApplication.create(context);
        GifService gifService = gifApplication.getGifService();

        Disposable disposable = gifService.fetchGifs(queryUrl)
                .subscribeOn(gifApplication.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GifResponse>() {
                    @Override
                    public void accept(GifResponse gifResponse) throws Exception {
                        if (gifResponse.getGifData().size() == 0) {
                            messageLabel.set(context.getString(R.string.no_matches_found));
                            gifLabel.set(View.VISIBLE);
                            gifRecycler.set(View.GONE);
                        } else {
                            changeGifsDataSet(gifResponse.getGifData());
                            gifProgress.set(View.GONE);
                            gifLabel.set(View.GONE);
                            gifRecycler.set(View.VISIBLE);
                            gifFab.set(View.VISIBLE);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        messageLabel.set(context.getString(R.string.error_message));
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
        oldGifList.addAll(gifList);
        gifList.clear();
        setChanged();
        notifyObservers();
    }

    public void revertGifsDataSet() {
        gifList.addAll(oldGifList);
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

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
}
