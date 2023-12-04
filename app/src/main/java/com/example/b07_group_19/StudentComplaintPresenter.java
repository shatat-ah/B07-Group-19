package com.example.b07_group_19;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StudentComplaintPresenter {
    private DatabaseReference databaseReference;

    public StudentComplaintPresenter(){
        databaseReference = FirebaseDatabase.getInstance().getReference().child("complaints");
    }

    public void submitComplaint(String title, String description) {
        StudentComplaint complaint = new StudentComplaint(title, description);
        databaseReference.push().setValue(complaint);
    }
}
