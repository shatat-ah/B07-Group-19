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

public class LoginActivityView extends AppCompatActivity implements Authenticate{
    private EditText usernameEditText, passwordEditText;
    private Button loginButton;
    private Spinner spinner;
    private TextView back_to_signup;
    LoginActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_view);

        presenter = new LoginActivityPresenter(this, new LoginActivityModel());
        loginButton = (Button)findViewById(R.id.login_button);
        usernameEditText = findViewById(R.id.login_username);
        passwordEditText = findViewById(R.id.login_password);
        back_to_signup = findViewById(R.id.switch_signup);
        spinner = findViewById(R.id.login_spinner);
        String[] Larray = getResources().getStringArray(R.array.dropdown_items);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Larray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username, password, role;
                username = String.valueOf(usernameEditText.getText());
                password = String.valueOf(passwordEditText.getText());
                switch(spinner.getSelectedItemPosition()){
                    case 1:
                        role = "Admin";
                        break;
                    default:
                        role = "Student";
                        break;
                }
                existing_user(username, role, password);
            }
        });
        back_to_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignupActivity();
            }
        });

    }
    public void existing_user(String username, String role, String password){
        presenter.checkDBuser(username, role, password);
    }
    @Override
    public void missingField(){
        Toast.makeText(LoginActivityView.this,"Enter Missing Field",Toast.LENGTH_SHORT).show();
    }
    public void userExist(String role){
        Toast.makeText(LoginActivityView.this,"User Exist!",Toast.LENGTH_SHORT).show();
        if (role.equals("Student")){
            openStudentHome();
        }
        else{
            openAdminHome();
        }

    }


    public void userNotFound() {
        Toast.makeText(LoginActivityView.this,"Invalid Credentials",Toast.LENGTH_SHORT).show();
    }

    public void openSignupActivity(){
        Intent intent = new Intent(this, SignupActivityView.class);
        startActivity(intent);
        finish();
    }
    public void openStudentHome(){
        Intent intent = new Intent(LoginActivityView.this, StudentHomeActivity.class);
        startActivity(intent);
    }
    public void openAdminHome(){
        Intent intent = new Intent(LoginActivityView.this, AdminHomeActivity.class);
        startActivity(intent);
    }
    @Override
    public void authError() {
        Toast.makeText(LoginActivityView.this,"Authentication Error", Toast.LENGTH_SHORT).show();
    }
}