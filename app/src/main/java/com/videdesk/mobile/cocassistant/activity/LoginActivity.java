package com.videdesk.mobile.cocassistant.activity;

import android.app.ProgressDialog;
import android.content.Intent;
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

public class LoginActivity extends AppCompatActivity {

    // Declare a DynamoDBMapper object
    //DynamoDBMapper dynamoDBMapper;

    private ProgressDialog pDialog;

    private EditText txtEmail, txtPassword;
    private String password, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /*
        // Instantiate a AmazonDynamoDBMapperClient
        AmazonDynamoDBClient dynamoDBClient = new AmazonDynamoDBClient(AWSMobileClient.getInstance().getCredentialsProvider());
        this.dynamoDBMapper = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient)
                .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
                .build();
        */

        Videx videx = new Videx(LoginActivity.this);
        TextView lblConn = findViewById(R.id.lbl_conn);
        if(videx.isConn()){
            lblConn.setVisibility(View.GONE);
        }

        txtEmail = findViewById(R.id.email);
        txtPassword = findViewById(R.id.password);
        Button btnRegister = findViewById(R.id.btn_signup);
        Button btnLogin = findViewById(R.id.btn_login);
        Button btnReset = findViewById(R.id.btn_reset_password);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ResetActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = txtEmail.getText().toString();
                password = txtPassword.getText().toString();
                if(checkInputs()){
                    readUser();
                }else {
                    Toast.makeText(getApplicationContext(), "Login failed! Please correct the errors and try again.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private boolean checkInputs(){

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            txtEmail.setError("Enter email address!");
            return false;
        }

        if (!email.contains("@")) {
            Toast.makeText(getApplicationContext(), "Email address is missing the '@' sign!", Toast.LENGTH_SHORT).show();
            txtEmail.setFocusable(true);
            txtEmail.requestFocus();
            txtEmail.setError("Email address is missing the '@' sign!");
            return false;
        }

        if (!email.contains(".")) {
            Toast.makeText(getApplicationContext(), "Email address is missing the 'dot (.)' sign!", Toast.LENGTH_SHORT).show();
            txtEmail.setFocusable(true);
            txtEmail.requestFocus();
            txtEmail.setError("Email address is missing the 'dot (.)' sign!");
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            txtPassword.setFocusable(true);
            txtPassword.requestFocus();
            txtEmail.setError("Enter password!");
            return false;
        }

        if (password.length() < 6 ) {
            Toast.makeText(getApplicationContext(), "Password must be more than 6 characters!", Toast.LENGTH_SHORT).show();
            txtPassword.setFocusable(true);
            txtPassword.requestFocus();
            txtEmail.setError("Password must be more than 6 characters!");
            return false;
        }
        return true;
    }
    public void readUser() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                //UsersDO userItem = dynamoDBMapper.load(UsersDO.class, identityManager.getCachedUserID(), "Article1");

                // Item read
                // Log.d("News Item:", newsItem.toString());
            }
        }).start();
    }
}
