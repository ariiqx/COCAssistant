package com.videdesk.mobile.cocassistant.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.videdesk.mobile.cocassistant.BuildConfig;
import com.videdesk.mobile.cocassistant.R;

import java.util.Calendar;

public class AboutActivity extends AppCompatActivity {

    private static final String copy = "Copyright ";
    private static final String version = "Version ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Year
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);

        // Get the copyright symbol
        String copi = "\u00a9";

        // Developer
        TextView appDev = findViewById(R.id.lbl_app_ryt);
        String dev = copy + copi + year;
        appDev.setText(dev);

        //Version
        String versionName = BuildConfig.VERSION_NAME;

        TextView appVer = findViewById(R.id.lbl_app_ver);
        String ver = version + versionName;
        appVer.setText(ver);
    }
}
