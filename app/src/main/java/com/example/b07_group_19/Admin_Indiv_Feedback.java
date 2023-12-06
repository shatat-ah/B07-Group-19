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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Admin_Indiv_Feedback extends AppCompatActivity {

    private FirebaseDatabase db;
    private TextView no_feed;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_indiv_feedback);

        String eventName = getIntent().getStringExtra("EVENT");
        db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference().child("feedback");
        DatabaseReference feedbackRef = ref.child(eventName);
        no_feed = findViewById(R.id.NoEvents);
        LinearLayout parentlayout = findViewById(R.id.layout);
        Button backBtn = findViewById(R.id.backBtnIndiv);

        Query query = feedbackRef;
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot user_feedback_snapshot: snapshot.getChildren()){
                        String userID = user_feedback_snapshot.getKey();
                        EventReviewObject obj = user_feedback_snapshot.getValue(EventReviewObject.class);
                        String summary = obj.getSummary();
                        int rating = obj.getRating();
                        populateScrollView(userID, rating, summary,parentlayout);
                    }
                }
                else{
                    no_feed.setText("No Feedback Available");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBack();
            }
        });

    }

    public TextView createTextView(String text){
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setPadding(5, 5, 5, 5);
        textView.setTextSize(22);
        ViewGroup.MarginLayoutParams textLayoutParams = new ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        textLayoutParams.setMargins(5, 5, 5, 5);
        textView.setLayoutParams(textLayoutParams);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.BLACK);
        return textView;
    }

    public LinearLayout newLayout(){

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.gravity = Gravity.CENTER;

        linearLayout.setPadding(16, 16, 16, 16);
        linearLayout.setLayoutParams(layoutParams);
        return linearLayout;
    }
    public void populateScrollView(String studentID,int rating, String summary, LinearLayout L) {
        //create the Textiews
        TextView id = createTextView("ID: "+studentID);
        String string_rating = Integer.toString(rating);
        TextView view_rating = createTextView("Rating: "+string_rating);
        TextView layout_summary = createTextView("Summary: "+summary);

        //Create new linear
        LinearLayout layout1 = newLayout();
        LinearLayout layout2 = newLayout();
        LinearLayout layout3 = newLayout();

        layout1.addView(id);
        layout2.addView(view_rating);
        layout3.addView(layout_summary);

        L.addView(layout1);
        L.addView(layout2);
        L.addView(layout3);
    }

    public void onBack(){
        Intent intent = new Intent(Admin_Indiv_Feedback.this,Admin_Feedback.class);
        startActivity(intent);
        finish();
    }
}
