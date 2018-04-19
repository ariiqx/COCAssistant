package com.videdesk.mobile.cocassistant.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.videdesk.mobile.cocassistant.R;
import com.videdesk.mobile.cocassistant.config.Videx;

public class ResetActivity extends AppCompatActivity {

    private ProgressDialog pDialog;
    private EditText txtEmail;
    private  String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);


        Videx videx = new Videx(ResetActivity.this);
        TextView lblConn = findViewById(R.id.lbl_conn);
        if(videx.isConn()){
            lblConn.setVisibility(View.GONE);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtEmail = findViewById(R.id.email);
        Button btnReset = findViewById(R.id.btn_reset_password);
        Button btnBack = findViewById(R.id.btn_back);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = txtEmail.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplication(), "Enter your registered email address", Toast.LENGTH_SHORT).show();
                    return;
                }
                pDialog = new ProgressDialog(ResetActivity.this);
                pDialog.setTitle("Please wait...");
                pDialog.setMessage("Resetting your COC Account on this device.");
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(false);
                pDialog.show();

            }
        });

    }
}
