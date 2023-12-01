package com.example.b07_group_19;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class CreateAnnouncementView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.announcement_creation_view);

        Button cancelAnnouncement = findViewById(R.id.cancel_announcement_button);
        cancelAnnouncement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateAnnouncementView.this, AdminHomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
