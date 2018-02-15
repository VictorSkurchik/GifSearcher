package com.victorskurchik.gifsearcher.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.victorskurchik.gifsearcher.R;
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
//        setSupportActionBar(gifActivityBinding.toolbar);
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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_search) {
            startSearchActionView();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void startSearchActionView() {
//        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(GifFactory.PROJECT_URL)));
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
