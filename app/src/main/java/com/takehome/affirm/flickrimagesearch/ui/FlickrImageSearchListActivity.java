package com.takehome.affirm.flickrimagesearch.ui;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

import com.takehome.affirm.flickrimagesearch.R;
import com.takehome.affirm.flickrimagesearch.model.FlickrPhoto;
import com.takehome.affirm.flickrimagesearch.model.FlickrPhotos;
import com.takehome.affirm.flickrimagesearch.model.FlickrSearchResult;
import com.takehome.affirm.flickrimagesearch.network.FlickrImageClientInstance;
import com.takehome.affirm.flickrimagesearch.network.IGetFlickrImages;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This activity handles the search, and also displays the result
 */
public class FlickrImageSearchListActivity extends AppCompatActivity {
    private static final String STATUS_OK = "ok";
    private SearchView mSearchView;
    private String mQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flickr_image_search_list);

        Toolbar searchBar = findViewById(R.id.toolbar);
        setSupportActionBar(searchBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        handleIntent(getIntent());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        mSearchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setQueryHint(getString(R.string.search_bar_hint));

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(getIntent());
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            if (!TextUtils.isEmpty(query)) {
                // Make network call to fetch images
                mQuery = query;
                IGetFlickrImages images = FlickrImageClientInstance.getRetrofitInstance().create(IGetFlickrImages.class);
                Call<FlickrSearchResult> call = images.getImagesFromFlickr(IGetFlickrImages.METHOD, IGetFlickrImages.API_KEY,
                        mQuery, IGetFlickrImages.EXTRAS, IGetFlickrImages.FORMAT, IGetFlickrImages.NO_JSON_CALLBACK);
                call.enqueue(new Callback<FlickrSearchResult>() {
                    @Override
                    public void onResponse(Call<FlickrSearchResult> call, Response<FlickrSearchResult> response) {
                        setupImageList(response.body());
                    }

                    @Override
                    public void onFailure(Call<FlickrSearchResult> call, Throwable t) {
                        Toast.makeText(FlickrImageSearchListActivity.this, getString(R.string.error_service_failure), Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
    }

    private void setupImageList(@NonNull FlickrSearchResult result) {
        RecyclerView recyclerView = findViewById(R.id.image_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        String status = result.getStat();
        if (STATUS_OK.equalsIgnoreCase(status)) {
            FlickrPhotos photos = result.getPhotos();
            List<FlickrPhoto> photoList = photos.getPhotos();
            FlickrImageAdapter adapter = new FlickrImageAdapter(photoList, this);
            recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
            recyclerView.setAdapter(adapter);
            mSearchView.setQuery(mQuery, false);
        }
    }
}
