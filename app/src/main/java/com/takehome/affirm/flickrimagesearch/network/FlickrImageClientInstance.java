package com.takehome.affirm.flickrimagesearch.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This class handles construction of the base url, and creates a Retrofit instance
 * which is used to make network calls
 */
public class FlickrImageClientInstance {
    private static Retrofit sRetrofit;
    private static final String RESTAURANT_LIST_BASE_URL = "https://api.flickr.com";

    public static Retrofit getRetrofitInstance() {
        if (null == sRetrofit) {
            sRetrofit = new Retrofit.Builder()
                    .baseUrl(RESTAURANT_LIST_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return sRetrofit;
    }
}
