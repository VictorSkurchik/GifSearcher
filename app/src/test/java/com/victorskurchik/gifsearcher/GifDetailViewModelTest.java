package com.victorskurchik.gifsearcher;

import android.test.mock.MockContext;

import com.victorskurchik.gifsearcher.data.FakeGifGeneratorAPI;
import com.victorskurchik.gifsearcher.model.GifResult;
import com.victorskurchik.gifsearcher.viewmodel.GifDetailViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.victorskurchik.gifsearcher.viewmodel.GifDetailViewModel.convertToMb;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class GifDetailViewModelTest {
    private GifDetailViewModel gifDetailViewModel;
    private GifResult gifResult;

    @Mock
    private MockContext mockContext;

    @Before
    public void setUpMainViewModelTest() {
        gifResult = FakeGifGeneratorAPI.getGif();
        gifDetailViewModel = new GifDetailViewModel(gifResult, mockContext);
    }

    @Test
    public void shouldGetGifId() throws Exception {
        assertEquals(gifResult.id, gifDetailViewModel.getId());
    }

    @Test
    public void shouldGetGifUrl() throws Exception {
        assertEquals(gifResult.images.fixed_height.url, gifDetailViewModel.getGifUrl());
    }

    @Test
    public void shouldGetGifHeight() throws Exception {
        assertEquals("Height " + gifResult.images.fixed_height.height, gifDetailViewModel.getGifHeight());
    }

    @Test
    public void shouldGetGifWidth() throws Exception {
        assertEquals("Width " + gifResult.images.fixed_height.height, gifDetailViewModel.getGifWidth());
    }

    @Test
    public void shouldConvertToMb() throws Exception {
        assertEquals(8.58306884765625, convertToMb(9_000_000),0.1d);
    }

    @Test
    public void shouldGetGifSize() throws Exception {
        assertEquals(String.format("Size %.2f Mb", convertToMb(gifResult.images.fixed_height.size)), gifDetailViewModel.getGifSize());
    }
}
