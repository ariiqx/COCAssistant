package com.videdesk.mobile.cocassistant.activity;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.videdesk.mobile.cocassistant.R;
import com.videdesk.mobile.cocassistant.adapter.ThemeAdapter;
import com.videdesk.mobile.cocassistant.adapter.TopicAdapter;
import com.videdesk.mobile.cocassistant.config.Value;
import com.videdesk.mobile.cocassistant.config.Videx;
import com.videdesk.mobile.cocassistant.models.Theme;
import com.videdesk.mobile.cocassistant.models.Topic;

import java.util.ArrayList;
import java.util.List;

public class TopicsActivity extends AppCompatActivity {

    private int topicCount;
    private TopicAdapter topicAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        Videx videx = new Videx(TopicsActivity.this);
        String theme_node = videx.getPref(Value.COLUMN_THEME_NODE);

        String head = getResources().getString(R.string.txt_topics);
        String[] theme_titles = Value.themeTitle;
        for(int s = 0; s < theme_titles.length; s++){
            int id = s + 1;
            if(id == Integer.parseInt(theme_node)) {
                head = theme_titles[s];
            }
        }
        ActionBar actionBar = getSupportActionBar();
        actionBar.setSubtitle("Select a topic");
        actionBar.setTitle(head);

        List<Topic> topicList = new ArrayList<>();
        topicAdapter = new TopicAdapter(TopicsActivity.this, topicList, theme_node);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(topicAdapter);
        topicList.clear();
        String[] topics = Value.topics;
        topicCount = topics.length;
        for(int s = 0; s < topics.length; s++){
            int id = s + 1;
            String node = "" + id;
            String title = topics[s];

            Topic topic  = new Topic(node, title);
            topicList.add(topic);
        }
        topicAdapter.notifyDataSetChanged();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        MenuItem search = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        search(searchView);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        switch (id) {
            case R.id.menu_about:
                startActivity(new Intent(TopicsActivity.this, AboutActivity.class));
                return true;

            case R.id.menu_share:
                shareCOC();
                return true;

            case R.id.menu_settings:
                //startActivity(new Intent(HomeActivity.this, NationsActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void search(SearchView searchView) {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if(topicCount > 0) {
                    topicAdapter.getFilter().filter(newText);
                }
                return true;
            }
        });
    }

    private void shareCOC(){
        String title = "Share With";
        String body = getResources().getString(R.string.txt_share_msg);
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, body);
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, title));
    }
}
