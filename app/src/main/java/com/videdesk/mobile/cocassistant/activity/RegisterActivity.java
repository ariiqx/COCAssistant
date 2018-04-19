package com.videdesk.mobile.cocassistant.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.videdesk.mobile.cocassistant.R;
import com.videdesk.mobile.cocassistant.config.Videx;

// import DynamoDBMapper

public class RegisterActivity extends AppCompatActivity {

    // Declare a DynamoDBMapper object
    //DynamoDBMapper dynamoDBMapper;

    private ProgressDialog pDialog;

    private EditText txtName, txtEmail, txtPhone, txtPass;

    private String name, email, phone, pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        /*
        // Instantiate a AmazonDynamoDBMapperClient
        AmazonDynamoDBClient dynamoDBClient = new AmazonDynamoDBClient(AWSMobileClient.getInstance().getCredentialsProvider());
        this.dynamoDBMapper = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient)
                .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
                .build();
        */

        Videx videx = new Videx(RegisterActivity.this);

        pDialog = new ProgressDialog(RegisterActivity.this);


        txtName = findViewById(R.id.user_name);
        txtEmail = findViewById(R.id.user_email);
        txtPhone = findViewById(R.id.user_phone);
        txtPass = findViewById(R.id.user_password);

        TextView lblConn = findViewById(R.id.lbl_conn);
        if(videx.isConn()){
            lblConn.setVisibility(View.GONE);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        Button btnRegister = findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInputs()){
                    name = Videx.toProper(txtName.getText().toString().trim());
                    email = txtEmail.getText().toString().trim().toLowerCase();
                    pass = txtPass.getText().toString().trim();
                    pDialog.show();
                    createUser();
                }

            }
        });

    }


    private boolean checkInputs(){

        if (TextUtils.isEmpty(txtName.getText().toString())) {
            txtName.setFocusable(true);
            txtName.requestFocus();
            txtName.setError("Enter your full name!");
            return false;
        }

        if (txtName.getText().toString().length() < 6) {
            txtName.setFocusable(true);
            txtName.requestFocus();
            txtName.setError("Please enter your valid full name!");
            return false;
        }

        if (TextUtils.isEmpty(txtEmail.getText().toString())) {
            txtEmail.setFocusable(true);
            txtEmail.requestFocus();
            txtEmail.setError("Enter email address!");
            return false;
        }

        if (!txtEmail.getText().toString().contains("@")) {
            txtEmail.setFocusable(true);
            txtEmail.requestFocus();
            txtEmail.setError("Email address is missing the '@' sign!");
            return false;
        }

        if (!txtEmail.getText().toString().contains(".")) {
            txtEmail.setFocusable(true);
            txtEmail.requestFocus();
            txtEmail.setError("Email address is missing the 'dot (.)' sign!");
            return false;
        }

        if (!txtPhone.getText().toString().startsWith("+")) {
            txtPhone.setFocusable(true);
            txtPhone.requestFocus();
            txtPhone.setError("Phone number must begin with a '+' sign!");
            return false;
        }

        if (TextUtils.isEmpty(txtPhone.getText().toString())) {
            txtPhone.setFocusable(true);
            txtPhone.requestFocus();
            txtPhone.setError("Please enter your phone number!");
            return false;
        }

        if(!isValidPhoneNumber(txtPhone.getText().toString())){
            txtPhone.setFocusable(true);
            txtPhone.requestFocus();
            txtPhone.setError("Phone number is not valid!");
            return false;
        }

        if(!validateUsing_libphonenumber(txtPhone.getText().toString())){
            txtPhone.setFocusable(true);
            txtPhone.requestFocus();
            txtPhone.setError("Phone number is not valid!");
            return false;
        }

        if (TextUtils.isEmpty(txtPass.getText().toString())) {
            txtPass.setFocusable(true);
            txtPass.requestFocus();
            txtPass.setError("Enter password!");
            return false;
        }

        if (txtPass.getText().toString().length() < 6) {
            txtPass.setFocusable(true);
            txtPass.requestFocus();
            txtPass.setError("Password too short, enter minimum 6 characters!");
            return false;
        }

        return true;
    }

    private boolean isValidPhoneNumber(CharSequence phoneNumber) {
        return !TextUtils.isEmpty(phoneNumber) && Patterns.PHONE.matcher(phoneNumber).matches();
    }

    private boolean validateUsing_libphonenumber(String vPhone) {
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        Phonenumber.PhoneNumber phoneNumber = null;
        try {
            phoneNumber = phoneNumberUtil.parse(vPhone, "");
        } catch (NumberParseException e) {
            System.err.println(e);
        }

        boolean isValid = phoneNumberUtil.isValidNumber(phoneNumber);
        if (isValid) {
            phone = phoneNumberUtil.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.E164);
            return true;
        } else {
            Toast.makeText(this, "Phone number is not valid " + phoneNumber, Toast.LENGTH_LONG).show();
            return false;
        }
    }

    private void createUser() {
        /*
        final UsersDO userItem = new UsersDO();

        userItem.setNode(Videx.getNode());
        userItem.setName(name);
        userItem.setEmail(email);
        userItem.setPhone(phone);
        userItem.setPass(pass);
        userItem.setCode(Videx.getCode());
        userItem.setPhoto("none");
        userItem.setCreated(Videx.getDatedTimed());
        userItem.setUpdated(Videx.getDatedTimed());
        userItem.setStatus(Value.KEY_STATUS_ACTIVE);

        new Thread(new Runnable() {
            @Override
            public void run() {
                dynamoDBMapper.save(userItem);
                // Item saved in AWS, pass it to local SQLite
                User user = new User();
                user.setNode(userItem.getNode());
                user.setName(userItem.getName());
                user.setEmail(userItem.getEmail());
                user.setPhone(userItem.getPhone());
                user.setPass(userItem.getPass());
                user.setCode(userItem.getCode());
                user.setPhoto(userItem.getPhoto());
                user.setCreated(userItem.getCreated());
                user.setUpdated(userItem.getUpdated());
                user.setStatus(userItem.getStatus());
                UsersDA users = new UsersDA();
                users.add(user);
            }
        }).start();
    */
    }

}
