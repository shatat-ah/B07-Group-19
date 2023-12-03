package com.example.b07_group_19;

import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class EventCreatorModel {

    FirebaseDatabase db;
    public EventCreatorModel(){
        db = FirebaseDatabase.getInstance();
    }
    public void addDbEvent(EventCreatorPresenter presenter, String title, String description, String creator, String department, int maxParticipants, int day, int month, int year, int hour, int minute){
        DatabaseReference reference = db.getReference();
        DatabaseReference eventReference = reference.child("events");
        Query query = eventReference.orderByChild("title").equalTo(title);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                eventReference.child(title).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            presenter.displayResult(snapshot.exists());
                        }
                        else{
                            Event new_event = new Event(title, description, creator, department, maxParticipants, day, month, year, hour, minute);
                            eventReference.child(title).setValue(new_event);
                            presenter.displayResult(snapshot.exists());
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        presenter.eventCreateFailed();
                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                presenter.eventCreateFailed();
            }
        });
    }
}
