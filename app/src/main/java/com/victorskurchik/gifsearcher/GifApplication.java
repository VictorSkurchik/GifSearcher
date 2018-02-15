package com.victorskurchik.gifsearcher;

import android.app.Application;
import android.content.Context;

import com.victorskurchik.gifsearcher.data.GifFactory;
import com.victorskurchik.gifsearcher.data.GifService;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class GifApplication extends Application {

    private GifService gifService;
    private Scheduler scheduler;

    private static GifApplication get(Context context) {
        return (GifApplication) context.getApplicationContext();
    }

    public static GifApplication create(Context context) {
        return GifApplication.get(context);
    }

    public GifService getGifService() {
        if (gifService == null) {
            gifService = GifFactory.create();
        }

        return gifService;
    }

    public Scheduler subscribeScheduler() {
        if (scheduler == null) {
            scheduler = Schedulers.io();
        }

        return scheduler;
    }

    public void setGifService(GifService gifService) {
        this.gifService = gifService;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }
}
