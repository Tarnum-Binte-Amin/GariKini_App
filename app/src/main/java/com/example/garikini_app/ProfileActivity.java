package com.example.garikini_app;

import static android.content.ClipData.newIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private TextView tvProfileName, tvProfilePhone;
    private Button btnLogout, btnBoostItem, btnEditProfile, btnTransactions;
    private Button btnStaySafe, btnFaq, btnShare, btnContactUs, btnAboutUs, btnPrivacy;
    private Button navHome, navSearch, navAddPost, navChats, navProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // 1. Bind Dynamic User Info TextViews
        tvProfileName = findViewById(R.id.tvProfileName);
        tvProfilePhone = findViewById(R.id.tvProfilePhone);

        // Load logged-in user data from Session
        SharedPreferences sessionPrefs = getSharedPreferences("UserSession", MODE_PRIVATE);
        String name = sessionPrefs.getString("current_name", "Tarnum Binte Amin");
        String phone = sessionPrefs.getString("current_phone", "+8801970272073");

        if (tvProfileName != null) tvProfileName.setText(name);
        if (tvProfilePhone != null) tvProfilePhone.setText(phone);


        // =========================================================
        // 2. HANDLE PROFILE MENU BUTTONS
        // =========================================================

        btnBoostItem = findViewById(R.id.btnBoostItem);
        if (btnBoostItem != null) {
            btnBoostItem.setOnClickListener(v -> {
                Toast.makeText(this, "Opening Boost Options...", Toast.LENGTH_SHORT).show();
                // TODO: Start your Boost Activity if you create one
            });
        }

        btnEditProfile = findViewById(R.id.btnEditProfile);
        if (btnEditProfile != null) {
            btnEditProfile.setOnClickListener(v -> {
                Toast.makeText(this, "Edit Profile feature coming soon!", Toast.LENGTH_SHORT).show();
            });
        }

        btnTransactions = findViewById(R.id.btnTransactions);
        if (btnTransactions != null) {
            btnTransactions.setOnClickListener(v -> {
                Toast.makeText(this, "Loading Transactions...", Toast.LENGTH_SHORT).show();
            });
        }

        btnStaySafe = findViewById(R.id.btnStaySafe);
        if (btnStaySafe != null) {
            btnStaySafe.setOnClickListener(v -> {
                Toast.makeText(this, "Stay Safe Guidelines: Meet in safe public places.", Toast.LENGTH_LONG).show();
            });
        }

        btnFaq = findViewById(R.id.btnFaq);
        if (btnFaq != null) {
            btnFaq.setOnClickListener(v -> {
                Toast.makeText(this, "Loading FAQ...", Toast.LENGTH_SHORT).show();
            });
        }

        btnShare = findViewById(R.id.btnShare);
        if (btnShare != null) {
            btnShare.setOnClickListener(v -> {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Download GariKini app to buy and sell vehicles easily!");
                startActivity(Intent.createChooser(shareIntent, "Share via"));
            });
        }

        btnContactUs = findViewById(R.id.btnContactUs);
        if (btnContactUs != null) {
            btnContactUs.setOnClickListener(v -> {
                Toast.makeText(this, "Contact Support: support@garikini.com", Toast.LENGTH_LONG).show();
            });
        }

        btnAboutUs = findViewById(R.id.btnAboutUs);
        if (btnAboutUs != null) {
            btnAboutUs.setOnClickListener(v -> {
                Toast.makeText(this, "GariKini.com - Your ultimate vehicle marketplace.", Toast.LENGTH_LONG).show();
            });
        }

        btnPrivacy = findViewById(R.id.btnPrivacy);
        if (btnPrivacy != null) {
            btnPrivacy.setOnClickListener(v -> {
                Toast.makeText(this, "Opening Privacy Policy...", Toast.LENGTH_SHORT).show();
            });
        }

        // Handle Logout Button
        btnLogout = findViewById(R.id.btnLogout);
        if (btnLogout != null) {
            btnLogout.setOnClickListener(v -> {
                // Clear session
                SharedPreferences.Editor editor = sessionPrefs.edit();
                editor.clear();
                editor.apply();

                // Redirect back to Login and clear history stack
                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            });
        }


        // =========================================================
        // 3. BOTTOM NAVIGATION BAR ROUTING FROM PROFILE
        // =========================================================

        navHome = findViewById(R.id.navHome);
        if (navHome != null) {
            navHome.setOnClickListener(v -> {
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
                finish();
            });
        }

        navSearch = findViewById(R.id.navSearch);
        if (navSearch != null) {
            navSearch.setOnClickListener(v -> {
                startActivity(new Intent(ProfileActivity.this, SearchActivity.class));
            });
        }

        navAddPost = findViewById(R.id.navAddPost);
        if (navAddPost != null) {
            navAddPost.setOnClickListener(v -> {
                startActivity(new Intent(ProfileActivity.this, AddPostMenuActivity.class));
            });
        }

        navChats = findViewById(R.id.navChats);
        if (navChats != null) {
            navChats.setOnClickListener(v -> {
                startActivity(new Intent(ProfileActivity.this, ChatsActivity.class));
            });
        }

        navProfile = findViewById(R.id.navProfile);
        if (navProfile != null) {
            navProfile.setOnClickListener(v -> {
                // Already on profile page
                Toast.makeText(this, "You are already on Profile", Toast.LENGTH_SHORT).show();
            });
        }
    }
}