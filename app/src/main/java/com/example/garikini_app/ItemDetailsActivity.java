package com.example.garikini_app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ItemDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        // Setup Toolbar with back button
        Toolbar toolbar = findViewById(R.id.detailToolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(v -> onBackPressed());
        }

        // Bind UI Elements
        ImageView imgProduct = findViewById(R.id.imgDetailProduct);
        TextView tvTitle = findViewById(R.id.tvDetailTitle);
        TextView tvPrice = findViewById(R.id.tvDetailPrice);
        TextView tvBadge = findViewById(R.id.tvDetailBadge);
        TextView tvDescription = findViewById(R.id.tvDetailDescription);

        Button btnCall = findViewById(R.id.btnCall);
        Button btnChat = findViewById(R.id.btnChat);

        // Receive Data passed from MainActivity Intent
        String title = getIntent().getStringExtra("ITEM_TITLE");
        String price = getIntent().getStringExtra("ITEM_PRICE");
        String badge = getIntent().getStringExtra("ITEM_BADGE");
        int imageRes = getIntent().getIntExtra("ITEM_IMAGE", R.drawable.toyota_corolla);

        // Set Data to Views dynamically
        if (title != null) tvTitle.setText(title);
        if (price != null) tvPrice.setText("Price: " + price + " Fixed Price");
        if (badge != null) tvBadge.setText("⭐ " + badge);
        imgProduct.setBackgroundResource(imageRes);
        tvDescription.setText(title + " in excellent condition. Fully up-to-date papers, smooth engine performance, and genuine mileage run.");

        // Populate Specifications Table Rows dynamically (from your second code snippet)
        populateSpecRow(R.id.specCategory, "Category:", "Light Vehicles / Transport");
        populateSpecRow(R.id.specBrand, "Brand:", title != null ? title.split(" ")[0] : "Toyota");
        populateSpecRow(R.id.specCondition, "Condition:", "Used / Reconditioned");
        populateSpecRow(R.id.specModel, "Model:", "2022 Edition");
        populateSpecRow(R.id.specFuel, "Fuel Type:", "Octane / Diesel");
        populateSpecRow(R.id.specBody, "Body Type:", "Sedan / Van");
        populateSpecRow(R.id.specTransmission, "Transmission:", "Automatic");
        populateSpecRow(R.id.specMileage, "Mileage:", "35,000 km");
        populateSpecRow(R.id.specEngine, "Engine Power:", "1500 cc");
        populateSpecRow(R.id.specYear, "Production Year:", "2022");

        // =========================================================================
        // 1. MAKE A DIRECT PHONE CALL TO SELLER (Using ACTION_DIAL)
        // =========================================================================
        btnCall.setOnClickListener(v -> {
            String sellerPhoneNumber = "+8801970272073"; // Preset seller contact number
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:" + sellerPhoneNumber));
            try {
                startActivity(callIntent);
            } catch (Exception e) {
                Toast.makeText(this, "Unable to launch dialer", Toast.LENGTH_SHORT).show();
            }
        });

        // =========================================================================
        // 2. OPEN CHATBOX WITH SELLER (Routes to ChatsActivity with Extras)
        // =========================================================================
        btnChat.setOnClickListener(v -> {
            Intent intent = new Intent(ItemDetailsActivity.this, ChatsActivity.class);
            intent.putExtra("SELLER_NAME", "Saidul Alam Ridoy");
            intent.putExtra("ITEM_NAME", title != null ? title : "Vehicle Item");
            startActivity(intent);
        });
    }

    // Helper method to fill each included specification row view dynamically
    private void populateSpecRow(int includeId, String label, String value) {
        View rowView = findViewById(includeId);
        if (rowView != null) {
            TextView tvLabel = rowView.findViewById(R.id.tvSpecLabel);
            TextView tvValue = rowView.findViewById(R.id.tvSpecValue);
            if (tvLabel != null) tvLabel.setText(label);
            if (tvValue != null) tvValue.setText(value);
        }
    }
}