package com.example.b07_group_19;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class CreateAnnouncementView extends AppCompatActivity {
    private EditText title;
    private EditText message;
    private Button publishAnnouncement;
    private Button cancelAnnouncement;

    private CreateAnnouncementPresenter presenter;


    public void returnHome(){
        Intent intent = new Intent(CreateAnnouncementView.this, AdminHomeActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.announcement_creation_view);

        cancelAnnouncement = findViewById(R.id.cancel_announcement_button);
        cancelAnnouncement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnHome();
            }
        });

        publishAnnouncement = findViewById(R.id.publish_announcement_button);
        title = findViewById(R.id.announcementTitleInput);
        message = findViewById(R.id.messageInput);

        presenter = new CreateAnnouncementPresenter(new CreateAnnouncementModel(), this);
        publishAnnouncement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Announcement announcement = new Announcement();
                announcement.setTitle(title.getText().toString());
                announcement.setMessage(message.getText().toString());
                presenter.publishAnnouncement(announcement);
            }
        });
    }

    public void showAnnouncementSuccessful(){
        Intent intent = new Intent(CreateAnnouncementView.this, SuccessfulAnnouncementActivity.class);
        startActivity(intent);
    }

    public void publishAnnouncement(Announcement announcement){
        showAnnouncementSuccessful();
    }
}
