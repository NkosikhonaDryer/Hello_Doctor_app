package com.example.hello_doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class History extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");

        DBHandler h = new DBHandler(getApplicationContext(), "Hello_Doctor", null, 1);
        h.getWritableDatabase();


        ArrayList list = h.DisplayAppointment(username.trim());

        EditText tv = findViewById(R.id.EditTextMulty);
        String x = " ";
        for(int i = 0; i < list.size(); i++)
        {
            x += list.get(i).toString() + " ";
        }

        tv.setText(x);




        Button mainM = findViewById(R.id.BtnHome);
        mainM.setOnClickListener(v -> startActivity(new Intent(History.this, MainMenu.class)));

    }
}