package com.takehome.affirm.flickrimagesearch.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.takehome.affirm.flickrimagesearch.R;
import com.takehome.affirm.flickrimagesearch.model.FlickrPhoto;

import java.util.List;

/**
 * This class handles the image data retrieved from Flickr, and facilitates loading them into
 * image views for each item in the recycler view
 */
public class FlickrImageAdapter extends RecyclerView.Adapter<FlickrImageAdapter.FlickrImageViewHolder> {
    private List<FlickrPhoto> mImages;
    private Context mContext;

    static class FlickrImageViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;

        FlickrImageViewHolder(@NonNull View listItemView) {
            super(listItemView);
            mImageView = listItemView.findViewById(R.id.flickr_image);
        }
    }

    FlickrImageAdapter(List<FlickrPhoto> searchResults, @NonNull Context context) {
        mImages = searchResults;
        mContext = context;
    }

    @NonNull
    @Override
    public FlickrImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_search_result, viewGroup, false);
        return new FlickrImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlickrImageViewHolder flickrImageViewHolder, int i) {
        setImageResourceWithTransformation(flickrImageViewHolder, i);

    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }

    private void setImageResourceWithTransformation(FlickrImageViewHolder viewHolder, int position) {
        int imageSize = (int) mContext.getResources().getDimension(R.dimen.image_size);
        // Loads the image from the result into the image view
        Picasso.get()
                .load(mImages.get(position).getImageUrl())
                .resize(imageSize, imageSize)
                .placeholder(R.drawable.ic_launcher_background)
                .into(viewHolder.mImageView);
    }
}
