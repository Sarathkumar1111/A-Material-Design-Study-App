package com.example.owlapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgot_password extends AppCompatActivity {

    EditText emailId;

    String txtEmailId;

    Button sendEmail;
    Button signup;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        mAuth = FirebaseAuth.getInstance();

        emailId = findViewById(R.id.emailId);

        sendEmail = findViewById(R.id.sendEmail);
        signup = findViewById(R.id.signup);

        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateEmail();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toRegister();
            }
        });
    }

    private void validateEmail() {
        txtEmailId = emailId.getText().toString().trim();
        if (txtEmailId.isEmpty()) {
            emailId.setError("Email field can't be empty");
        } else {
            forgot();
        }
    }

    private void forgot() {
        mAuth.sendPasswordResetEmail(txtEmailId)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Forgot_password.this, "Email sent successfully!!", Toast.LENGTH_SHORT).show();
                            toLogin();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Forgot_password.this, "Error - " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void toLogin() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }

    private void toRegister() {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }


}
