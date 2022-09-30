package com.example.firebaseauth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    private String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private EditText password, email;
    ProgressDialog progressDialog;

    private AppCompatButton login_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login_button = findViewById(R.id.login_bttn);
        progressDialog = new ProgressDialog(this);
        final TextView signUp = findViewById(R.id.sign_up_click);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Homepage.class));
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Signup.class));
            }
        });

    }


}