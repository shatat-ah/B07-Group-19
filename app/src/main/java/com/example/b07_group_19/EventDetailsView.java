package com.example.b07_group_19;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class EventDetailsView extends AppCompatActivity{

    public TextView eventTitle, eventDesc, eventTime, eventRemainingSpaces;
    public Button registerButton;
    public boolean buttonActive;
    private TextView exitDetails;
    Event currentEvent;
    EventDetailsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details_view);

        Intent intent = getIntent();
        String title = intent.getExtras().getString("Title");
        presenter.getEventInfo(title);

        presenter = new EventDetailsPresenter(this, new EventDetailsModel());
        eventTitle = findViewById(R.id.event_details_title);
        eventDesc = findViewById(R.id.event_details_description);
        eventTime = findViewById(R.id.event_details_time);
        eventRemainingSpaces = findViewById(R.id.event_details_remaining_spaces);
        registerButton = findViewById(R.id.event_details_register_button);
        exitDetails = findViewById(R.id.exit_details_button);

        eventTitle.setText(currentEvent.getTitle());
        eventDesc.setText(currentEvent.getDescription());
        eventTime.setText(currentEvent.getTimeAsString());
        String eventSpaces = ((currentEvent.getMaxParticipants() - currentEvent.getCurrentParticipants()) + "/" + (currentEvent.getMaxParticipants()));
        eventRemainingSpaces.setText(eventSpaces);
        String email = presenter.getUserEmail();
        buttonActive = !(currentEvent.containsParticipant(email));
        registerButton.setBackgroundColor((buttonActive)?(Color.BLUE):(Color.GRAY));

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonActive) {
                    currentEvent.addParticipant(email);
                    presenter.updateDbEvent(currentEvent);
                    registerButton.setText("Cancel RSVP");
                    buttonActive = !buttonActive;
                } else {
                    currentEvent.removeParticipant(email);
                    registerButton.setText("RSVP");
                    buttonActive = !buttonActive;
                }
            }
        });

        exitDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeDetails();
            }
        });
    }

    public void eventLoadFailed() {
        Toast.makeText(EventDetailsView.this, "Failed to load event information", Toast.LENGTH_SHORT).show();
    }

    public void eventNotFound() {
        Toast.makeText(EventDetailsView.this, "Event could not be located", Toast.LENGTH_SHORT).show();
    }

    public void closeDetails() {
        finish();
    }
}
