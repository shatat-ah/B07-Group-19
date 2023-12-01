package com.example.b07_group_19;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateAnnouncementModel {
    private DatabaseReference dbr;

    public CreateAnnouncementModel() {
        dbr = FirebaseDatabase.getInstance().getReference().child("announcements");
    }

    void addAnnouncement(Announcement announcement){
        dbr.push().setValue(announcement.getMessage());
    }
}
