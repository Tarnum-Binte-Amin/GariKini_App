package com.example.garikini_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class LoginActivity extends AppCompatActivity {

    private Toolbar loginToolbar;
    private EditText etPhone, etPassword;
    private Button btnLogin, btnSignUp;
    private String redirectTo = ""; // To check where to go after login

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Check if an intent extra was passed telling us where to redirect after login
        if (getIntent() != null) {
            redirectTo = getIntent().getStringExtra("REDIRECT_TO");
        }

        // Setup Toolbar
        loginToolbar = findViewById(R.id.loginToolbar);
        setSupportActionBar(loginToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("GariKini - Login");
        }

        // Initialize UI Views
        etPhone = findViewById(R.id.etPhone);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);

        // Handle Login Button Click
        btnLogin.setOnClickListener(v -> {
            String phone = etPhone.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            // Validation checks
            if (phone.isEmpty()) {
                etPhone.setError("Please enter your phone number");
                etPhone.requestFocus();
                return;
            }
            if (password.isEmpty()) {
                etPassword.setError("Please enter your password");
                etPassword.requestFocus();
                return;
            }

            String loggedInName = "";

            // 1. Check if it's the default preset user
            if (phone.equals("01970272073")) {
                loggedInName = "Tarnum Binte Amin";
                saveUserSession(loggedInName, phone);
                proceedAfterLogin();
                return;
            }

            // 2. Otherwise, check against registered users in SharedPreferences
            SharedPreferences prefs = getSharedPreferences("GariKiniUsers", MODE_PRIVATE);
            String savedPassword = prefs.getString("password_" + phone, null);

            if (savedPassword != null && savedPassword.equals(password)) {
                loggedInName = prefs.getString("name_" + phone, "User");
                saveUserSession(loggedInName, phone);
                proceedAfterLogin();
            } else {
                Toast.makeText(LoginActivity.this, "Invalid Phone Number or Password!", Toast.LENGTH_LONG).show();
            }
        });

        // Handle Sign Up Button Click -> Redirects user to RegisterActivity
        btnSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    // Helper method to save current active user session
    private void saveUserSession(String name, String phone) {
        SharedPreferences sessionPrefs = getSharedPreferences("UserSession", MODE_PRIVATE);
        SharedPreferences.Editor editor = sessionPrefs.edit();
        editor.putString("current_name", name);
        editor.putString("current_phone", phone);
        editor.putBoolean("is_logged_in", true);
        editor.apply();
    }

    // Helper method to transition to the correct page after successful login
    private void proceedAfterLogin() {
        Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();

        Intent intent;
        // If they came from the Post button, send them to AddPostMenuActivity
        if ("AddPost".equals(redirectTo)) {
            intent = new Intent(LoginActivity.this, AddPostMenuActivity.class);
        } else {
            // Otherwise, go to MainActivity by default
            intent = new Intent(LoginActivity.this, MainActivity.class);
        }

        startActivity(intent);
        finish(); // Finish LoginActivity so user can't go back to it via the back button
    }
}