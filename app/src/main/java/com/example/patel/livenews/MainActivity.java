package com.example.patel.livenews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.patel.livenews.Adapter.LiveNews;
import com.example.patel.livenews.Adapter.LiveNewsAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private  static final String TECH_REQUEST_URL =
            "https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=0bd6ee1e25c04e1eaf3ab67459d8e684";


    private LiveNewsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView liveNews = (ListView)findViewById(R.id.main_listView);


        // Create a new adapter that takes an empty list of news as input
        mAdapter = new LiveNewsAdapter(this,new ArrayList<LiveNews>());

        liveNews.setAdapter(mAdapter);
    }
}
