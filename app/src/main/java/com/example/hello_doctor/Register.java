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

public class Register extends AppCompatActivity {
    private EditText firstname, Lastname, IDnumber, Phone, Password, Email;
    private EditText confirmPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button backBtn = findViewById(R.id.BackBtnRegi);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, MainActivity.class));
            }
        });
        Button RegisterBtn = findViewById(R.id.RegisterBut);


        RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstname = findViewById(R.id.firstnameTxBx);
                Lastname = findViewById(R.id.EditTextLastName);
                IDnumber = findViewById(R.id.EditTextIDno);
                Email = findViewById(R.id.EmailAddressTxtR);
                Phone = findViewById(R.id.TextBxPhone);
                Password = findViewById(R.id.RegisterPassword);
                confirmPass = findViewById(R.id.ConfirmPassword);

                String fname = firstname.getText().toString();
                String Lname = Lastname.getText().toString();
                String IDN = IDnumber.getText().toString();
                String fone = Phone.getText().toString();
                String Pass = Password.getText().toString();
                String mail = Email.getText().toString();
                String confirmPas =  confirmPass.getText().toString();



                DBHandler db = new DBHandler(getApplicationContext(), "Hello_Doctor", null, 1);

                if(fname.length() != 0 || Lname.length() != 0 || IDN.length() != 0 || fone.length() != 0 || Pass.length() != 0 || mail.length() != 0)
                {
                    if(IDN.length() == 13 || fone.length() == 10 || mail.contains("@")) {

                        db.register(fname, Lname, IDN, mail, fone, Pass);
                        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                       SharedPreferences sharedPreferences = getSharedPreferences("shares_prefs", Context.MODE_PRIVATE);
                       SharedPreferences.Editor editor = sharedPreferences.edit();
                       editor.putString("username", mail);
                       editor.apply();

                        Intent intent = new Intent(Register.this, MainActivity.class);
                        startActivity(intent);
                    }
                        if(!confirmPass.getText().equals(Pass))
                        {
                            Toast.makeText(getApplicationContext(), "Password does not match!", Toast.LENGTH_SHORT).show();
                        }
                    else{
                        Toast.makeText(getApplicationContext(), "please fill the Boxes right.", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "please fill the boxes Right.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}