package com.example.garikini_app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class BimanTicketActivity extends AppCompatActivity {

    private EditText etFrom, etTo, etDepartureDate, etReturnDate;
    private Button btnSearchFlights;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biman_ticket);

        // Setup Toolbar with back button navigation
        Toolbar toolbar = findViewById(R.id.bimanToolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(v -> onBackPressed());
        }

        // Bind XML fields to Java variables
        etFrom = findViewById(R.id.etFrom);
        etTo = findViewById(R.id.etTo);
        etDepartureDate = findViewById(R.id.etDepartureDate);
        etReturnDate = findViewById(R.id.etReturnDate);
        btnSearchFlights = findViewById(R.id.btnSearchFlights);

        // Handle Flight Search Button Click & Validation
        btnSearchFlights.setOnClickListener(v -> {
            String fromCity = etFrom.getText().toString().trim();
            String toCity = etTo.getText().toString().trim();
            String depDate = etDepartureDate.getText().toString().trim();
            String retDate = etReturnDate.getText().toString().trim();

            if (fromCity.isEmpty() || toCity.isEmpty() || depDate.isEmpty()) {
                Toast.makeText(this, "Please fill in Departure, Arrival, and Departure Date", Toast.LENGTH_SHORT).show();
            } else {
                // Success action: Proceed to search flights
                Toast.makeText(this, "Searching flights from " + fromCity + " to " + toCity + "...", Toast.LENGTH_LONG).show();

                // You can add your search results routing intent here if needed
            }
        });
    }
}