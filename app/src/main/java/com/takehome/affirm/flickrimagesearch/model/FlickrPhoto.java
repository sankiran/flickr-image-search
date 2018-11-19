package com.takehome.affirm.flickrimagesearch.model;

import android.support.annotation.NonNull;

/**
 * Model for Flickr photo object
 */
public class FlickrPhoto {
    private String url_s;

    public FlickrPhoto(@NonNull String url_s) {
        this.url_s = url_s;
    }

    /**
     * This method gives the url to the image for a particular photo
     *
     * @return photo url
     */
    public String getImageUrl() {
        return url_s;
    }
}
