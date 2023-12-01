package com.example.b07_group_19;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
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
    private ArrayList<FeedbackScrollObject> eventList = new ArrayList<>();

    //UNDO
    private ArrayList<String> userEmails = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attended_events);

        db = FirebaseDatabase.getInstance();
        event_ref = db.getReference().child("events");
        mAuth = FirebaseAuth.getInstance();
        LinearLayout parentlayout = findViewById(R.id.layout1);
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null){
             //String user_email = user.getEmail();
             String password = "bob123";
            //Query query = event_ref.orderByChild("rvspList").equalTo(user_email);
            DatabaseReference user_ref = db.getReference().child("users");
            //Query query2 = user_ref.orderByChild("password").equalTo(password);

            user_ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        for(DataSnapshot userSnapshot: snapshot.getChildren()){
                            String password1 = userSnapshot.child("password").getValue(String.class);
                            if (password1.equals(password)){
                                String email = userSnapshot.child("email").getValue(String.class);
                                userEmails.add(email);
                            }
                            //FeedbackScrollObject obj = new FeedbackScrollObject(password, email);
                            //eventList.add(obj);
                        }
                        //add under here
                        for(String email:userEmails){
                            populateScrollView(email, parentlayout);
                        }
                    }
                    else{
                        Toast.makeText(AttendedEventsActivity.this,"Currently no users",Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            //UNDO
            /*query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()){
                        for (DataSnapshot eventSnapshot : snapshot.getChildren()) {
                            // Access the event data here
                            String eventName = eventSnapshot.getKey();
                            LocalDateTime eventDate = eventSnapshot.child("LocalDateTime").getValue(new GenericTypeIndicator<LocalDateTime>() {});
                            String time = getTimeAsString(eventDate);
                            FeedbackScrollObject obj = new FeedbackScrollObject(eventName, time);
                            eventList.add(obj);
                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });*/

            //Now, I should have an arrayList of object containing the title and date of events who the current user is rvsp for
            //I need to now check that arrayList for any events that have passed, as that means they are "attended"
            //int len = userEmails.size();
            //String size = Integer.toString(len);
            //Toast.makeText(AttendedEventsActivity.this,"The useremail List has lenght" + size,Toast.LENGTH_SHORT).show();
            //for (FeedbackScrollObject obj: eventList){
            //    populateScrollView(obj.event_name,obj.time,parentlayout);
            //}
            //UNDO
            /*LocalDateTime currentTime = LocalDateTime.now();
            String currentTimeString = getTimeAsString(currentTime);
            for (FeedbackScrollObject obj: eventList){
                if(isLaterDate(obj.time,currentTimeString)){
                    //Now call add to scroll view function
                    populateScrollView(obj.event_name, obj.time, parentlayout);
                }
            }*/
        }
        else{
            //go to login?
            Toast.makeText(AttendedEventsActivity.this,"no user found",Toast.LENGTH_SHORT).show();
        }
        //I want an array of simple object which I will use to populate the scroll View




    }

    //Add the database events to the schroll view
    public void populateScrollView(String eventName, LinearLayout L){
        //create new linear layout
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        //create text for the card
        TextView titleview = new TextView(this);
        titleview.setText(eventName);
        titleview.setPadding(5,5,5,5);
        titleview.setTextSize(25);
        ViewGroup.MarginLayoutParams textLayoutParams = new ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        textLayoutParams.setMargins(1,10,1,1);
        titleview.setLayoutParams(textLayoutParams);
        titleview.setGravity(Gravity.CENTER);
        titleview.setTextColor(Color.WHITE);
        //create text for the card
        //Create card
        CardView cardView = new CardView(this);
        cardView.setCardElevation(10);
        cardView.setRadius(16);
        cardView.setPadding(20,40,20,20);
        ViewGroup.MarginLayoutParams p = new ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                200
        );
        p.setMargins(5,5,5,5);
        cardView.setLayoutParams(p);
        cardView.setBackgroundColor(Color.rgb(51,81,88));
        cardView.addView(titleview);
        cardView.setForegroundGravity(Gravity.CENTER);
        //add card to the linearlayout
        //layout.addView(cardView);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        L.addView(cardView);
    }
    //Listen for any update to the database event structure, to update scrowwView

    //Need a function to delete and refresh all the scrollable cardviews
    public String getTimeAsString(LocalDateTime t){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("E, MMM dd, yyyy, HH:mm");
        return t.format(format);
    }

    public boolean isLaterDate(String date1, String date2){
        String date1year = date1.substring(11,15);
        String date2year = date2.substring(11, 15);
        String date1month = date1.substring(3,6);
        String date2month = date2.substring(3,6);
        String date1day = date1.substring(7,9);
        String date2day = date2.substring(7,9);
        if (date1year.compareTo(date2year) >0) {
            return true;
        }
        else if(date1year.compareTo(date2year)==0){
            if(earlierMonth(date2month,date1month) >0){
                return true;
            }
            else if(date1month.compareTo(date2month)==0){
                if(date1day.compareTo(date2day)>0){
                    return true;
                }
                else if(date1day.compareTo(date2day)<=0){
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
        if(index1 < index2){
            return -1;
        }
        else if(index1 == index2){
            return 0;
        }
        return 1;
    }

    public int getArrayIndex(String[] s, String x){
        int len = s.length;
        int result=0;
        for (int i = 0;i<len;i++){
            if(s[i] == x){
                result = i;
            }
        }
        return result;
    }


}
