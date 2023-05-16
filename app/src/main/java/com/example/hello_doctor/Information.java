package com.example.hello_doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Information extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        TextView tw = findViewById(R.id.UpdatesTxt);
        tw.setText("NO UDATES YET");
        tw.setTextColor(Color.GREEN);

        Button goHome = findViewById(R.id.Homebtn123);
        goHome.setOnClickListener(v -> startActivity(new Intent(Information.this, MainMenu.class)));



    }
}