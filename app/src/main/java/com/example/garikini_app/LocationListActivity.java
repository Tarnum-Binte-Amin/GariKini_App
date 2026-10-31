package com.example.garikini_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LocationListActivity extends AppCompatActivity {

    private Spinner spinnerDistrict, spinnerUpazila;
    private Button btnLocationNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list);

        spinnerDistrict = findViewById(R.id.spinnerDistrict);
        spinnerUpazila = findViewById(R.id.spinnerUpazila);
        btnLocationNext = findViewById(R.id.btnLocationNext);

        // =========================================================================
        // ADDITION 1: Retrieve the vehicle category passed from AddPostMenuActivity
        // =========================================================================
        String selectedCategory = getIntent().getStringExtra("SELECTED_CATEGORY");

        // Map district selections to their respective Upazila arrays dynamically
        spinnerDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedDistrict = parent.getItemAtPosition(position).toString();
                int arrayResource = R.array.default_upazilas;

                switch (selectedDistrict) {
                    case "Dhaka":
                        arrayResource = R.array.dhaka_upazilas;
                        break;
                    case "Chattogram":
                        arrayResource = R.array.chattogram_upazilas;
                        break;
                    case "Comilla":
                        arrayResource = R.array.comilla_upazilas;
                        break;
                    case "Feni":
                        arrayResource = R.array.feni_upazilas;
                        break;
                    case "Brahmanbaria":
                        arrayResource = R.array.brahmanbaria_upazilas;
                        break;
                    case "Rajshahi":
                        arrayResource = R.array.rajshahi_upazilas;
                        break;
                    case "Barisal":
                        arrayResource = R.array.barisal_upazilas;
                        break;
                    case "Sylhet":
                        arrayResource = R.array.sylhet_upazilas;
                        break;
                    case "Khulna":
                        arrayResource = R.array.khulna_upazilas;
                        break;
                    case "Rangpur":
                        arrayResource = R.array.rangpur_upazilas;
                        break;
                    case "Mymensingh":
                        arrayResource = R.array.mymensingh_upazilas;
                        break;
                }

                // Bind the corresponding Upazila list to the second spinner
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                        LocationListActivity.this,
                        arrayResource,
                        android.R.layout.simple_spinner_dropdown_item
                );
                spinnerUpazila.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        // Next Button Action
        btnLocationNext.setOnClickListener(v -> {
            String district = spinnerDistrict.getSelectedItem().toString();
            String upazila = spinnerUpazila.getSelectedItem().toString();

            if (district.equals("Select District")) {
                Toast.makeText(this, "Please select a district", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Selected: " + district + ", " + upazila, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(LocationListActivity.this, PostFormActivity.class);
                intent.putExtra("SELECTED_DISTRICT", district);
                intent.putExtra("SELECTED_UPAZILA", upazila);

                // =========================================================================
                // ADDITION 2: Pass the category forward to PostFormActivity as well
                // =========================================================================
                intent.putExtra("SELECTED_CATEGORY", selectedCategory);

                startActivity(intent);
            }
        });
    }
}