package com.example.rkjc.news_app_2;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class NewsItemViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<NewsItem>> news;

    public NewsItemViewModel(Application application){
        super(application);
        repository = new Repository(application);
        news = repository.getAllNewsItems();
    }

    public LiveData<List<NewsItem>> getNews(){
        return news;
    }

    public void sync(){
        repository.sync();
    }
}
