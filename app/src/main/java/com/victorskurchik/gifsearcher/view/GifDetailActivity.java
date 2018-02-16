package com.victorskurchik.gifsearcher.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.victorskurchik.gifsearcher.R;
import com.victorskurchik.gifsearcher.databinding.GifDetailActivityBinding;
import com.victorskurchik.gifsearcher.model.GifResult;
import com.victorskurchik.gifsearcher.viewmodel.GifDetailViewModel;

public class GifDetailActivity extends AppCompatActivity {

    private static final String EXTRA_GIF = "EXTRA_GIF";

    private GifDetailActivityBinding gifDetailActivityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gifDetailActivityBinding =
                DataBindingUtil.setContentView(this, R.layout.gif_detail_activity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });
        displayHomeAsUpEnabled();
        getExtrasFromIntent();
    }

    public static Intent launchDetail(Context context, GifResult gifResult) {
        Intent intent = new Intent(context, GifDetailActivity.class);
        intent.putExtra(EXTRA_GIF, gifResult);
        return intent;
    }

    private void displayHomeAsUpEnabled() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void getExtrasFromIntent() {
        GifResult gifResult = (GifResult) getIntent().getSerializableExtra(EXTRA_GIF);
        GifDetailViewModel gifDetailViewModel = new GifDetailViewModel(gifResult, getApplicationContext());
        gifDetailActivityBinding.setGifDetailViewModel(gifDetailViewModel);
        setTitle(gifResult.title);
    }
}
