package com.victorskurchik.gifsearcher.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.victorskurchik.gifsearcher.R;
import com.victorskurchik.gifsearcher.data.GifFactory;
import com.victorskurchik.gifsearcher.databinding.GifActivityBinding;
import com.victorskurchik.gifsearcher.viewmodel.GifViewModel;

import java.util.Observable;
import java.util.Observer;

public class GifActivity extends AppCompatActivity implements Observer {

    private GifActivityBinding gifActivityBinding;
    private GifViewModel gifViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDataBinding();
        setupListGifView(gifActivityBinding.listGif);
        setupObserver(gifViewModel);
    }

    private void initDataBinding() {
        gifActivityBinding = DataBindingUtil.setContentView(this, R.layout.gif_activity);
        gifViewModel = new GifViewModel(this);
        gifActivityBinding.setMainViewModel(gifViewModel);
    }

    private void setupListGifView(RecyclerView recyclerView) {
        GifAdapter adapter = new GifAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void setupObserver(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        gifViewModel.reset();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                gifViewModel.clearGifsDataSet();
                gifViewModel.fetchGifList(GifFactory.getSearchGifsQueryUrl(query));

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_search) {
//            gifViewModel.gifFab.set(View.GONE);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void update(Observable observable, Object data) {
        if (observable instanceof GifViewModel) {
            GifAdapter gifAdapter = (GifAdapter) gifActivityBinding.listGif.getAdapter();
            GifViewModel gifViewModel = (GifViewModel) observable;
            gifAdapter.setGifList(gifViewModel.getGifList());
        }
    }
}
