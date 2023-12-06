package com.example.b07_group_19;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EventDetailsModel {

    FirebaseDatabase db;
    public EventDetailsModel(){
        db = FirebaseDatabase.getInstance();
    }
    public void getEventInfo(EventDetailsPresenter presenter, String title){
        DatabaseReference eventReference = FirebaseDatabase.getInstance().getReference("events");
        Query eventQuery = eventReference.orderByChild("title").equalTo(title).limitToFirst(1);


        eventQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if(snapshot.exists()){
                    presenter.eventLoadFailed();
                }
                else {
                    eventReference.child(title).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                for(DataSnapshot eventIndex : snapshot.getChildren()) {
                                    String newTitle = eventIndex.child("title").getValue(String.class);
                                    String newDesc = eventIndex.child("description").getValue(String.class);
                                    String newCreator = eventIndex.child("creator").getValue(String.class);
                                    String newDepartment = eventIndex.child("department").getValue(String.class);
                                    int newMaxParticipants = eventIndex.child("maxParticipants").getValue(Integer.class);
                                    String newTime = eventIndex.child("timeAsString").getValue(String.class);
                                    DateTimeFormatter format = DateTimeFormatter.ofPattern("E, MMM dd, yyyy, HH:mm");
                                    LocalDateTime newTimeFormat = LocalDateTime.parse(newTime, format);
                                    //This is absolutely disgusting, but it should work
                                    Event newEvent = new Event(newTitle, newDesc, newCreator, newDepartment, newMaxParticipants, newTimeFormat.getDayOfMonth(), newTimeFormat.getMonthValue(), newTimeFormat.getYear(), newTimeFormat.getHour(), newTimeFormat.getMinute());
                                    presenter.updateEventInfo(newEvent);
                                }
                            }
                            else{
                                presenter.eventNotFound();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }

    public void updateDbEvent(EventDetailsPresenter presenter, Event event) {
        DatabaseReference reference = db.getReference();
        DatabaseReference eventReference = reference.child("events");
        Query query = eventReference.orderByChild("title").equalTo(event.getTitle());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                eventReference.child(event.getTitle()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            eventReference.child(event.getTitle()).setValue(event);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                presenter.eventNotFound();
            }
        });
    }

    public String getUserEmail(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        return user.getEmail();
    }
}
