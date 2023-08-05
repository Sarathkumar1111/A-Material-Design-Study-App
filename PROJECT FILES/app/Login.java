package com.example.owlapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    // Giving variable names
    Button login;
    Button fp;
    Button signup;

    EditText emailId;
    EditText pwd;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"; // Setting a particular pattern for email address
    String txtEmailId, txtPassword;

    private FirebaseAuth mAuth; // Setting firebase authentication variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Assign variables for EditText
        emailId = findViewById(R.id.emailId);
        pwd = findViewById(R.id.pwd);

        // Assign variables for Button
        login = findViewById(R.id.login);
        fp = findViewById(R.id.fp);
        signup = findViewById(R.id.signup);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // To be done when we click login button
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtEmailId = emailId.getText().toString().trim();
                txtPassword = pwd.getText().toString().trim();

                if (!TextUtils.isEmpty(txtEmailId)) {
                    if (txtEmailId.matches(emailPattern)) {
                        if (!TextUtils.isEmpty(txtPassword)) {
                            verifyUser(); // For verifying whether the user is available or not in the database
                            int a = 0;
                        } else {
                            pwd.setError("Password field can't be empty");
                        }
                    } else {
                        emailId.setError("Enter a valid email address");
                    }
                } else {
                    emailId.setError("Email field can't be empty");
                }
            }
        });

        // To be done when we click forgot password button
        fp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toForgotPage();
            }
        });

        // To be done when we click signup button
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toRegister();
            }
        });
    }

    private void verifyUser() {
        // Validation using email address and password entered
        mAuth.signInWithEmailAndPassword(txtEmailId, txtPassword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(Login.this, "Login is successful !!", Toast.LENGTH_SHORT).show();
                        toHome();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Login.this, "Error - " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // Method to forgot password activity
    private void toForgotPage() {
        Intent intent = new Intent(this, Forgot_password.class);
        startActivity(intent);
    }

    // Method to register activity
    private void toRegister() {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    // Method to Home page activity
    private void toHome() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
        finish();
    }
}