package com.example.b07_group_19;

import android.content.Intent;

import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class SuccessfulAnnouncementActivity extends AppCompatActivity{
    Button returnHome;
    public void returnHome(){
        Intent intent = new Intent(SuccessfulAnnouncementActivity.this, AdminHomeActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.announcement_creation_successful);

        returnHome = findViewById(R.id.return_home_From_announcement);
        returnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnHome();
            }
        });
    }
}
