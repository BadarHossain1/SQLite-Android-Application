package com.example.uitest;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class viewUsersActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_users);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        LinearLayout linearLayout = findViewById(R.id.linearLayout);

        // Receive the buffer data from Intent
        String userData = getIntent().getStringExtra("user_data");
        if (userData != null && !userData.isEmpty()) {
            String[] entries = userData.split("\n\n");

            TextView title = new TextView(this);
            title.setText("Royalty Estate"); // Title text
            title.setTextSize(28); // Set title text size
            title.setTextColor(Color.YELLOW); // Set title color
            title.setTypeface(null, Typeface.BOLD); // Make the title bold
            title.setPadding(0, 40, 0, 20); // Add padding (top and bottom)

// Set layout parameters for the title
            LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            titleParams.setMargins(20, 20, 20, 20); // Add margins around the title
            title.setLayoutParams(titleParams);

// Center the title
            title.setGravity(Gravity.CENTER);

// Add the title to the LinearLayout
            linearLayout.addView(title);

            for (String entry : entries) {
                // Create a TextView for each entry
                TextView card = new TextView(this);
                card.setText(entry.trim());
                card.setTextSize(24); // Set text size
                card.setTextColor(Color.WHITE); // Set text color to white
                card.setPadding(40, 40, 40, 40); // Add padding inside the card

                // Set background with yellow border
                GradientDrawable cardBackground = new GradientDrawable();
                cardBackground.setColor(Color.parseColor("#083c5d")); // Set background color
                cardBackground.setStroke(4, Color.parseColor("#FFD700")); // Set yellow border
                cardBackground.setCornerRadius(16); // Add rounded corners

                card.setBackground(cardBackground);

                // Add margin between cards
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(20, 20, 20, 20);
                card.setLayoutParams(params);

                // Add the card to the LinearLayout
                linearLayout.addView(card);
            }
        }
    }
}