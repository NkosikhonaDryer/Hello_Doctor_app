package com.example.hello_doctor;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class changePass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);


        EditText ema = findViewById(R.id.editEMailChangePass);
        EditText id = findViewById(R.id.editTextIDChangePass);
        EditText Fname = findViewById(R.id.EditFnameTxt);
        EditText inpassword = findViewById(R.id.editTextChangePassword);
        EditText cpass = findViewById(R.id.EditChangeConfirmPassword);


        Button login = findViewById(R.id.goLoginBtn);
        login.setOnClickListener(v -> startActivity(new Intent(changePass.this, MainActivity.class)));

        DBHandler h = new DBHandler(this.getApplicationContext(), "Hello_Doctor", null, 1);
        Button RData = findViewById(R.id.RetrieveDatBtn);
        RData.setOnClickListener(v -> {

            String emai, id_no, firsname;
            emai = ema.getText().toString();
            id_no = id.getText().toString();

            if(emai.isEmpty())
            {
                Toast.makeText(getApplicationContext(), "Email must not be empty", Toast.LENGTH_SHORT).show();
            }else {
                ArrayList db1 = h.getUser(emai);

                id_no = db1.get(2).toString();
                firsname = db1.get(0).toString();

                UseRestrictions resr = new UseRestrictions();

                if(id_no.equals(id.getText().toString()) || !id.getText().toString().isEmpty())
                {
                    Fname.setText(firsname);

                    if(inpassword.getText().toString().isEmpty() || cpass.getText().toString().isEmpty())
                    {
                        Toast.makeText(getApplicationContext(), "Please enter password on both fields", Toast.LENGTH_SHORT).show();
                    }else{
                        if(inpassword.getText().toString().equals(cpass.getText().toString())) {
                                int r = h.updatePass(inpassword.getText().toString(), id_no, emai);
                                if (r > 0) {
                                    Toast.makeText(getApplicationContext(), "Update Success", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(changePass.this, MainActivity.class));
                                } else {
                                    Toast.makeText(getApplicationContext(), "Update Failed", Toast.LENGTH_SHORT).show();
                                }
                            }else {

                                Toast.makeText(getApplicationContext(), "Password must contain Capital,Special letters and numbers", Toast.LENGTH_SHORT).show();

                            }
                        }
                    }
                }
        });

    }
}