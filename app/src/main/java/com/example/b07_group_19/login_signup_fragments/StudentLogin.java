package com.example.b07_group_19.login_signup_fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.b07_group_19.LoginActivityView;
import com.example.b07_group_19.R;
import com.example.b07_group_19.SignupActivity;
import com.google.android.material.textfield.TextInputEditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StudentLogin#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudentLogin extends Fragment {

    private Button signup;
    private Button btn_2;

    TextInputEditText editTextEmail, editTextPassword;

    public StudentLogin(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_student_login, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize the button here
        signup = (Button)view.findViewById(R.id.student_switch_signup_button);
        btn_2  =(Button)view.findViewById(R.id.student_login_button);
        editTextEmail = view.findViewById(R.id.student_login_email);
        editTextPassword = view.findViewById(R.id.student_login_password);

        //USER INPUT
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password;
                email  = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());

                //check if email or password is empty
                if (TextUtils.isEmpty(email)){
                    Toast.makeText(getActivity(),"Enter email",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(getActivity(),"Enter password",Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });
        //Fragments should not launch other activities or fragments.
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignupActivity();
            }
        });
    }

    public void openSignupActivity(){
        Intent intent = new Intent(getActivity(), SignupActivity.class);
        startActivity(intent);
    }
}