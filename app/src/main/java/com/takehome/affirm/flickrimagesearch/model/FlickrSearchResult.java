package com.takehome.affirm.flickrimagesearch.model;

import android.support.annotation.NonNull;

/**
 * Base object for result from Flickr search
 */
public class FlickrSearchResult {
    private FlickrPhotos photos;
    private String stat;

    public FlickrSearchResult(FlickrPhotos photos, @NonNull String status) {
        this.photos = photos;
        this.stat = status;
    }

    /**
     * This method returns the object from search result that contains the list of photos
     *
     * @return Object that contains list of photos
     */
    public FlickrPhotos getPhotos() {
        return photos;
    }

    /**
     * This method returns the Flickr photo search network call result status
     *
     * @return status of result
     */
    @NonNull
    public String getStat() {
        return stat;
    }
}
