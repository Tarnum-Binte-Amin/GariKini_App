package com.example.garikini_app;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

public class AddPostMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post_menu); // Make sure this matches your XML file name

        // Setup Toolbar with back arrow
        Toolbar toolbar = findViewById(R.id.addPostToolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(v -> onBackPressed());
        }

        // 1. Light Vehicles Card
        CardView cardLightVehicle = findViewById(R.id.cardLightVehicle);
        if (cardLightVehicle != null) cardLightVehicle.setOnClickListener(v -> openLocationSelection("Light Vehicles"));

        // 2. Heavy Vehicles Card
        CardView cardHeavyVehicle = findViewById(R.id.cardHeavyVehicle);
        if (cardHeavyVehicle != null) cardHeavyVehicle.setOnClickListener(v -> openLocationSelection("Heavy Vehicles"));

        // 3. Bus Card
        CardView cardBus = findViewById(R.id.cardBus);
        if (cardBus != null) cardBus.setOnClickListener(v -> openLocationSelection("Bus"));

        // 4. Truck Card
        CardView cardTruck = findViewById(R.id.cardTruck);
        if (cardTruck != null) cardTruck.setOnClickListener(v -> openLocationSelection("Truck"));

        // 5. Heavy & Equipment Card
        CardView cardHeavyEquipment = findViewById(R.id.cardHeavyEquipment);
        if (cardHeavyEquipment != null) cardHeavyEquipment.setOnClickListener(v -> openLocationSelection("Heavy & Equipment"));

        // 6. Motorbike Card
        CardView cardMotorbike = findViewById(R.id.cardMotorbike);
        if (cardMotorbike != null) cardMotorbike.setOnClickListener(v -> openLocationSelection("Motorbike"));

        // 7. Bicycle Card
        CardView cardBicycle = findViewById(R.id.cardBicycle);
        if (cardBicycle != null) cardBicycle.setOnClickListener(v -> openLocationSelection("Bicycle"));

        // 8. Auto CNG Card
        CardView cardAutoCng = findViewById(R.id.cardAutoCng);
        if (cardAutoCng != null) cardAutoCng.setOnClickListener(v -> openLocationSelection("Auto CNG"));

        // 9. Paddle Rickshaw Card
        CardView cardPaddleRickshaw = findViewById(R.id.cardPaddleRickshaw);
        if (cardPaddleRickshaw != null) cardPaddleRickshaw.setOnClickListener(v -> openLocationSelection("Paddle Rickshaw"));

        // 10. Paddle Van Card
        CardView cardPaddleVan = findViewById(R.id.cardPaddleVan);
        if (cardPaddleVan != null) cardPaddleVan.setOnClickListener(v -> openLocationSelection("Paddle Van"));

        // 11. Three Wheeler Card
        CardView cardThreeWheeler = findViewById(R.id.cardThreeWheeler);
        if (cardThreeWheeler != null) cardThreeWheeler.setOnClickListener(v -> openLocationSelection("Three Wheeler"));

        // 12. Auto Service Center Card
        CardView cardAutoService = findViewById(R.id.cardAutoService);
        if (cardAutoService != null) cardAutoService.setOnClickListener(v -> openLocationSelection("Auto Service Center"));

        // Top Urgent Banner Button action (optional)
        if (findViewById(R.id.btnTopUrgentBanner) != null) {
            findViewById(R.id.btnTopUrgentBanner).setOnClickListener(v -> {
                // Handle Top Urgent redirection or info modal here
            });
        }
    }

    // Helper method to transition to LocationActivity and pass the selected category name forward
    private void openLocationSelection(String categoryName) {
        Intent intent = new Intent(AddPostMenuActivity.this, LocationListActivity.class);
        intent.putExtra("SELECTED_CATEGORY", categoryName);
        startActivity(intent);
    }
}