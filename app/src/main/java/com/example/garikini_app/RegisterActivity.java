package com.example.garikini_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {

    private EditText etName, etPhone, etLocation;
    private TextInputEditText etPassword, etRePassword;
    private Button btnRegister;
    private TextView tvLoginRedirect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        etLocation = findViewById(R.id.etLocation);
        etPassword = findViewById(R.id.etPassword);
        etRePassword = findViewById(R.id.etRePassword);
        btnRegister = findViewById(R.id.btnRegister);
        tvLoginRedirect = findViewById(R.id.tvLoginRedirect);

        btnRegister.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();
            String location = etLocation.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String rePassword = etRePassword.getText().toString().trim();

            if (name.isEmpty() || phone.isEmpty() || location.isEmpty() || password.isEmpty() || rePassword.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            } else if (!password.equals(rePassword)) {
                Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
            } else {
                // 1. SAVE USER DATA LOCALLY USING SHARED PREFERENCES
                SharedPreferences prefs = getSharedPreferences("GariKiniUsers", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("name_" + phone, name);
                editor.putString("password_" + phone, password);
                editor.putString("location_" + phone, location);
                editor.apply();

                Toast.makeText(this, "Registration Successful! Please login.", Toast.LENGTH_LONG).show();

                // 2. REDIRECT TO LOGIN PAGE INSTEAD OF ADD POST
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                // Optional: Pass the registered phone number back to the login screen for convenience
                intent.putExtra("REGISTERED_PHONE", phone);
                startActivity(intent);

                // Close RegisterActivity so pressing back doesn't loop here
                finish();
            }
        });

        // Redirect back to login screen when clicking the footer login text
        tvLoginRedirect.setOnClickListener(v -> finish());
    }
}