package com.example.b07_group_19;

import androidx.appcompat.app.AppCompatActivity;
import java.time.LocalDateTime;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class EventCreatorView extends AppCompatActivity{

    public EditText eventTitle, eventDescription, eventParticipants;
    public DatePicker eventDate;
    public TimePicker eventTime;
    private Spinner departmentPicker;
    private TextView backHome;
    private Button buttonCreate;

    EventCreatorPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_creator_view);

        presenter = new EventCreatorPresenter(this, new EventCreatorModel());
        eventTitle = findViewById(R.id.event_title);
        eventDescription = findViewById(R.id.event_description);
        eventParticipants = findViewById(R.id.event_participant_limit);
        eventDate = findViewById(R.id.event_date);
        eventTime = findViewById(R.id.event_time);
        buttonCreate = (Button) findViewById(R.id.event_create);
        backHome = findViewById(R.id.switch_admin_home);
        departmentPicker = findViewById(R.id.department_picker);

        String[] stringArray = getResources().getStringArray(R.array.departmentItems);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, stringArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        departmentPicker.setAdapter(adapter);

        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHome();
            }
        });

        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title, description, creator;
                String department = "CS";
                int maxParticipants, day, month, year, hour, minute;
                title = String.valueOf(eventTitle.getText());
                description = String.valueOf(eventDescription.getText());
                maxParticipants = Integer.parseInt(eventParticipants.getText().toString()); //There has to be a better way of doing this
                creator = presenter.getEmail();
                day = eventDate.getDayOfMonth();
                month = eventDate.getMonth();
                year = eventDate.getYear();
                hour = eventTime.getHour();
                minute = eventTime.getMinute();
                switch (departmentPicker.getSelectedItemPosition()) {
                    default:
                        department = "CS";
                        break;
                    case 1:
                        department = "Math";
                        break;
                    case 2:
                        department = "Statistics";
                        break;
                }
                if (TextUtils.isEmpty(title) || TextUtils.isEmpty(description)) {
                    missingField();
                } else if (!(maxParticipants > 0)) {
                    invalidParticipantLimit();
                } else if (LocalDateTime.now().isAfter(LocalDateTime.of(year, month, day, hour, minute))) {
                    invalidTime();
                }else{
                    createEvent(title, description, creator, department, maxParticipants, day, month, year, hour, minute);
                }

            }
        });

    }

    public void createEvent(String title, String description, String creator, String department, int maxParticipants, int day, int month, int year, int hour, int minute){
        presenter.createNewEvent(title, description, creator, department, maxParticipants, day, month, year, hour, minute);
    }

    public void eventCreated(){
        Toast.makeText(EventCreatorView.this, "Successfully Registered Event", Toast.LENGTH_SHORT).show();
        openHome();
    }
    public void openHome(){
        Intent intent = new Intent(EventCreatorView.this, AdminHomeActivity.class);
        startActivity(intent);
        finish();
    }

    public void missingField() {
        Toast.makeText(EventCreatorView.this, "Missing fields", Toast.LENGTH_SHORT).show();
    }

    public void invalidParticipantLimit() {
        Toast.makeText(EventCreatorView.this, "Participant limit must be at least 1", Toast.LENGTH_SHORT).show();
    }

    public void invalidTime() {
        Toast.makeText(EventCreatorView.this, "Event time is before current time", Toast.LENGTH_SHORT).show();
    }

    public void eventFound() {
        Toast.makeText(EventCreatorView.this, "Event already exists", Toast.LENGTH_SHORT).show();
    }

    public void eventCreateFailed() {
        Toast.makeText(EventCreatorView.this, "Failed to create event", Toast.LENGTH_SHORT).show();
    }
}
