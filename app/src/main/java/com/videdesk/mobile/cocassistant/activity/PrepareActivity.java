package com.videdesk.mobile.cocassistant.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.videdesk.mobile.cocassistant.R;
import com.videdesk.mobile.cocassistant.models.Book;
import com.videdesk.mobile.cocassistant.models.Verse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PrepareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepare);
    }

    private void checkBibles(){

    }

    private void makeVerses(){
        try {
            InputStreamReader is = new InputStreamReader(getAssets().open("bible_asv.csv"));
            BufferedReader reader = new BufferedReader(is);
            reader.readLine();
            String line;
            StringTokenizer st;
            int n = 1;
            String bible = "1";
            while ((line = reader.readLine()) != null) {
                st = new StringTokenizer(line, ",");
                Verse verse = new Verse();
                verse.setNode("" + n);
                verse.setCode(st.nextToken().replace("\"", ""));
                verse.setBible_node(bible);
                verse.setBook_node(st.nextToken().replace("\"", ""));
                verse.setChapter_node(st.nextToken().replace("\"", ""));
                verse.setTitle(st.nextToken().replace("\"", ""));
                verse.setDetails(st.nextToken().replace("\"", ""));
                //verseList.add(verse);
                n++;
            }

        } catch (IOException e) {

            e.printStackTrace();
        }
    }


}
