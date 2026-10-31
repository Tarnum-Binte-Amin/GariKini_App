package com.example.garikini_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ==========================================
        // 1. TOP FILTER & TABS NAVIGATION BAR
        // ==========================================

        // Bangladesh Tab -> Opens Location Selector Screen
        findViewById(R.id.btnLocationTab).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LocationListActivity.class);
            startActivity(intent);
        });

        // Category Tab -> Opens Category Picker Screen
        findViewById(R.id.btnCategoryTab).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CategoryListActivity.class);
            startActivity(intent);
        });

        // Biman E-Ticket Tab -> Opens Biman E-Ticket Booking Screen
        findViewById(R.id.btnBimanTab).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BimanTicketActivity.class);
            startActivity(intent);
        });


        // ==========================================
        // 2. SPECIAL OFFER BANNER BAR ACTIONS
        // ==========================================

        // "বিস্তারিত" Details Button -> Opens Top Urgent Info Dialog Popup
        findViewById(R.id.btnDetailsInfo).setOnClickListener(v -> {
            View dialogView = getLayoutInflater().inflate(R.layout.dialog_top_urgent, null);
            AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                    .setView(dialogView)
                    .create();

            // Close dialog when OK button is pressed inside the popup
            Button btnOk = dialogView.findViewById(R.id.btnDialogOk);
            if (btnOk != null) {
                btnOk.setOnClickListener(btn -> dialog.dismiss());
            }
            dialog.show();
        });

        // Refresh Feed Button -> Shows confirmation toast
        findViewById(R.id.btnRefreshFeed).setOnClickListener(v -> {
            Toast.makeText(this, "Feed refreshed successfully!", Toast.LENGTH_SHORT).show();
        });


        // ==========================================
        // 3. FEED CARDS CLICK LISTENERS (Professional Product Details Routing)
        // ==========================================

        // Item 1: Toyota Corolla Wxb
        findViewById(R.id.cardItem1).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ItemDetailsActivity.class);
            intent.putExtra("ITEM_TITLE", "Toyota Corolla Wxb");
            intent.putExtra("ITEM_PRICE", "৳ 16,00,000");
            intent.putExtra("ITEM_BADGE", "TOP URGENT");
            intent.putExtra("ITEM_IMAGE", R.drawable.toyota_corolla);
            startActivity(intent);
        });

        // Item 2: Truck Selling Post
        findViewById(R.id.cardItem2).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ItemDetailsActivity.class);
            intent.putExtra("ITEM_TITLE", "Truck Selling Post");
            intent.putExtra("ITEM_PRICE", "৳ 13,50,000");
            intent.putExtra("ITEM_BADGE", "TITANIUM MEMBER");
            intent.putExtra("ITEM_IMAGE", R.drawable.truck_selling);
            startActivity(intent);
        });

        // Item 3: Toyota Noah
        findViewById(R.id.cardItem3).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ItemDetailsActivity.class);
            intent.putExtra("ITEM_TITLE", "Toyota Noah");
            intent.putExtra("ITEM_PRICE", "৳ 16,00,000");
            intent.putExtra("ITEM_BADGE", "SILVER MEMBER");
            intent.putExtra("ITEM_IMAGE", R.drawable.toyota_noah);
            startActivity(intent);
        });

        // Item 4: Concrete boom pump
        findViewById(R.id.cardItem4).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ItemDetailsActivity.class);
            intent.putExtra("ITEM_TITLE", "Concrete boom pump");
            intent.putExtra("ITEM_PRICE", "৳ 1,60,00,000");
            intent.putExtra("ITEM_BADGE", "SILVER MEMBER");
            intent.putExtra("ITEM_IMAGE", R.drawable.concrete_boom_pump);
            startActivity(intent);
        });

        // Item 5: Honda Civic Turbo 2022
        findViewById(R.id.cardItem5).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ItemDetailsActivity.class);
            intent.putExtra("ITEM_TITLE", "Honda Civic Turbo 2022");
            intent.putExtra("ITEM_PRICE", "৳ 35,00,000");
            intent.putExtra("ITEM_BADGE", "GOLD MEMBER");
            intent.putExtra("ITEM_IMAGE", R.drawable.honda_civic_turbo);
            startActivity(intent);
        });

        // Item 6: Yamaha R15 V4 Bike
        findViewById(R.id.cardItem6).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ItemDetailsActivity.class);
            intent.putExtra("ITEM_TITLE", "Yamaha R15 V4 Bike");
            intent.putExtra("ITEM_PRICE", "৳ 4,85,000");
            intent.putExtra("ITEM_BADGE", "VERIFIED SELLER");
            intent.putExtra("ITEM_IMAGE", R.drawable.yamaha_r_v);
            startActivity(intent);
        });

        // Item 7: Suzuki WagonR Fresh
        findViewById(R.id.cardItem7).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ItemDetailsActivity.class);
            intent.putExtra("ITEM_TITLE", "Suzuki WagonR Fresh");
            intent.putExtra("ITEM_PRICE", "৳ 9,50,000");
            intent.putExtra("ITEM_BADGE", "SILVER MEMBER");
            intent.putExtra("ITEM_IMAGE", R.drawable.suzuki_wagon_fresh);
            startActivity(intent);
        });

        // Item 8: Mini Pickup Van
        findViewById(R.id.cardItem8).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ItemDetailsActivity.class);
            intent.putExtra("ITEM_TITLE", "Mini Pickup Van");
            intent.putExtra("ITEM_PRICE", "৳ 7,20,000");
            intent.putExtra("ITEM_BADGE", "TITANIUM MEMBER");
            intent.putExtra("ITEM_IMAGE", R.drawable.mini_pickup_van);
            startActivity(intent);
        });


        // ==========================================
        // 4. BOTTOM NAVIGATION BAR ROUTING
        // ==========================================

        // Home Tab (Current Page)
        findViewById(R.id.navHome).setOnClickListener(v -> {
            Toast.makeText(this, "You are already on Home", Toast.LENGTH_SHORT).show();
        });

        // Search Tab -> Opens Search Activity
        findViewById(R.id.navSearch).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SearchActivity.class);
            startActivity(intent);
        });

        // Post Tab (+) -> Checks if user is logged in first
        findViewById(R.id.navPost).setOnClickListener(v -> {
            SharedPreferences sessionPrefs = getSharedPreferences("UserSession", MODE_PRIVATE);
            boolean isLoggedIn = sessionPrefs.getBoolean("is_logged_in", false);

            if (isLoggedIn) {
                // If logged in, go straight to Add Post Menu Activity
                Intent intent = new Intent(MainActivity.this, AddPostMenuActivity.class);
                startActivity(intent);
            } else {
                // If NOT logged in, go to Login Activity and tell it to redirect to AddPost after login
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.putExtra("REDIRECT_TO", "AddPost");
                startActivity(intent);
            }
        });

        // Chats Tab -> Opens Chats Activity
        findViewById(R.id.navChats).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ChatsActivity.class);
            startActivity(intent);
        });

        // Profile Tab -> Opens Profile Activity
        findViewById(R.id.navProfile).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(intent);
        });
    }
}