package com.victorskurchik.gifsearcher.data;

import com.victorskurchik.gifsearcher.model.GifImage;
import com.victorskurchik.gifsearcher.model.GifResult;
import com.victorskurchik.gifsearcher.model.GifUrlSet;

import java.util.ArrayList;
import java.util.List;

public class FakeGifGeneratorAPI {
    private static final String FAKE_TITLE = "fake title";
    private static final String FAKE_ID = "fake_id123";
    private static final String FAKE_URL = "http://fake_url.com/";
    private static final int FAKE_NUMBER = 123;

    public static List<GifResult> getGifList() {
        List<GifResult> gifResults = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            gifResults.add(getGif());
        }
        return gifResults;
    }

    public static GifResult getGif() {
        GifResult gifResult = new GifResult();

        gifResult.title = FAKE_TITLE;
        gifResult.id = FAKE_ID;

        GifUrlSet gifUrlSet = new GifUrlSet();
        gifUrlSet.fixed_width = new GifImage();
        gifUrlSet.fixed_height = new GifImage();
        gifUrlSet.original = new GifImage();

        gifUrlSet.fixed_height.height = FAKE_NUMBER;
        gifUrlSet.fixed_height.width = FAKE_NUMBER;
        gifUrlSet.fixed_height.url = FAKE_URL;
        gifUrlSet.fixed_height.size = FAKE_NUMBER;

        gifUrlSet.fixed_width.height = FAKE_NUMBER;
        gifUrlSet.fixed_width.width = FAKE_NUMBER;
        gifUrlSet.fixed_width.url = FAKE_URL;
        gifUrlSet.fixed_width.size = FAKE_NUMBER;

        gifUrlSet.original.height = FAKE_NUMBER;
        gifUrlSet.original.width = FAKE_NUMBER;
        gifUrlSet.original.url = FAKE_URL;
        gifUrlSet.original.size = FAKE_NUMBER;

        gifResult.images = gifUrlSet;

        return gifResult;
    }
}
