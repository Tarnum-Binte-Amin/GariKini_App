package com.example.garikini_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CategoryListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        // Initialize all 16 buttons from XML

        Button btnAllAds = findViewById(R.id.btnAllAds);
        Button btnTopUrgent = findViewById(R.id.btnTopUrgent);
        Button btnLightVehicles = findViewById(R.id.btnLightVehicles);
        Button btnHeavyVehicles = findViewById(R.id.btnHeavyVehicles);
        Button btnMotorbike = findViewById(R.id.btnMotorbike);
        Button btnBicycle = findViewById(R.id.btnBicycle);
        Button btnHelicopter = findViewById(R.id.btnHelicopter);
        Button btnPaddleRickshaw = findViewById(R.id.btnPaddleRickshaw);
        Button btnPaddleVan = findViewById(R.id.btnPaddleVan);
        Button btnThreeWheeler = findViewById(R.id.btnThreeWheeler);
        Button btnAutoCng = findViewById(R.id.btnAutoCng);
        Button btnAutoService = findViewById(R.id.btnAutoService);
        Button btnAutoSpare = findViewById(R.id.btnAutoSpare);
        Button btnGarage = findViewById(R.id.btnGarage);
        Button btnParking = findViewById(R.id.btnParking);
        Button btnRentalVehicle = findViewById(R.id.btnRentalVehicle);

        // Set click behavior for each category button (All 16 covered)

        btnAllAds.setOnClickListener(v -> onCategorySelected("All Ads"));
        btnTopUrgent.setOnClickListener(v -> onCategorySelected("Top Urgent"));
        btnLightVehicles.setOnClickListener(v -> onCategorySelected("Light Vehicles"));
        btnHeavyVehicles.setOnClickListener(v -> onCategorySelected("Heavy Vehicles"));
        btnMotorbike.setOnClickListener(v -> onCategorySelected("Motorbike"));
        btnBicycle.setOnClickListener(v -> onCategorySelected("Bicycle"));
        btnHelicopter.setOnClickListener(v -> onCategorySelected("Helicopter"));
        btnPaddleRickshaw.setOnClickListener(v -> onCategorySelected("Paddle Rickshaw"));
        btnPaddleVan.setOnClickListener(v -> onCategorySelected("Paddle Van"));
        btnThreeWheeler.setOnClickListener(v -> onCategorySelected("Three Wheeler"));
        btnAutoCng.setOnClickListener(v -> onCategorySelected("Auto CNG"));
        btnAutoService.setOnClickListener(v -> onCategorySelected("Auto Service Center"));
        btnAutoSpare.setOnClickListener(v -> onCategorySelected("Auto Spare Shop and Parts"));
        btnGarage.setOnClickListener(v -> onCategorySelected("Garage"));
        btnParking.setOnClickListener(v -> onCategorySelected("Parking"));
        btnRentalVehicle.setOnClickListener(v -> onCategorySelected("Rental Vehicle"));
    }

    // Helper method to handle actions and route to Search/Results page for ALL categories
    private void onCategorySelected(String categoryName) {
        Toast.makeText(this, "Filtering: " + categoryName, Toast.LENGTH_SHORT).show();

        // Pass the selected category name to SearchActivity (or a results screen)
        Intent intent = new Intent(CategoryListActivity.this, SearchActivity.class);
        intent.putExtra("SELECTED_CATEGORY", categoryName);
        startActivity(intent);
    }
}