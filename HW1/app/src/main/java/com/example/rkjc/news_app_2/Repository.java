package com.example.rkjc.news_app_2;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Repository {

    private NewsItemDao mNewsItemDao;
    private LiveData<List<NewsItem>> mAllNewsItems;

    Repository(Application application) {
        NewsItemDatabase db = NewsItemDatabase.getDatabase(application);
        mNewsItemDao = db.NewsItemDao();
        mAllNewsItems = mNewsItemDao.loadAllNewsItems();
    }

    LiveData<List<NewsItem>> getAllNewsItems() {
        return mAllNewsItems;
    }


    public void sync (){
        new syncTask(mNewsItemDao).execute();
    }

    private static class syncTask extends AsyncTask<Void, Void, Void> {

        private NewsItemDao mAsyncTaskDao;

        syncTask(NewsItemDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... params) {
            ArrayList<NewsItem> news;

            mAsyncTaskDao.clearAll();
            try {
                news = JsonUtils.parseNews(NetworkUtils.getResponseFromHttpUrl(NetworkUtils.buildURL()));
                mAsyncTaskDao.insert(news);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}

