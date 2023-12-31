package com.example.b07_group_19;

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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AttendedEventsActivity extends AppCompatActivity {


    private FirebaseDatabase db;
    private DatabaseReference event_ref;
    private FirebaseAuth mAuth;
    private Button home;
    private ArrayList<FeedbackScrollObject> eventList = new ArrayList<>();


    //UNDO


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attended_events);

        home = (Button)findViewById(R.id.home_btn);
        db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference();
        event_ref = ref.child("events");
        mAuth = FirebaseAuth.getInstance();
        LinearLayout parentlayout = findViewById(R.id.layout1);
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null){
           String user_email = user.getEmail();
           Query query = event_ref.orderByChild("rvspList").equalTo(user_email);


           query.addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot snapshot) {
                   if(snapshot.exists()){
                       for(DataSnapshot eventSnapshot: snapshot.getChildren()){
                           LocalDateTime eventDate = eventSnapshot.child("LocalDateTime").getValue(new GenericTypeIndicator<LocalDateTime>() {});
                           String time = getTimeAsString(eventDate);
                           String eventName = eventSnapshot.child("title").getValue(String.class);
                           FeedbackScrollObject obj = new FeedbackScrollObject(eventName, time);
                           eventList.add(obj);
                       }
                       //add under here
                       LocalDateTime timeNow = LocalDateTime.now();
                       String currentTime = getTimeAsString(timeNow);
                       for(FeedbackScrollObject obj:eventList){
                           if (isLaterDate(obj.time,currentTime)){
                               populateScrollView(obj.event_name,parentlayout,user_email);
                           }
                       }
                   }
                   else{
                       Toast.makeText(AttendedEventsActivity.this,"User has no attended events",Toast.LENGTH_SHORT).show();
                   }


               }


               @Override
               public void onCancelled(@NonNull DatabaseError error) {


               }
           });
            home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    backToHome();
                }
            });
       }
       else{
           //go to login?
           Toast.makeText(AttendedEventsActivity.this,"no user found",Toast.LENGTH_SHORT).show();
           openLoginActivity();
       }
    }

    private void openLoginActivity() {
        Intent intent = new Intent(AttendedEventsActivity.this, LoginActivityView.class);
        startActivity(intent);
        finish();
    }
    private void backToHome(){
        Intent intent = new Intent(AttendedEventsActivity.this,StudentHomeActivity.class);
        startActivity(intent);
        finish();
    }
    /////////////////////////////////////////////////////////////////////////////////////
    public TextView createTextView(String text){
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setPadding(5, 5, 5, 5);
        textView.setTextSize(25);
        ViewGroup.MarginLayoutParams textLayoutParams = new ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        textLayoutParams.setMargins(1, 10, 1, 1);
        textView.setLayoutParams(textLayoutParams);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.WHITE);
        return textView;
    }
    public CardView createCardView(){
        CardView cardView = new CardView(this);
        cardView.setCardElevation(10);
        cardView.setRadius(16);
        cardView.setPadding(20, 40, 20, 20);
        ViewGroup.MarginLayoutParams p = new ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                200
        );
        p.setMargins(5, 8, 5, 8);
        cardView.setLayoutParams(p);
        cardView.setBackgroundColor(Color.rgb(51, 81, 88));
        cardView.setForegroundGravity(Gravity.CENTER);
        return cardView;
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    //Add the database events to the schroll view
    public void populateScrollView(String eventName, LinearLayout L, String ID) {
        TextView textView = createTextView(eventName);
        CardView cardView = createCardView();
        cardView.addView(textView);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFeedbackForm(eventName);
            }
        });
        L.addView(cardView);
    }

    private void openFeedbackForm(String name) {
        Intent intent = new Intent(AttendedEventsActivity.this, StudentFeedbackActivity.class);
        intent.putExtra("NAME", name);
        startActivity(intent);
    }
    //Listen for any update to the database event structure, to update scrowwView


    //Need a function to delete and refresh all the scrollable cardviews
    public String getTimeAsString(LocalDateTime t) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("E, MMM dd, yyyy, HH:mm");
        return t.format(format);
    }


    public boolean isLaterDate(String date1, String date2) {
        String date1year = date1.substring(11, 15);
        String date2year = date2.substring(11, 15);
        String date1month = date1.substring(3, 6);
        String date2month = date2.substring(3, 6);
        String date1day = date1.substring(7, 9);
        String date2day = date2.substring(7, 9);
        if (date1year.compareTo(date2year) > 0) {
            return true;
        } else if (date1year.compareTo(date2year) == 0) {
            if (earlierMonth(date2month, date1month) > 0) {
                return true;
            } else if (date1month.compareTo(date2month) == 0) {
                if (date1day.compareTo(date2day) > 0) {
                    return true;
                } else if (date1day.compareTo(date2day) <= 0) {
                    return false;
                }
            }
            return false;
        }
        return false;
    }

    public int earlierMonth(String month1, String month2) {
        String[] list = new String[12];
        list[0] = "Jan";
        list[1] = "Feb";
        list[2] = "Mar";
        list[3] = "Apr";
        list[4] = "May";
        list[5] = "Jun";
        list[6] = "Jul";
        list[7] = "Aug";
        list[8] = "Sep";
        list[9] = "Oct";
        list[10] = "Nov";
        list[11] = "Dec";


        int index1 = getArrayIndex(list, month1);
        int index2 = getArrayIndex(list, month2);
        if (index1 < index2) {
            return -1;
        } else if (index1 == index2) {
            return 0;
        }
        return 1;
    }


    public int getArrayIndex(String[] s, String x) {
        int len = s.length;
        int result = 0;
        for (int i = 0; i < len; i++) {
            if (s[i] == x) {
                result = i;
            }
        }
        return result;
    }

}
