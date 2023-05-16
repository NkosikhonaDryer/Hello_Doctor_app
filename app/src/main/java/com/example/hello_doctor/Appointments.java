package com.example.hello_doctor;


import static java.lang.String.*;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class Appointments extends AppCompatActivity {

    private int apYear, apMonth, apDay, apHour,apMinute;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);

        SharedPreferences sharedPreferences = getSharedPreferences("shares_prefs", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("username", "");
        name = name.trim();
        DBHandler h = new DBHandler(getApplicationContext(), "Hello_Doctor", null, 1);
        ArrayList list = h.getUser(name);


        EditText firstname = findViewById(R.id.editTextName);
        EditText email = findViewById(R.id.editTextTextEmailAddress);
        EditText phone = findViewById(R.id.editTextPhone);
        EditText time = findViewById(R.id.editTime);
        EditText date = findViewById(R.id.EditDate);
        EditText appointN = findViewById(R.id.editAppointNature);
        firstname.setText(list.get(0).toString());
        email.setText(name);
        phone.setText(list.get(4).toString());
        Button BookApp = findViewById(R.id.BookAppbtn);
        BookApp.setOnClickListener(v -> {
            String name1 = firstname.getText().toString();
            String emails = email.getText().toString();
            String times = time.getText().toString();
            String dates = date.getText().toString();
            String lastname = list.get(1).toString();
            String idNo = list.get(2).toString();
            String notes = appointN.getText().toString();

            UseRestrictions r  = new UseRestrictions();

            if(r.isValidIdNo(idNo) || r.isValidDate(dates) || r.isValidFnam(name1) || r.isValidEMail(emails)
             || r.isValidLnam(lastname) || r.isValidNOAP(notes) || r.isValidTime(times))
            {
                if(h.getAppointsments(times, dates) == 0) {
                     long c = h.UserAppointments(name1, lastname, idNo, emails, times, dates, notes);
                     if(c != -1) {
                         Toast.makeText(getApplicationContext(), "Success.", Toast.LENGTH_SHORT).show();
                         startActivity(new Intent(Appointments.this, Confirmation.class));
                     }
                }else{
                    Toast.makeText(getApplicationContext(),"time,date or notes not filled!.", Toast.LENGTH_SHORT).show();
                }
            }else{

                Toast.makeText(getApplicationContext(),"choose other time,date or notes.", Toast.LENGTH_SHORT).show();

            }





            /*

            if(times.isEmpty() || dates.isEmpty() || notes.isEmpty())
            {
                Toast.makeText(getApplicationContext(),"time,date or notes not filled!.", Toast.LENGTH_SHORT).show();
            }else{

                if(h.getAppointsments(times.trim(), dates.trim()) == 0) {

                    long  resu = h.UserAppointments(name1, lastname, idNo, emails, times,dates, notes);
                    if(resu > -1 ) {
                        Toast.makeText(getApplicationContext(), "Appointment successfully Booked", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Appointments.this, Confirmation.class));
                    }else{
                        Toast.makeText(getApplicationContext(), "Error occurred in the database", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Please choose another date or time slot", Toast.LENGTH_SHORT).show();
                }
            }*/

        });

        Button homeBtn = findViewById(R.id.HomeBtn);
        homeBtn.setOnClickListener(v -> startActivity(new Intent(Appointments.this, MainMenu.class)));

        Button dateSelectBtn = findViewById(R.id.DateBtn);
        dateSelectBtn.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();
            apYear = c.get(Calendar.YEAR);
            apMonth = c.get(Calendar.MONTH);
            apDay = c.get(Calendar.DAY_OF_MONTH);

            @SuppressLint("DefaultLocale") DatePickerDialog datePicker = new DatePickerDialog(this,
                    (view, year, month, dayOfMonth) -> date.setText(format("%d-%d-%d", dayOfMonth, month + 1, year))
                    , apYear,apMonth, apDay);
            datePicker.getDatePicker().setMinDate(c.getTimeInMillis());

            datePicker.show();
        });

        Button timeSelect = findViewById(R.id.SelectTimeBtn);
        timeSelect.setOnClickListener(v -> {
            final Calendar cal = Calendar.getInstance();
            apHour = cal.get(Calendar.HOUR);
            apMinute = cal.get(Calendar.MINUTE);


            @SuppressLint(value = "SetTextI18n") TimePickerDialog timepick = new TimePickerDialog(this, (view, hourOfDay, minute) ->
                    time.setText(hourOfDay + " : " + minute), apHour, apMinute, true);


            timepick.show();
        });

    }

}

