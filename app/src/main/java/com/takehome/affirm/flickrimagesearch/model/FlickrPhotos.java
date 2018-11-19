package com.takehome.affirm.flickrimagesearch.model;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Model that contains the list of photos from Flickr search
 */
public class FlickrPhotos {
    private List<FlickrPhoto> photo;

    public FlickrPhotos(@NonNull List<FlickrPhoto> photoList) {
        this.photo = photoList;
    }

    /**
     * This method returns the list of photos from Flickr
     * @return list of photos
     */
    public List<FlickrPhoto> getPhotos() {
        return photo;
    }
}
