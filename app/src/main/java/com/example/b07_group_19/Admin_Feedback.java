package com.example.b07_group_19;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;



public class Admin_Feedback extends AppCompatActivity {

    private FirebaseDatabase db;
    private DatabaseReference event_ref;
    private FirebaseAuth mAuth;
    private TextView no_events;
    private ArrayList<FeedbackScrollObject> eventList = new ArrayList<>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_feedback);

        db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference();
        DatabaseReference feed_ref = ref.child("feedback");
        mAuth = FirebaseAuth.getInstance();
        no_events = findViewById(R.id.NoEvents);
        LinearLayout parentlayout = findViewById(R.id.layout);
        FirebaseUser user = mAuth.getCurrentUser();
        ArrayList<String> list = new ArrayList<>();
        Query query = ref;

        Button backBtn = findViewById(R.id.backBtnFeedback);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToHome();
            }
        });

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot eventname: snapshot.getChildren()){
                        String eventName = snapshot.child("eventname").getValue(String.class);
                        list.add(eventName);
                    }
                    for (String name: list){
                        populateScrollView(name,parentlayout);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void populateScrollView(String eventName, LinearLayout L) {
        //create new linear layout
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        //create text for the card
        TextView titleview = new TextView(this);
        titleview.setText(eventName);
        titleview.setPadding(5, 5, 5, 5);
        titleview.setTextSize(25);
        ViewGroup.MarginLayoutParams textLayoutParams = new ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        textLayoutParams.setMargins(1, 10, 1, 1);
        titleview.setLayoutParams(textLayoutParams);
        titleview.setGravity(Gravity.CENTER);
        titleview.setTextColor(Color.WHITE);
        //create text for the card
        //Create card
        CardView cardView = new CardView(this);
        cardView.setCardElevation(10);
        cardView.setRadius(16);
        cardView.setPadding(20, 40, 20, 20);
        ViewGroup.MarginLayoutParams p = new ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                200
        );
        no_events.setText(" ");
        p.setMargins(5, 8, 5, 8);
        cardView.setLayoutParams(p);
        cardView.setBackgroundColor(Color.rgb(51, 81, 88));
        cardView.addView(titleview);
        cardView.setForegroundGravity(Gravity.CENTER);
        //add card to the linearlayout
        //layout.addView(cardView);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Open a new .java file dor indiv event Admin_Feedback
                Intent intent = new Intent(Admin_Feedback.this, Admin_Indiv_Feedback.class);
                startActivity(intent);
            }
        });

        L.addView(cardView);
    }

    private void backToHome(){
        Intent intent = new Intent(Admin_Feedback.this,AdminHomeActivity.class);
        startActivity(intent);
        finish();
    }
}
