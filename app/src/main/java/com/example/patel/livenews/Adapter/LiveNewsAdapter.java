package com.example.patel.livenews.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.patel.livenews.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by PATEL on 11/23/2017.
 */

public class LiveNewsAdapter extends ArrayAdapter<LiveNews> {


    public LiveNewsAdapter(@NonNull Context context, List<LiveNews> liveNews) {
        super(context, 0, liveNews);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        //checking if there is any existing view to reuse or make new 1
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.customlayout, parent, false
            );
        }

        //get news at given position in list
        LiveNews currentLiveNews = getItem(position);


        //finding textview with view id & display current title of news
        TextView titleView = (TextView) listItemView.findViewById(R.id.titleView);
        titleView.setText(currentLiveNews.getmTitle());

        //finding textview with view id & display current author of news
        TextView authorView = (TextView) listItemView.findViewById(R.id.authorView);
        authorView.setText(currentLiveNews.getmAuthor());

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.imageView);
        Picasso.with(getContext()).load(currentLiveNews.getmImageView()).into(imageView);


        return listItemView;
    }
}
