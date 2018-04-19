package com.videdesk.mobile.cocassistant.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.videdesk.mobile.cocassistant.R;

public class VersesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verses);


        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
    }
}
