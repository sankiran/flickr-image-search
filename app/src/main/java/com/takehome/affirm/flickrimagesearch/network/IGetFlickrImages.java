package com.takehome.affirm.flickrimagesearch.network;

import com.takehome.affirm.flickrimagesearch.model.FlickrSearchResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IGetFlickrImages {
    String METHOD = "flickr.photos.search";
    String API_KEY = "675894853ae8ec6c242fa4c077bcf4a0";
    String EXTRAS = "url_s";
    String FORMAT = "json";
    String NO_JSON_CALLBACK = "1";

    @GET("/services/rest/?")
    Call<FlickrSearchResult> getImagesFromFlickr(@Query("method") String method, @Query("api_key") String apiKey, @Query("text") String text, @Query("extras") String  extras, @Query("format") String  format, @Query("nojsoncallback") String nojsoncallback);
}
