package com.example.patel.livenews;

import android.text.TextUtils;
import android.util.Log;

import com.example.patel.livenews.Adapter.LiveNews;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PATEL on 11/23/2017.
 */

public class QueryUtils {

    private static final String LOG_TAG = QueryUtils.class.getSimpleName();


    //constructor
    public QueryUtils() {
    }

    //requesting live news & returns list of News Objects
    public static List<LiveNews> fetchLiveNewsData(String requestUrl) {

        //create url object
        URL url = createUrl(requestUrl);

        //perform http request to url and receive json data
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            System.out.println("ERROR");
        }

        //extract relevant fields from json reponse and creat a list of news
        List<LiveNews> liveNewsList = extractFeatureFromJson(jsonResponse);
        return liveNewsList;
    }



    //return new url Object from given string Url
    private static URL createUrl(String requestUrl) {
        URL url = null;
        try {
            url = new URL(requestUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        //if url is null,then return early;
        if (url == null) {
            return jsonResponse;
        }

        // If the request was successful (response code 200),
        // then read the input stream and parse the response.


        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            //If the request was successful(Response Code 200),
            //then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "ERROR response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    //Covert inputStream into a string which contains the
    //whole Json response from the server
    private static String readFromStream(InputStream inputStream) throws IOException {

        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    //return list of objects that has been built up from
    //parsing the given Json response
    private static List<LiveNews> extractFeatureFromJson(String jsonResponse) {

        if (TextUtils.isEmpty(jsonResponse)){
            return null;
        }

        //Create new ArrayList that we can start adding News to
        List<LiveNews> liveNews = new ArrayList<>();

        try{

            //create JSONObject from the Json response string
            JSONObject baseJsonObject = new JSONObject(jsonResponse);

            //Extract JSONArray associated with the key called "articles"
            JSONArray newsArray = baseJsonObject.getJSONArray("articles");

            //for each news in newsarray, create object
            for (int i = 0; i <newsArray.length() ; i++) {

                JSONObject currentLiveNews = newsArray.getJSONObject(i);

                String titleString =  currentLiveNews.getString("title");
                String authors = currentLiveNews.getString("description");
                String image = currentLiveNews.getString("urlToImage");

                LiveNews news = new LiveNews(titleString,authors,image);
                liveNews.add(news);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return liveNews;
    }


}
