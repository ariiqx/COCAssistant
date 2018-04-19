package com.videdesk.mobile.cocassistant.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TextInputEditText;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.videdesk.mobile.cocassistant.R;
import com.videdesk.mobile.cocassistant.adapter.BookAdapter;
import com.videdesk.mobile.cocassistant.adapter.ChapAdapter;
import com.videdesk.mobile.cocassistant.adapter.VerseAdapter;
import com.videdesk.mobile.cocassistant.config.Value;
import com.videdesk.mobile.cocassistant.config.Videx;
import com.videdesk.mobile.cocassistant.models.Book;
import com.videdesk.mobile.cocassistant.models.Chap;
import com.videdesk.mobile.cocassistant.models.Verse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BibleActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private BookAdapter bookAdapter;
    private ChapAdapter chapAdapter;
    private VerseAdapter verseAdapter;

    private int countBooks, countChaps, countVerses, first, last;
    private Videx videx;

    private RecyclerView recyclerView;

    private int half, genre;
    private ActionBar actionBar;
    private String bible_page, bible_face;
    private EditText txtSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bible);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();

        videx = new Videx(BibleActivity.this);
        bible_page = videx.getPref(Value.COLUMN_BIBLE_PAGE);
        bible_face = Value.KEY_HALF;

        half = first = last = 1;
        countBooks = countChaps = countVerses = 0;

        // Set default bible to KJV - King James Version
        actionBar.setSubtitle(getResources().getString(R.string.txt_bible_kjv));
        videx.setPref(Value.COLUMN_BIBLE_NODE, Value.KEY_BIBLE_KJV);

        recyclerView = findViewById(R.id.recycler_view);

        loadPage();

        txtSearch = findViewById(R.id.advance_search);

        ImageView btnSearch = findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(txtSearch.getText())) {
                    Toast.makeText(getApplicationContext(), "Enter search key!", Toast.LENGTH_SHORT).show();
                    txtSearch.setError("Enter search key!");
                }
            }
        });

        /*
        FloatingActionButton fabBack = findViewById(R.id.fab_back);
        fabBack.setBackgroundColor(videx.getColor("500"));
        fabBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (bible_page){
                    case Value.KEY_BIBLE_BOOKS:
                        startActivity(new Intent(BibleActivity.this, HomeActivity.class));
                        finish();
                        break;
                    case Value.KEY_BIBLE_CHAPS:
                        videx.setPref(Value.COLUMN_BIBLE_PAGE, Value.KEY_BIBLE_BOOKS);
                        startActivity(getIntent());
                        break;
                    case Value.KEY_BIBLE_VERSES:
                        videx.setPref(Value.COLUMN_BIBLE_PAGE, Value.KEY_BIBLE_CHAPS);
                        startActivity(getIntent());
                        break;
                }

            }
        });
        */

        com.github.clans.fab.FloatingActionMenu fabNavi = findViewById(R.id.fab_navi);
        fabNavi.setMenuButtonColorNormal(videx.getColor("500"));

        // Fab Next.
        com.github.clans.fab.FloatingActionButton fabNext = findViewById(R.id.fab_next);
        fabNext.setColorNormal(videx.getColor("500"));
        fabNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               moveNext();
            }
        });

        // Fab Prev.
        com.github.clans.fab.FloatingActionButton fabPrev = findViewById(R.id.fab_prev);
        fabPrev.setColorNormal(videx.getColor("500"));
        fabPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveBack();
            }
        });

        // Fab Beck.
        com.github.clans.fab.FloatingActionButton fabBack = findViewById(R.id.fab_back);
        fabBack.setColorNormal(videx.getColor("500"));
        fabBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BibleActivity.this, HomeActivity.class));
                finish();
            }
        });

        com.github.clans.fab.FloatingActionMenu fabMenu = findViewById(R.id.fab_menu);
        fabMenu.setMenuButtonColorNormal(videx.getColor("500"));

        // Fab New.
        com.github.clans.fab.FloatingActionButton fabNT = findViewById(R.id.fab_nt);
        fabNT.setColorNormal(videx.getColor("500"));
        fabNT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionBar.setTitle(getResources().getString(R.string.txt_new_testament));
                videx.setPref(Value.COLUMN_BIBLE_PAGE, Value.KEY_BIBLE_BOOKS);
                bible_face = Value.KEY_HALF;
                half = 2;
                loadBooks();
            }
        });

        // Fab Old.
        com.github.clans.fab.FloatingActionButton fabOT = findViewById(R.id.fab_ot);
        fabOT.setColorNormal(videx.getColor("500"));
        fabOT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionBar.setTitle(getResources().getString(R.string.txt_old_testament));
                videx.setPref(Value.COLUMN_BIBLE_PAGE, Value.KEY_BIBLE_BOOKS);
                bible_face = Value.KEY_HALF;
                half = 1;
                loadBooks();
            }
        });

        // Fab All.
        com.github.clans.fab.FloatingActionButton fabAll = findViewById(R.id.fab_all);
        fabAll.setColorNormal(videx.getColor("500"));
        fabAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionBar.setTitle(getResources().getString(R.string.txt_holy_bible));
                videx.setPref(Value.COLUMN_BIBLE_PAGE, Value.KEY_BIBLE_BOOKS);
                bible_face = Value.KEY_HALF;
                half = 0;
                loadBooks();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //  WHEN THE CURRENT PAGE IS...!
        switch (bible_page){
            case Value.KEY_BIBLE_BOOKS:
                fabPrev.setVisibility(View.GONE);
                fabNext.setVisibility(View.GONE);
                break;
            case Value.KEY_BIBLE_CHAPS:
                last = 66;
                fabPrev.setLabelText("Prev Book");
                fabNext.setLabelText("Next Book");
                break;
            case Value.KEY_BIBLE_VERSES:
                fabPrev.setLabelText("Prev Chap");
                fabNext.setLabelText("Next Chap");
                break;
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bible, menu);
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
        bible_face = Value.KEY_GENRE;
        videx.setPref(Value.COLUMN_BIBLE_PAGE, Value.KEY_BIBLE_BOOKS);
        switch (id) {
            case R.id.menu_law:
                genre = 1;
                bible_page = Value.KEY_BIBLE_BOOKS;
                loadBooks();
                return true;

            case R.id.menu_histor:
                genre = 2;
                bible_page = Value.KEY_BIBLE_BOOKS;
                loadBooks();
                return true;

            case R.id.menu_wisdom:
                genre = 3;
                bible_page = Value.KEY_BIBLE_BOOKS;
                loadBooks();
                return true;

            case R.id.menu_prophets:
                genre = 4;
                bible_page = Value.KEY_BIBLE_BOOKS;
                loadBooks();
                return true;

            case R.id.menu_gospels:
                genre = 5;
                bible_page = Value.KEY_BIBLE_BOOKS;
                loadBooks();
                return true;

            case R.id.menu_acts:
                genre = 6;
                bible_page = Value.KEY_BIBLE_BOOKS;
                loadBooks();
                return true;

            case R.id.menu_epistles:
                genre = 7;
                bible_page = Value.KEY_BIBLE_BOOKS;
                loadBooks();
                return true;

            case R.id.menu_apocalyptic:
                genre = 8;
                bible_page = Value.KEY_BIBLE_BOOKS;
                loadBooks();
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
                switch (bible_page){
                    case Value.KEY_BIBLE_BOOKS:
                        if(countBooks > 0) {
                            bookAdapter.getFilter().filter(newText);
                        }
                        return true;
                    case Value.KEY_BIBLE_CHAPS:
                        if(countChaps > 0) {
                            chapAdapter.getFilter().filter(newText);
                        }
                        return true;
                    case Value.KEY_BIBLE_VERSES:
                        if(countVerses > 0) {
                            verseAdapter.getFilter().filter(newText);
                        }
                        return true;
                }

                return true;
            }
        });
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        String bv = Value.KEY_BIBLE_KJV;
        switch (id){
            case R.id.nav_asv:
                actionBar.setSubtitle(getResources().getString(R.string.txt_bible_asv));
                bv = Value.KEY_BIBLE_ASV;
                break;
            case R.id.nav_bbe:
                actionBar.setSubtitle(getResources().getString(R.string.txt_bible_bbe));
                bv = Value.KEY_BIBLE_BBE;
                break;
            case R.id.nav_deb:
                actionBar.setSubtitle(getResources().getString(R.string.txt_bible_deb));
                bv = Value.KEY_BIBLE_DEB;
                break;
            case R.id.nav_kjv:
                actionBar.setSubtitle(getResources().getString(R.string.txt_bible_kjv));
                bv = Value.KEY_BIBLE_KJV;
                break;
            case R.id.nav_wbt:
                actionBar.setSubtitle(getResources().getString(R.string.txt_bible_wbt));
                bv = Value.KEY_BIBLE_WBT;
                break;
            case R.id.nav_web:
                actionBar.setSubtitle(getResources().getString(R.string.txt_bible_web));
                bv = Value.KEY_BIBLE_WEB;
                break;
            case R.id.nav_ylt:
                actionBar.setSubtitle(getResources().getString(R.string.txt_bible_ylt));
                bv = Value.KEY_BIBLE_YLT;
                break;
        }
        videx.setPref(Value.COLUMN_BIBLE_NODE, bv);
        videx.setPref(Value.COLUMN_BIBLE_PAGE, Value.KEY_BIBLE_BOOKS);
        loadPage();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void loadPage(){
        switch (bible_page){
            case Value.KEY_BIBLE_BOOKS:
                loadBooks();
                break;
            case Value.KEY_BIBLE_CHAPS:
                loadChaps();
                break;
            case Value.KEY_BIBLE_VERSES:
                checkVerses();
                break;
        }
    }

    private void loadBooks(){
        List<Book> bookList = new ArrayList<>();
        bookAdapter = new BookAdapter(BibleActivity.this, bookList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(bookAdapter);
        bookList.clear();

        String[] books = Value.books;
        for(int s = 0; s < books.length; s++){
            int id = s + 1;
            String node = "" + id;
            String[] expo = books[s].split(",");
            String bkHalf = expo[0];
            String bkGenre = expo[1];
            String bkChapters = expo[2];
            String bkTitle = expo[3];

            Book book  = new Book(node, bkTitle, bkHalf, bkGenre, bkChapters);
            if(half > 0) {
                switch (bible_face) {
                    case Value.KEY_GENRE:
                        if (Integer.parseInt(bkGenre) == genre) {
                            bookList.add(book);
                        }
                        break;
                    case Value.KEY_HALF:
                        if (Integer.parseInt(bkHalf) == half) {
                            bookList.add(book);
                        }
                        break;
                }
            }else{
                bookList.add(book);
            }

        }
        // add 2 empty rows to fill bottom space created by Advance search
        for(int j = 0; j < 2; j++){
            Book empty  = new Book("0", "", "", "", "");
            bookList.add(empty);
        }
        countBooks = 66;
        bookAdapter.notifyDataSetChanged();

    }

    private void loadChaps(){
        String book_id = videx.getPref(Value.COLUMN_BOOK_NODE);
        String[] books = Value.books;
        int book_ch = 1;
        String book_title = getResources().getString(R.string.txt_holy_bible);
        for(int s = 0; s < books.length; s++) {
            int id = s + 1;
            if(id == Integer.parseInt(book_id)) {
                String[] expo = books[s].split(",");
                book_ch += Integer.parseInt(expo[2]);
                book_title = expo[3];
            }
        }
        countChaps = book_ch;

        actionBar.setTitle(book_title);
        List<Chap> chapList = new ArrayList<>();
        chapAdapter = new ChapAdapter(BibleActivity.this, chapList);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 5);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(chapAdapter);
        for(int i = 1; i < book_ch; i++){
            String ch = "" + i;
            Chap chap = new Chap(ch, ch);
            chapList.add(chap);
        }
        // add 2 empty rows to fill bottom space created by Advance search
        for(int j = 0; j < 6; j++){
            Chap empty  = new Chap(null, null);
            chapList.add(empty);
        }
        chapAdapter.notifyDataSetChanged();
    }

    private void checkVerses(){
        String bible_id = videx.getPref(Value.COLUMN_BIBLE_NODE);

        String book_id = videx.getPref(Value.COLUMN_BOOK_NODE);
        String[] books = Value.books;
        String book_title = getResources().getString(R.string.txt_holy_bible);
        for(int s = 0; s < books.length; s++) {
            int id = s + 1;
            if(id == Integer.parseInt(book_id)) {
                String[] expo = books[s].split(",");
                book_title = expo[3];
            }
        }
        countVerses = books.length;

        String chap_id = videx.getPref(Value.COLUMN_CHAPTER_NODE);
        String pg = book_title + " " + chap_id;
        actionBar.setTitle(pg);

        List<Verse> verseList = new ArrayList<>();
        verseAdapter = new VerseAdapter(BibleActivity.this, verseList, "0");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(verseAdapter);
        verseList.clear();

        String bible_file = "bible_" + bible_id + ".csv";

        try {
            InputStreamReader is = new InputStreamReader(getAssets().open(bible_file));
            BufferedReader reader = new BufferedReader(is);
            reader.readLine();
            String line;
            StringTokenizer st;
            int n = 1;
            //String bible = "1";
            while ((line = reader.readLine()) != null) {
                st = new StringTokenizer(line, ",");
                int total = st.countTokens();
                Verse verse = new Verse();
                verse.setNode("" + n);
                verse.setCode(st.nextToken().replace("\"", ""));
                verse.setBible_node(bible_id);
                verse.setBook_node(st.nextToken().replace("\"", ""));
                verse.setChapter_node(st.nextToken().replace("\"", ""));
                verse.setTitle(st.nextToken().replace("\"", ""));
                int cbodi = total - 4;
                StringBuilder det = new StringBuilder();
                for(int i = 0; i < cbodi; i++){
                    String d = st.nextToken().replace("\"", "") + ", ";
                    det.append(d.substring(0, d.length()-2));
                }
                verse.setDetails(det.toString()) ;
                if(verse.getBook_node().equals(book_id) && verse.getChapter_node().equals(chap_id)){
                    verseList.add(verse);
                }
                //verseList.add(verse);
                n++;
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
        // add 2 empty rows to fill bottom space created by Advance search
        for(int j = 0; j < 2; j++){
            Verse empty  = new Verse("0", "", "", "", "", "", "");
            verseList.add(empty);
        }
        verseAdapter.notifyDataSetChanged();
    }

    private void moveBack(){
        switch (bible_page){
            case Value.KEY_BIBLE_CHAPS:

                startActivity(getIntent());
                break;
            case Value.KEY_BIBLE_VERSES:

                startActivity(getIntent());
                break;
        }
    }

    private void moveNext(){
        switch (bible_page){
            case Value.KEY_BIBLE_CHAPS:

                startActivity(getIntent());
                break;
            case Value.KEY_BIBLE_VERSES:

                startActivity(getIntent());
                break;
        }
    }
}
