package com.example.hello_doctor;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText emailTx = findViewById(R.id.LoginEmail);
        EditText passTx = findViewById(R.id.editLoginPassword);
        Button RegisterBtn = findViewById(R.id.registerBtn);
        Button LoginBtn = findViewById(R.id.LoginBtn);

        TextView cpa = findViewById(R.id.changePassTxt);
        cpa.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, changePass.class)));



        RegisterBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Register.class)));

        LoginBtn.setOnClickListener(v -> {
            String username = emailTx.getText().toString();
            String password = passTx.getText().toString();

            DBHandler db = new DBHandler(getApplicationContext(), "Hello_Doctor",null, 1);

            if(username.length() == 0 || password.length()==0)
            {
                Toast.makeText(getApplicationContext(), "please fill the forms", Toast.LENGTH_SHORT).show();
            }
            else{
                if(db.login(username, password) == 1)
                {
                    Toast.makeText(getApplicationContext(),"Login Success", Toast.LENGTH_SHORT).show();
                    SharedPreferences sharedPreferences = getSharedPreferences("shares_prefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username", username);
                    editor.apply();

                    Intent i2 = new Intent(MainActivity.this, MainMenu.class);
                    startActivity(i2);
                }else{
                    Toast.makeText(getApplicationContext(),"Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}