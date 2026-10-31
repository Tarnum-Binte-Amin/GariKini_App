package com.example.garikini_app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class PostFormActivity extends AppCompatActivity {

    private EditText etTitle, etDescription, etPrice, etPhone;
    private CheckBox cbTerms;
    private Button btnSelectImages, btnSubmitPost;
    private String selectedCategory = "";
    private Uri selectedImageUri = null; // To store the chosen gallery picture URI

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_form);

        // Receive the category clicked from the previous Add Post menu screen
        selectedCategory = getIntent().getStringExtra("SELECTED_CATEGORY");

        // Also retrieve location info if passed from LocationListActivity
        String district = getIntent().getStringExtra("SELECTED_DISTRICT");
        String upazila = getIntent().getStringExtra("SELECTED_UPAZILA");

        // Setup Toolbar & display the category name dynamically
        Toolbar toolbar = findViewById(R.id.formToolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            if (selectedCategory != null) {
                getSupportActionBar().setTitle("Post: " + selectedCategory);
            }
            toolbar.setNavigationOnClickListener(v -> onBackPressed());
        }

        // Initialize fields
        etTitle = findViewById(R.id.etTitle);
        etDescription = findViewById(R.id.etDescription);
        etPrice = findViewById(R.id.etPrice);
        etPhone = findViewById(R.id.etPhone);
        cbTerms = findViewById(R.id.cbTerms);
        btnSelectImages = findViewById(R.id.btnSelectImages);
        btnSubmitPost = findViewById(R.id.btnSubmitPost);

        // Handle Image Selector click (opens phone gallery)
        btnSelectImages.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, 300);
        });

        // Handle Post Submission, Validation, and Home Feed Redirection
        btnSubmitPost.setOnClickListener(v -> {
            String title = etTitle.getText().toString().trim();
            String description = etDescription.getText().toString().trim();
            String price = etPrice.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();

            if (title.isEmpty() || description.isEmpty() || price.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            } else if (!cbTerms.isChecked()) {
                Toast.makeText(this, "Please agree to the Terms and Conditions", Toast.LENGTH_SHORT).show();
            } else {
                // Success feedback
                Toast.makeText(this, selectedCategory + " post submitted successfully!", Toast.LENGTH_LONG).show();

                // TODO: Save your post details (title, price, image URI, etc.) to database or local storage here if needed

                // Redirect user directly back to the Home feed (MainActivity)
                Intent intent = new Intent(PostFormActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish(); // Close this form activity so users can't press back into an empty form
            }
        });
    }

    // Capture the selected image from the device gallery
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 300 && resultCode == RESULT_OK && data != null) {
            selectedImageUri = data.getData();
            if (selectedImageUri != null) {
                Toast.makeText(this, "Image selected successfully!", Toast.LENGTH_SHORT).show();
                btnSelectImages.setText("Image Selected ✓");
            }
        }
    }
}