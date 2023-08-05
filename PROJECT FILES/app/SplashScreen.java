package com.example.owlapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class SplashScreen extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mAuth.getCurrentUser() != null) {
                    toHome();
                } else {
                    toLogin();
                }
            }
        }, 2000);
    }

    private void toHome() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
        finish();
    }

    private void toLogin() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }
}
