package com.example.b07_group_19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivityView extends AppCompatActivity implements Authenticate{

    private Spinner spinner;
    private TextView back_to_login;
    private Button btn_signup;
    private EditText signupemail, signuppassword,signupusername;

    SignupActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_view);

        presenter = new SignupActivityPresenter(this, new SignupActivityModel());
        signupemail = findViewById(R.id.signup_email);
        signuppassword = findViewById(R.id.signup_password);
        signupusername = findViewById(R.id.signup_username);
        btn_signup = (Button) findViewById(R.id.signup_button);
        back_to_login = findViewById(R.id.switch_login);
        spinner = findViewById(R.id.signup_spinner);

        String[] Sarray = getResources().getStringArray(R.array.dropdown_items);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Sarray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        back_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginActivity();
            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password, username, role;
                email = String.valueOf(signupemail.getText());
                password = String.valueOf(signuppassword.getText());
                username = String.valueOf(signupusername.getText());
                switch(spinner.getSelectedItemPosition()){
                    case 1:
                        role = "Admin";
                        break;
                    default:
                        role = "Student";
                        break;
                }
                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
                    missingField();
                }
                else if(password.length()<6 || password.length()>30){
                    passwordLength();
                }
                else{
                    create_user(email, username, password, role);
                }

            }
        });

    }

    public void create_user(String email, String username, String password, String role){
        presenter.create_new_user(email, username, password, role);

    }

    public void user_created(String role){
        Toast.makeText(SignupActivityView.this, "Successfully Registered a new " + role + " account!", Toast.LENGTH_SHORT).show();
        openLoginActivity();
    }
    public void openLoginActivity(){
        Intent intent = new Intent(this, LoginActivityView.class);
        startActivity(intent);
        finish();
    }
    @Override
    public void missingField() {
        Toast.makeText(SignupActivityView.this, "Enter missing field", Toast.LENGTH_SHORT).show();
    }

    public void accountFound(String role) {
        Toast.makeText(SignupActivityView.this, "Email already exist for " + role +" account", Toast.LENGTH_SHORT).show();
    }

    public void uniqueUsername() {
        Toast.makeText(SignupActivityView.this, "Enter unique username", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void authError() {
        Toast.makeText(SignupActivityView.this,"Authentication Error. Invalid Email", Toast.LENGTH_SHORT).show();
    }

    public void passwordLength(){
        Toast.makeText(SignupActivityView.this,"Password must be 6 - 30 characters",Toast.LENGTH_SHORT).show();
    }

}