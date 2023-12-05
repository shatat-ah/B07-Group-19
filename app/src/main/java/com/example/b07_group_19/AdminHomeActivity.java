package com.example.b07_group_19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class AdminHomeActivity extends AppCompatActivity implements HomeFunction{

    private CardView feedback, schedule, complaints, new_announcement;

    private TextView logOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        logOut = findViewById(R.id.logout_btn);
        schedule = findViewById(R.id.schedule_card);
        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHomeActivity.this, EventCreatorView.class);
                startActivity(intent);
            }
        });
        complaints = findViewById(R.id.complaint_card);
        complaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(this, );
                //startActivity(intent);
            }
        });
        new_announcement = findViewById(R.id.create_announcement_card);
        new_announcement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(this, );
                //startActivity(intent);
            }
        });

        feedback = findViewById(R.id.feedback_card);

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(this,);
                //startActivity(intent);
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                redirectLogin(AdminHomeActivity.this);
            }
        });
    }

    @Override
    public void redirectLogin(Activity activity) {
        Intent intent = new Intent(activity, LoginActivityView.class);
        startActivity(intent);
    }
}


