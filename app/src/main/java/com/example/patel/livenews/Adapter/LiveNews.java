package com.example.patel.livenews.Adapter;

/**
 * Created by PATEL on 11/23/2017.
 */

public class LiveNews {

    String mTitle;
    String mAuthor;
    String mImageView;

    public LiveNews(String mTitle, String mAuthor, String mImageView) {
        this.mTitle = mTitle;
        this.mAuthor = mAuthor;
        this.mImageView = mImageView;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public String getmImageView() {
        return mImageView;
    }
}
