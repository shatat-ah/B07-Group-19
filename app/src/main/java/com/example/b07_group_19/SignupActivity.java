package com.example.b07_group_19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class SignupActivity extends AppCompatActivity {

    private Spinner spinner;

    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        spinner = findViewById(R.id.signup_spinner);
        String[] Sarray = getResources().getStringArray(R.array.dropdown_items);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Sarray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        btn_login = (Button) findViewById(R.id.button_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginActivity();
            }
        });

    }

    public void openLoginActivity(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}