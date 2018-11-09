package com.example.rkjc.news_app_2;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class JsonUtils {

    public static ArrayList<NewsItem> parseNews(String JSONString) {
        try {
            JSONObject json = new JSONObject(JSONString);
            JSONArray articles = json.getJSONArray("articles");
            ArrayList<NewsItem> items = new ArrayList<NewsItem>();
            for(int i = 0; i < articles.length(); i++){
                JSONObject itemJson = articles.getJSONObject(i);
                NewsItem item = new NewsItem(itemJson.getString("author"), itemJson.getString("title"), itemJson.getString("description"), itemJson.getString("url"), itemJson.getString("urlToImage"), itemJson.getString("publishedAt"));
                items.add(item);
            }
            return items;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}


