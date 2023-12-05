package com.example.b07_group_19;

import android.widget.Toast;

public class CreateAnnouncementPresenter {
    private CreateAnnouncementModel model;
    private CreateAnnouncementView view;

    public CreateAnnouncementPresenter(CreateAnnouncementModel model, CreateAnnouncementView view){
        this.model = model;
        this.view = view;
    }

    public void publishAnnouncement(Announcement announcement){
        if (announcement.getTitle().isEmpty()){
            Toast.makeText(view.getApplicationContext(), "Announcement creation unsuccessful; title cannot be empty.", Toast.LENGTH_SHORT).show();
            view.fail();
        } else {
            if (announcement.getMessage().isEmpty()){
                announcement.setMessage("(Empty announcement)");
            }

            model.addAnnouncement(announcement);
            view.success(announcement);
        }
    }
}
