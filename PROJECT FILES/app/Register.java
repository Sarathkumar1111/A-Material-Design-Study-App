package com.example.owlapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    // Giving variable names
    EditText emailId;
    EditText mobileNo;
    EditText userName;
    EditText newPassword;
    EditText conPassword;

    Button signup;
    Button login;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"; // Setting a particular pattern for email address
    String txtEmailId, txtMobile, txtUserName, txtNewPassword, txtConPassword;

    // Setting firebase
    protected FirebaseAuth mAuth;
    protected FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initializing firebase
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // Assign variables for EditText
        emailId = findViewById(R.id.emailId);
        mobileNo = findViewById(R.id.mobileNo);
        userName = findViewById(R.id.Usr);
        newPassword = findViewById(R.id.pwd);
        conPassword = findViewById(R.id.conPwd);

        // Assign variables for Button
        signup = findViewById(R.id.newUser);
        login = findViewById(R.id.login);

        // To be done when we click signup button
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtEmailId = emailId.getText().toString().trim();
                txtMobile = mobileNo.getText().toString().trim();
                txtUserName = userName.getText().toString().trim();
                txtNewPassword = newPassword.getText().toString().trim();
                txtConPassword = conPassword.getText().toString().trim();
                if (!TextUtils.isEmpty(txtEmailId)) {
                    if (txtEmailId.matches(emailPattern)) {
                        if (!TextUtils.isEmpty(txtMobile)) {
                            if (txtMobile.length() == 10) {
                                if (!TextUtils.isEmpty(txtUserName)) {
                                    if (!TextUtils.isEmpty(txtNewPassword)) {
                                        if (!TextUtils.isEmpty(txtConPassword)) {
                                            if (txtConPassword.equals(txtNewPassword)) {
                                                createUser(); // To create a new user
                                                toLogin();
                                            } else {
                                                conPassword.setError("Confirm password and password doesn't match");
                                            }
                                        } else {
                                            conPassword.setError("Confirm password can't be empty");
                                        }
                                    } else {
                                        newPassword.setError("Password field can't be empty");
                                    }
                                } else {
                                    userName.setError("Username field can't be empty");
                                }
                            } else {
                                mobileNo.setError("Enter a valid mobile number");
                            }
                        } else {
                            mobileNo.setError("Mobile number can't be empty");
                        }
                    } else {
                        emailId.setError("Enter valid email address");
                    }
                } else {
                    emailId.setError("Email field can't be empty");
                }
            }
        });

        // To be done when we click login button
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toLogin();
            }
        });
    }

    private void createUser() {
        signup.setVisibility(View.VISIBLE);

        // Saving the user details to firebase
        mAuth.createUserWithEmailAndPassword(txtEmailId, txtNewPassword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Map<String, Object> user = new HashMap<>();
                        user.put("Email Id", txtEmailId);
                        user.put("Mobile no", txtMobile);
                        user.put("User name", txtUserName);

                        db.collection("users")
                                .add(user)
                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        Toast.makeText(Register.this, "Data stored successfully", Toast.LENGTH_SHORT).show();
                                        toHome();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(Register.this, "Error - " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Register.this, "Error - " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // Method to login activity
    private void toLogin() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }

    // Method to home activity
    private void toHome() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
        finish();
    }
}