package com.videdesk.mobile.cocassistant.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.videdesk.mobile.cocassistant.R;
import com.videdesk.mobile.cocassistant.adapter.ChapAdapter;
import com.videdesk.mobile.cocassistant.config.Videx;
import com.videdesk.mobile.cocassistant.models.Chap;

import java.util.ArrayList;
import java.util.List;

public class ChapActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Videx videx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chap);

        videx = new Videx(ChapActivity.this);

        recyclerView = findViewById(R.id.recycler_view);
    }

    private void loadChaps(){
        List<Chap> chapList = new ArrayList<>();
        ChapAdapter chapAdapter = new ChapAdapter(ChapActivity.this, chapList);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 5);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new Videx.GridSpacingItemDecoration(5, videx.dpToPx(1), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(chapAdapter);
        for(int i = 1; i < 151; i++){
            Chap chap = new Chap(Videx.getNode(), "" + i);
            chapList.add(chap);
        }
        chapAdapter.notifyDataSetChanged();
    }
}
