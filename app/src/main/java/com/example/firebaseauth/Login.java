package com.example.firebaseauth;

import androidx.annotation.NonNull;
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private EditText password, email;
    private TextView l_google;
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    private AppCompatButton login_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        l_google=findViewById(R.id.lgg);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login_button = findViewById(R.id.login_bttn);
        progressDialog = new ProgressDialog(this);
        final TextView signUp = findViewById(R.id.sign_up_click);
        mAuth = FirebaseAuth.getInstance();

        l_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //olin er kaj
            }
        });


        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String s_email = email.getText().toString();
                String s_pass = password.getText().toString();
                if (!s_email.matches(emailpattern)) email.setError("Enter correct e-mail");
                else if (s_pass.isEmpty()) password.setError("Password field can't be empty.");
                else {
                    progressDialog.setMessage("Please wait while login");
                    progressDialog.setTitle("Log In");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();

                    mAuth.signInWithEmailAndPassword(s_email, s_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                progressDialog.dismiss();
                                send_to_homepage();
                                Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();
                            } else {
                                progressDialog.dismiss();
                                try {
                                    throw task.getException();
                                } catch (Exception e) {
                                    Toast.makeText(getApplicationContext(), "Wrong Credential. Please try again.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
                }
            }

            private void send_to_homepage() {
                Intent intent = new Intent(Login.this, Homepage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
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