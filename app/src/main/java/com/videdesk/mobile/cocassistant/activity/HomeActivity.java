package com.videdesk.mobile.cocassistant.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.videdesk.mobile.cocassistant.BuildConfig;
import com.videdesk.mobile.cocassistant.R;
import com.videdesk.mobile.cocassistant.adapter.ThemeAdapter;
import com.videdesk.mobile.cocassistant.config.Value;
import com.videdesk.mobile.cocassistant.config.Videx;
import com.videdesk.mobile.cocassistant.models.Appz;
import com.videdesk.mobile.cocassistant.models.Theme;
import com.videdesk.mobile.cocassistant.models.User;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Appz appz;
    private RecyclerView recyclerView;

    private Videx videx;
    private ActionBar actionBar;
    private com.github.clans.fab.FloatingActionMenu fabHome, fabMenu;

    private int page_id, countThemes, countBookmarks, countFavourites, countMessages, countContacts, countCalendars;
    private ThemeAdapter themeAdapter;

    //private PeopleDA personData;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /*
        AWSMobileClient.getInstance().initialize(this, new AWSStartupHandler() {
            @Override
            public void onComplete(AWSStartupResult awsStartupResult) {
                Log.d("HomeActivity", "AWSMobileClient is instantiated and you are connected to AWS!");
            }
        }).execute();
        */

        user = null;
        // More onCreate code ...
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getVersion();

        actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.txt_home));


        videx = new Videx(HomeActivity.this);
        //personData = new PeopleDA(HomeActivity.this);

        countThemes = 8;

        page_id = 0;
        countContacts = 0;
        countCalendars = 0;
        countBookmarks = 0;
        countFavourites = 0;
        countMessages = 0;

                recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        loadThemes();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /*
            ACCOUNT MENU
         */
        fabHome = findViewById(R.id.fab_home);
        fabHome.setMenuButtonColorNormal(videx.getColor("500"));

        // Fab ConnectMe.
        com.github.clans.fab.FloatingActionButton fabProfile = findViewById(R.id.fab_connectme);
        fabProfile.setColorNormal(videx.getColor("500"));
        fabProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user != null) {
                    //startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
                }else{
                    authAlert("Connect Me", "You need a COC Account to be connected to a Personal Bible Teachers. Login now to continue.");
                }
            }
        });

        // Fab Dashboard.
        com.github.clans.fab.FloatingActionButton fabDashboard = findViewById(R.id.fab_dashboard);
        fabDashboard.setColorNormal(videx.getColor("500"));
        fabDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user != null) {
                    //startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
                }else{
                    authAlert("Editor Dashboard", "You need a COC Account to have access to the Editor Dashboard. Login now to continue.");
                }
            }
        });

        /*
            PERSONAL MENU
         */
        fabMenu = findViewById(R.id.fab_menu);
        fabMenu.setMenuButtonColorNormal(videx.getColor("500"));

        // Fab Chats.
        com.github.clans.fab.FloatingActionButton fabNew = findViewById(R.id.fab_new);
        fabNew.setColorNormal(videx.getColor("500"));
        fabNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
            }
        });

        // Fab Mails.
        com.github.clans.fab.FloatingActionButton fabManage = findViewById(R.id.fab_manage);
        fabManage.setColorNormal(videx.getColor("500"));
        fabManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(HomeActivity.this, StartActivity.class));
            }
        });

    }

    private void getVersion(){
        try {
            String id = getResources().getString(R.string.app_id);

        }catch (Exception ex){
            Log.e("AppVersion:", ex.getMessage());
        }
    }

    private void checkVersion(){
        String userVersion = BuildConfig.VERSION_NAME;
        String liveVersion = appz.getVersion();
        double uv = Double.parseDouble(userVersion);
        double lv = Double.parseDouble(liveVersion);
        if(uv < lv){
            startActivity(new Intent(HomeActivity.this, ObsoleteActivity.class));
            finish();
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
                startActivity(new Intent(HomeActivity.this, AboutActivity.class));
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

                switch ( page_id){
                    case 0:
                        // If the list of mails is greater than 0, allow search on mails
                        if(countThemes > 0) {
                            themeAdapter.getFilter().filter(newText);
                        }
                        return true;
                    case 1:
                        // If the list of mails is greater than 0, allow search on mails
                        if(countMessages > 0) {
                            //notebookAdapter.getFilter().filter(newText);
                        }
                        return true;
                    case 2:
                        // If the list of mails is greater than 0, allow search on mails
                        if(countContacts > 0) {
                            //notebookAdapter.getFilter().filter(newText);
                        }
                        return true;
                    case 3:
                        // If the list of mails is greater than 0, allow search on mails
                        if(countCalendars > 0) {
                            //notebookAdapter.getFilter().filter(newText);
                        }
                        return true;
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id){
            case R.id.nav_home:
                page_id = 0;
                actionBar.setTitle(getResources().getString(R.string.txt_home));
                fabMenu.setVisibility(View.GONE);
                fabHome.setVisibility(View.VISIBLE);

                break;
            case R.id.nav_messages:
                page_id = 1;
                actionBar.setTitle(getResources().getString(R.string.txt_messages));
                fabHome.setVisibility(View.GONE);
                fabMenu.setVisibility(View.VISIBLE);

                break;
            case R.id.nav_contacts:
                page_id = 2;
                actionBar.setTitle(getResources().getString(R.string.txt_contacts));
                fabHome.setVisibility(View.GONE);
                fabMenu.setVisibility(View.VISIBLE);

                break;
            case R.id.nav_calendar:
                page_id = 3;
                actionBar.setTitle(getResources().getString(R.string.txt_calendar));
                fabHome.setVisibility(View.GONE);
                fabMenu.setVisibility(View.VISIBLE);

                break;
            case R.id.nav_bookmarks:

                break;
            case R.id.nav_favourites:

                break;
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void loadThemes(){
        List<Theme> themeList = new ArrayList<>();
        themeAdapter = new ThemeAdapter(HomeActivity.this, themeList);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(themeAdapter);
        themeList.clear();
        String[] theme_nodes = Value.themeNodes;
        String[] theme_titles = Value.themeTitle;
        String[] theme_images = Value.themeImages;
        String[] theme_colors = Value.themeColors;
        for(int s = 0; s < theme_nodes.length; s++){
            int id = s + 1;
            String node = "" + id;
            String title = theme_titles[s];
            String image = theme_images[s];
            String color = theme_colors[s];

            Theme theme  = new Theme(node, title, image, color);
            themeList.add(theme);
        }
        themeAdapter.notifyDataSetChanged();
    }



    private void authAlert(String title, String message){
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(HomeActivity.this, android.R.style.Theme_Material_Dialog_NoActionBar);
        } else {
            builder = new AlertDialog.Builder(HomeActivity.this);
        }
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(HomeActivity.this, StartActivity.class));
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
    }
}
