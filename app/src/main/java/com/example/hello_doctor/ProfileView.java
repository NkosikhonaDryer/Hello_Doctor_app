package com.example.hello_doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ProfileView extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);

        EditText firstname, lastname, id_no, email, phone;

        firstname = findViewById(R.id.EditTextFirstNameProfile);
        lastname = findViewById(R.id.editLastnameProfile);
        id_no = findViewById(R.id.ID_numberProfile);
        email = findViewById(R.id.editEmailAddressProfile);
        phone = findViewById(R.id.EditPhoneProfile);
        DBHandler h = new DBHandler(this.getApplicationContext(), "Hello_Doctor", null, 1);


        SharedPreferences sharedPreferences = getSharedPreferences("shares_prefs", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("username", "");
        name = name.trim();

        String finalName  = name;

        ArrayList db1 = h.getUser(finalName);

        firstname.setText(db1.get(0).toString());
        lastname.setText(db1.get(1).toString());
        id_no.setText(db1.get(2).toString());
        email.setText(db1.get(3).toString());
        phone.setText(db1.get(4).toString());


        Button updateBtn = findViewById(R.id.UpdateProBtn);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int r = h.updateUser(email.getText().toString(), phone.getText().toString(), finalName);

                if(r == 0)
                {
                    Toast.makeText(getApplicationContext(), "Failed to update " + finalName + "try again" , Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "phone and email updated successfully", Toast.LENGTH_LONG).show();
                    SharedPreferences sharedPreferences = getSharedPreferences("shares_prefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.clear();
                    editor.apply();
                    startActivity(new Intent(ProfileView.this, MainActivity.class));
                }

            }
        });
        TextView tx = findViewById(R.id.textChangePassword);


        Button BackBtn = findViewById(R.id.BackProBtn);

        BackBtn.setOnClickListener(v -> {
            startActivity(new Intent(ProfileView.this, MainMenu.class));

        });


        tx.setOnClickListener(v -> startActivity(new Intent(ProfileView.this, changePass.class)));

    }
}