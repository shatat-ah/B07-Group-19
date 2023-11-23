package com.example.b07_group_19.login_signup_fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.b07_group_19.R;
import com.example.b07_group_19.SignupActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdminLogin#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminLogin extends Fragment {

    private Button btn_signup;
    private Button btn_login;
    public AdminLogin() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize the button here
        btn_signup = view.findViewById(R.id.signup_button);

        btn_signup.setOnClickListener(new View.OnClickListener() {
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