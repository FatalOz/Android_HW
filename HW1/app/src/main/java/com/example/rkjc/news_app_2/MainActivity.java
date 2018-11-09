package com.example.rkjc.news_app_2;

import android.content.Context;
import android.net.Network;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;

import static com.example.rkjc.news_app_2.NetworkUtils.buildURL;


public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public ArrayList<NewsItem> items;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_search:
                new NetworkUtils().new NewsQueryTask() {
                    @Override
                    public void onResponseReceived(String result) {
                        items.clear();
                        items.addAll(JsonUtils.parseNews(result));
                        mAdapter.notifyDataSetChanged();
                    }
                }.execute(buildURL());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.news_recyclerview);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        items = new ArrayList<NewsItem>();
        mAdapter = new NewsAdapter(items, this);
        mRecyclerView.setAdapter(mAdapter);
       /* MenuItem refresh = (MenuItem) findViewById(R.id.get_news);
        refresh.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                new NetworkUtils().new NewsQueryTask() {
                    @Override
                    public void onResponseReceived(String result) {
                       items.clear();
                       items.addAll(JsonUtils.parseNews(result));
                       mAdapter.notifyDataSetChanged();
                    }
                }.execute(buildURL());
                return true;
            }
        }); */
    }
    public interface ClientIF {

        public void onResponseReceived(String result);

    }
}
