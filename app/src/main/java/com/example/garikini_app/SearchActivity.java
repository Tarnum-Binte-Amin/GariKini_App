package com.example.garikini_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class SearchActivity extends AppCompatActivity {

    private SearchView searchViewWidget;
    private TextView tvSectionTitle;
    private Button btnFilterCategory, btnFilterCondition, btnFilterPrice, btnFilterLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchViewWidget = findViewById(R.id.searchViewWidget);
        tvSectionTitle = findViewById(R.id.tvSectionTitle);
        btnFilterCategory = findViewById(R.id.btnFilterCategory);
        btnFilterCondition = findViewById(R.id.btnFilterCondition);
        btnFilterPrice = findViewById(R.id.btnFilterPrice);
        btnFilterLocation = findViewById(R.id.btnFilterLocation);

        // 1. Handle Search Query Submission
        searchViewWidget.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                performSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Optional: Live filtering logic can be written here
                return false;
            }
        });

        // 2. Handle Category Filter Button Click (Opens a Selection Dialog)
        btnFilterCategory.setOnClickListener(v -> {
            String[] categories = {"All Categories", "Light Vehicles", "Heavy Vehicles", "Motorbike", "Bicycle", "Auto CNG", "Truck", "Bus"};

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select Vehicle Category");
            builder.setItems(categories, (dialog, which) -> {
                String selectedCategory = categories[which];
                btnFilterCategory.setText("🏷️ " + selectedCategory);
                Toast.makeText(this, "Filtered by: " + selectedCategory, Toast.LENGTH_SHORT).show();
                // TODO: Apply your adapter list filter here for this category
            });
            builder.show();
        });

        // 3. Handle Condition Filter Button Click (Opens Condition Dialog)
        btnFilterCondition.setOnClickListener(v -> {
            String[] conditions = {"All Conditions", "Brand New", "Reconditioned", "Used / Second-hand"};

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select Vehicle Condition");
            builder.setItems(conditions, (dialog, which) -> {
                String selectedCondition = conditions[which];
                btnFilterCondition.setText("⚙️ " + selectedCondition);
                Toast.makeText(this, "Filtered by condition: " + selectedCondition, Toast.LENGTH_SHORT).show();
            });
            builder.show();
        });

        // 4. Handle Price Range Filter
        btnFilterPrice.setOnClickListener(v -> {
            Toast.makeText(this, "Price range filter options opened", Toast.LENGTH_SHORT).show();
        });

        // 5. Handle Location Filter
        btnFilterLocation.setOnClickListener(v -> {
            Toast.makeText(this, "Opening Location selector...", Toast.LENGTH_SHORT).show();
        });

        // =========================================================================
        // 6. CATCH INCOMING CATEGORY FROM CategoryListActivity
        // =========================================================================
        String categoryFilter = getIntent().getStringExtra("SELECTED_CATEGORY");

        if (categoryFilter != null) {
            // Update the UI title header to show the user what category they clicked
            tvSectionTitle.setText("Category: " + categoryFilter);
            btnFilterCategory.setText("🏷️ " + categoryFilter);

            // Optional feedback toast
            Toast.makeText(this, "Showing items for: " + categoryFilter, Toast.LENGTH_SHORT).show();

            // TODO: Filter your vehicle lists or database queries based on `categoryFilter` here!
        }
    }

    // Method to execute search, show feedback, and save search history locally
    private void performSearch(String query) {
        if (query.trim().isEmpty()) return;

        // Update the section label dynamically to reflect what was searched
        tvSectionTitle.setText("Search Results for: \"" + query + "\"");

        Toast.makeText(this, "Showing results for: " + query, Toast.LENGTH_SHORT).show();

        // Save Search History locally using SharedPreferences
        SharedPreferences prefs = getSharedPreferences("SearchHistoryPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("last_search", query);
        editor.apply();
    }
}