package com.example.hello_doctor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String Username = sharedPreferences.getString("username", "");
        Toast.makeText(getApplicationContext(), "Welcome " + Username, Toast.LENGTH_SHORT).show();

        CardView exitCardV = findViewById(R.id.Exit);
        exitCardV.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
            startActivity(new Intent(MainMenu.this, MainActivity.class));
        });

        CardView bookappCardV = findViewById(R.id.BookAppoint);
        bookappCardV.setOnClickListener(v -> {
            Intent i2 = new Intent(MainMenu.this, Appointments.class);
            startActivity(i2);
        });

        CardView info = findViewById(R.id.Infor);
        info.setOnClickListener(v -> {
            Intent i2 = new Intent(MainMenu.this, Information.class);
            startActivity(i2);

        });

        CardView history = findViewById(R.id.HistoryCardView);
        history.setOnClickListener(v -> startActivity(new Intent(MainMenu.this, History.class)));

        CardView ProfileV = findViewById(R.id.ProfileViewingCard);
        ProfileV.setOnClickListener(v -> {
            Intent i2 = new Intent(MainMenu.this, ProfileView.class);
            startActivity(i2);

        });

        CardView editDeleteAPp = findViewById(R.id.EditAppointView);
        editDeleteAPp.setOnClickListener(v -> startActivity(new Intent(MainMenu.this, Confirmation.class)));
    }
}