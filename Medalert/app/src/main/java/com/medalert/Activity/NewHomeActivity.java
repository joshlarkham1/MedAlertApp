package com.medalert.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.medalert.Model.User;
import com.medalert.R;

public class NewHomeActivity extends AppCompatActivity {

    private TextView tvUserName, tvAddressDetail;
    private Button btnSOS, navHome, navBook, navPhone, navMap; // Added navMap
    private ImageButton btnUser;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_home);

        // Initialize Firebase
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        // Initialize UI components
        tvUserName = findViewById(R.id.tv_user_name);
        tvAddressDetail = findViewById(R.id.tv_address_detail);
        btnSOS = findViewById(R.id.btn_sos);
        navHome = findViewById(R.id.nav_home);
        navBook = findViewById(R.id.nav_book);
        navPhone = findViewById(R.id.nav_phone);
        navMap = findViewById(R.id.nav_map); // Initialize the new map button
        btnUser = findViewById(R.id.btn_user);

        // Fetch and display user details
        fetchAndDisplayUserDetails();

        // Set up button click listeners
        setUpButtonListeners();
    }

    private void fetchAndDisplayUserDetails() {
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        if (currentUser != null) {
            String userID = currentUser.getUid();

            // Fetch the user's full name from Firebase Realtime Database
            databaseReference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        User user = dataSnapshot.getValue(User.class);
                        if (user != null) {
                            tvUserName.setText(user.getFullName());
                            tvAddressDetail.setText("Current address: Not available"); // Placeholder for address
                        } else {
                            tvUserName.setText("User");
                            tvAddressDetail.setText("No address available");
                        }
                    } else {
                        tvUserName.setText("Guest");
                        tvAddressDetail.setText("No address available");
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(NewHomeActivity.this, "Failed to load user info.", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            tvUserName.setText("Guest");
            tvAddressDetail.setText("No address available");
        }
    }

    private void setUpButtonListeners() {
        // SOS Button
        btnSOS.setOnClickListener(v -> {
            // Launch SOSActivity
            Intent intent = new Intent(NewHomeActivity.this, SOSActivity.class);
            startActivity(intent);
        });

        // Navigation Buttons
        navHome.setOnClickListener(v ->
                Toast.makeText(this, "Home button clicked: Navigating to Home", Toast.LENGTH_SHORT).show()
        );

        navBook.setOnClickListener(v ->
                Toast.makeText(this, "Book button clicked: Opening Booking Services", Toast.LENGTH_SHORT).show()
        );

        navPhone.setOnClickListener(v -> {
            // Open MedicalNumbersActivity
            Intent intent = new Intent(NewHomeActivity.this, MedicalNumbersActivity.class);
            startActivity(intent);
        });

        navMap.setOnClickListener(v -> {
            // Navigate to MapActivity
            Intent intent = new Intent(NewHomeActivity.this, MapActivity.class);
            startActivity(intent);
        });

        // User Button -> Navigate to Profile Page
        btnUser.setOnClickListener(v -> {
            Intent intent = new Intent(NewHomeActivity.this, ProfileActivity.class);
            startActivity(intent);
        });
    }

    private void sendSOS() {
        Toast.makeText(this, "SOS Alert Sent!", Toast.LENGTH_SHORT).show();
    }
}
