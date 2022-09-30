package com.example.firebaseauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Signup extends AppCompatActivity {

    private String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private EditText name, email, mobile, pass1, pass2;

    private AppCompatButton signup_button;
    private TextView login;
    ProgressDialog progressDialog;
    ProgressBar progressBar;

    FirebaseAuth mAuth;
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
        mAuth = FirebaseAuth.getInstance();

        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(view.VISIBLE);
                final String getEmail = email.getText().toString();
                final String getMobile = mobile.getText().toString();
                //signupauthentication(view);
                String s_email = email.getText().toString();
                String s_phn = mobile.getText().toString();
                String s_pass1 = pass1.getText().toString();
                String s_pass2 = pass2.getText().toString();
                String s_name = name.getText().toString();

                if (s_name.isEmpty()) name.setError("Name field can't be empty");
                else if (!s_email.matches(emailpattern)) email.setError("Enter correct e-mail");
                else if (s_phn.isEmpty() || s_phn.length() != 11)
                    mobile.setError("Enter correct mobile no");
                else if (s_pass1.isEmpty()) pass1.setError("Password field can't be empty.");
                else if (s_pass1.length() < 6) pass1.setError("Password length must be at least 6");
                else if (!s_pass1.equals(s_pass2)) pass2.setError("Password didn't match");
                else {
                    progressDialog.setMessage("Please wait for the next step");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();

                    mAuth.createUserWithEmailAndPassword(s_email,s_pass1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {


                                progressDialog.dismiss();
                                Toast.makeText(Signup.this, "Registration successfull", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Signup.this, Homepage.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            } else {
                                progressDialog.dismiss();
                                try {
                                    throw task.getException();
                                } catch (Exception e) {
                                    Toast.makeText(getApplicationContext(), "Email already taken! Try login", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
                    login.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(Signup.this, Login.class));
                        }
                    });


                }
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Signup.this, Login.class));
            }
        });

    }
}