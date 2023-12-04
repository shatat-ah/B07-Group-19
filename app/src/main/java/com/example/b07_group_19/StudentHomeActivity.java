package com.example.b07_group_19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class StudentHomeActivity extends AppCompatActivity implements  HomeFunction{
    private CardView post_card, even_card, compl_card, announce_card, feedback_card;
    private TextView logOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);

        logOut = findViewById(R.id.logout_btn);
        post_card = findViewById(R.id.post_card);
        post_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentHomeActivity.this, PostReqActivity.class);
                startActivity(intent);
            }
        });

        even_card = findViewById(R.id.rsvp_card);
        even_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(this, );
                //startActivity(intent);
            }
        });
        compl_card = findViewById(R.id.complaint_card);
        compl_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentHomeActivity.this, StudentComplaintView.class);
                startActivity(intent);
            }
        });
        announce_card = findViewById(R.id.announcement_card);
        announce_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        feedback_card = findViewById(R.id.feedback_card);
        feedback_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(StudentHomeActivity.this, AttendedEventsActivity.class);
                startActivity(intent);*/
            }
        });



        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                redirectLogin(StudentHomeActivity.this);
            }
        });
    }


    @Override
    public void redirectLogin(Activity activity) {
        Intent intent = new Intent(activity, LoginActivityView.class);
        startActivity(intent);
    }
}