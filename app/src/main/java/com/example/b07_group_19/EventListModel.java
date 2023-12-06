package com.example.b07_group_19;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
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
import java.util.List;

public class EventListModel {

    FirebaseDatabase db;
    List<Event> events = new ArrayList<>();
    public EventListModel(){
        db = FirebaseDatabase.getInstance();
    }
    public void getEventInfo(EventListPresenter presenter){
        DatabaseReference eventReference = FirebaseDatabase.getInstance().getReference("events");
        Query eventQuery = eventReference.orderByChild("time");

        eventQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //This... probably works?
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
                    events.add(newEvent);
                    presenter.updateEventList(events);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                presenter.eventLoadFailed();
            }
        });

        eventReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
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
                    events.add(newEvent);
                    presenter.updateEventList(events);
                }
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                //aaaaaauuuuggghhhh I don't wanna
                presenter.updateEventList(events);
            }

            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                //This actually does not affect the listView at all
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
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
                    events.add(newEvent);
                    presenter.updateEventList(events);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                presenter.eventLoadFailed();
            }
        });
    }
}
