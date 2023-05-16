package com.example.hello_doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Confirmation extends AppCompatActivity {

    private Button backBtn, ConfirmButn, DeleteBtn;
    EditText firstname, email, Idno, date, time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");

        DBHandler h = new DBHandler(getApplicationContext(), "Hello_Doctor", null, 1);
        h.getReadableDatabase();

        firstname = findViewById(R.id.editFName);
        email = findViewById(R.id.editEmailName);
        Idno = findViewById(R.id.Edit_IdName);
        date = findViewById(R.id.DateTxt);
        time = findViewById(R.id.EditTxtTime);

        ArrayList list = h.DisplayAppointment(username.trim());

        firstname.setText(list.get(0).toString());
        email.setText(list.get(3).toString());
        Idno.setText(list.get(2).toString());
        date.setText(list.get(4).toString());
        time.setText(list.get(5).toString());

        Button dbtn = findViewById(R.id.DeleteBtn);
        dbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        ConfirmButn = findViewById(R.id.ConBtn);
        ConfirmButn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "See you on the " + date.getText().toString(), Toast.LENGTH_SHORT ).show();
                startActivity(new Intent(Confirmation.this, History.class));
            }
        });


        backBtn = findViewById(R.id.BackCbtn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Confirmation.this, MainMenu.class));
            }
        });

    }
}