package com.videdesk.mobile.cocassistant.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.videdesk.mobile.cocassistant.BuildConfig;
import com.videdesk.mobile.cocassistant.R;

public class ObsoleteActivity extends AppCompatActivity {

    private static final String version = "Version ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obsolete);

        //Version
        String versionName = BuildConfig.VERSION_NAME;

        TextView appVer = findViewById(R.id.app_version);
        String ver = version + versionName;
        appVer.setText(ver);

        Button btnUpdate = findViewById(R.id.btn_update);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateApp();
            }
        });
    }

    private void updateApp(){
        final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }
}
