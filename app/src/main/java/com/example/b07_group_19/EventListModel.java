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

import java.util.ArrayList;
import java.util.List;

public class EventListModel {

    FirebaseDatabase db;
    List<Event> events = new ArrayList<>();
    public EventListModel(){
        db = FirebaseDatabase.getInstance();
    }
    public List<Event> getEventInfo(EventListPresenter presenter){
        DatabaseReference eventReference = FirebaseDatabase.getInstance().getReference("events");
        Query eventQuery = eventReference.orderByChild("time");

        eventQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //This... probably works?
                for(DataSnapshot eventIndex : snapshot.getChildren()) {
                    Event newEvent = eventIndex.getValue(Event.class);
                    events.add(newEvent);
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
                Event newEvent = snapshot.getValue(Event.class);
                events.add(newEvent);
                presenter.updateEventList(events);
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
                Event lostEvent = snapshot.getValue(Event.class);
                events.remove(lostEvent);
                presenter.updateEventList(events);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                presenter.eventLoadFailed();
            }
        });
        return(events);
    }
}
