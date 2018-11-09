package com.example.rkjc.news_app_2;

import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    public static final String BASE_URL = "https://newsapi.org/v1/articles?source=the-next-web&sortBy=latest&apiKey=";
    static final String API_KEY = "6d4d5ebbef6e40508609ffa53fcc383d";
    public static URL buildURL() {
        Uri.Builder builder = new Uri.Builder();
        Uri uri = builder.scheme("https")
                .authority("newsapi.org")
                .appendPath("v1")
                .appendPath("articles")
                .appendQueryParameter("source", "the-next-web")
                .appendQueryParameter("sortBy", "latest")
                .appendQueryParameter("apiKey", API_KEY)
                .build();
        URL url = null;
        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Log.d("URL-x", url.toString());
        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            }
            else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    public abstract class NewsQueryTask extends AsyncTask<URL, String, String> {
        public abstract void onResponseReceived(String result);

        @Override
        protected String doInBackground(URL... urls) {
            try {
                return getResponseFromHttpUrl(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        }

        @Override
        public void onPostExecute(String result){
            onResponseReceived(result);
        }
    }
}
