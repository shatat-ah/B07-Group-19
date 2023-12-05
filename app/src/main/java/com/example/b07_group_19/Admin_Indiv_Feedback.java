package com.example.b07_group_19;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class Admin_Indiv_Feedback extends AppCompatActivity {

    private FirebaseDatabase db;
    private DatabaseReference event_ref;
    private FirebaseAuth mAuth;
    private TextView no_feed;
    private ArrayList<String> eventList = new ArrayList<>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_indiv_feedback);

        String eventName = getIntent().getStringExtra("EVENT");
        db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference();
        DatabaseReference feedbackRef = ref.child(eventName);
        no_feed = findViewById(R.id.NoEvents);
        LinearLayout parentlayout = findViewById(R.id.layout);

        Button backBtn = findViewById(R.id.backBtnIndiv);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToHome();
            }
        });

        String[] Array = new String[]{"badevent", "eventa", "eventb", "eventc", "eventd"};
        for (int i = 0; i < Array.length; i++) {
            populateScrollView(Array[i], parentlayout);
        }

        String name = "NAME";
    }

    private void backToHome(){
        Intent intent = new Intent(Admin_Indiv_Feedback.this,StudentHomeActivity.class);
        startActivity(intent);
        finish();
    }
}
