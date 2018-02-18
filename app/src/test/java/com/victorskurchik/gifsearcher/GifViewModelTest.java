package com.victorskurchik.gifsearcher;

import android.test.mock.MockContext;
import android.view.View;

import com.victorskurchik.gifsearcher.data.FakeGifGeneratorAPI;
import com.victorskurchik.gifsearcher.data.GifService;
import com.victorskurchik.gifsearcher.model.GifResult;
import com.victorskurchik.gifsearcher.viewmodel.GifViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import io.reactivex.Observable;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class GifViewModelTest {

    private static final String URL_TEST = "https://api.giphy.com/v1/gifs/trending?api_key=1sjNHhsOqDJ775HGPP7SzXVxpRyf40V3&limit=10&rating=G";

    @Mock
    private GifService gifService;
    @Mock
    private MockContext mockContext;

    private GifViewModel gifViewModel;

    @Before
    public void setUpMainViewModelTest() {
        gifViewModel = new GifViewModel(mockContext);
    }

    @Test
    public void simulateGivenTheUserCallListFromApi() throws Exception {
        List<GifResult> gifList = FakeGifGeneratorAPI.getGifList();
        doReturn(Observable.just(gifList)).when(gifService).fetchGifs(URL_TEST);
    }

    @Test
    public void ensureTheViewsAreInitializedCorrectly() throws Exception {
        gifViewModel.initializeViews();
        assertEquals(View.GONE, gifViewModel.gifLabel.get());
        assertEquals(View.GONE, gifViewModel.gifRecycler.get());
        assertEquals(View.VISIBLE, gifViewModel.gifProgress.get());
        assertEquals(View.GONE, gifViewModel.gifFab.get());
    }
}