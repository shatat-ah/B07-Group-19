package com.example.b07_group_19;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

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
                                Event eventOutput = (Event) snapshot.getValue();
                                presenter.updateEventInfo(eventOutput);
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
}
