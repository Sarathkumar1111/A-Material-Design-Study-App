package com.example.owlapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

    CardView cv1, cv2, cv3, cv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        cv1 = findViewById(R.id.cv1);
        cv2 = findViewById(R.id.cv2);
        cv3 = findViewById(R.id.cv3);
        cv4 = findViewById(R.id.cv4);

        cv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toCourse1();
            }
        });

        cv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toCourse2();
            }
        });

        cv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toCourse3();
            }
        });

        cv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toCourse4();
            }
        });
    }

    private void toCourse1() {
        Intent intent = new Intent(this, Course1.class);
        startActivity(intent);
    }
    private void toCourse2() {
        Intent intent = new Intent(this, Course2.class);
        startActivity(intent);
    }
    private void toCourse3() {
        Intent intent = new Intent(this, Course3.class);
        startActivity(intent);
    }
    private void toCourse4() {
        Intent intent = new Intent(this, Course4.class);
        startActivity(intent);
    }
}