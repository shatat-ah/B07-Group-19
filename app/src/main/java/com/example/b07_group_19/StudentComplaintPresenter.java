package com.example.b07_group_19;

import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

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
