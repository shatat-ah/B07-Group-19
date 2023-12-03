package com.example.b07_group_19;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class AdminComplaintPresenter {
    private DatabaseReference databaseReference;
    private List<StudentComplaint> complaintsList;
    private AdminComplaintView view;

    public AdminComplaintPresenter(AdminComplaintView view) {
        this.view = view;
        databaseReference = FirebaseDatabase.getInstance().getReference().child("complaints");
        complaintsList = new ArrayList<>();
    }

    public void fetchComplaints() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                complaintsList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String title = snapshot.child("title").getValue(String.class);
                    String description = snapshot.child("description").getValue(String.class);

                    StudentComplaint complaint = new StudentComplaint("Title: " + title, "Description: " + description);
                    complaintsList.add(complaint);
                }
                view.displayComplaints(complaintsList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle an error fetching complaints
            }
        });
    }
}
