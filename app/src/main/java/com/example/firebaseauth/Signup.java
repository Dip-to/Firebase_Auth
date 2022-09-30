package com.example.firebaseauth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Signup extends AppCompatActivity {
    private boolean pass_show1 = false;
    private boolean pass_show2 = false;

    private String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private EditText name, email, mobile, pass1, pass2;

    private AppCompatButton signup_button;
    private TextView login;
    ProgressDialog progressDialog;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name = findViewById(R.id.sup_name);
        email = findViewById(R.id.sup_email);
        mobile = findViewById(R.id.sup_mobile);
        pass1 = findViewById(R.id.sup_pass);
        pass2 = findViewById(R.id.sup_pass_cnfrm);
        progressBar = findViewById(R.id.progressBar);
        signup_button = findViewById(R.id.sup_button);
        login = findViewById(R.id.login_page_back);
        progressDialog = new ProgressDialog(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Signup.this, Login.class));
            }
        });

    }
}