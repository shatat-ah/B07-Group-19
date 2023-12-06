package com.example.b07_group_19;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDateTime;
import java.util.List;

public class EventListView extends AppCompatActivity{

    public ListView eventsListView;
    public List<Event> eventsList;
    private TextView backHome;
    EventListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) 
        eventsList = new ArrayList<Event>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_register_view);

        presenter = new EventListPresenter(this, new EventListModel());
        eventsListView = findViewById(R.id.active_event_list);
        backHome = findViewById(R.id.switch_student_home);
        presenter.getEventList();
        ArrayAdapter<Event> eventArrayAdapter = new ArrayAdapter<Event>(this, android.R.layout.simple_list_item_1, eventsList);
        eventsListView.setAdapter(eventArrayAdapter);
        eventsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                openEventDetails(i);
            }
        });

        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHome();
            }
        });
    }

    public void openEventDetails(int i) {
        Intent intent = new Intent(this, EventDetailsView.class);
        intent.putExtra("Title", eventsList.get(i).getTitle());
        startActivity(intent);
    }

    public void eventLoadFailed() {
        Toast.makeText(EventListView.this, "Failed to update events", Toast.LENGTH_SHORT).show();
    }

    public void openHome() {
        Intent intent = new Intent(EventListView.this, AdminHomeActivity.class);
        startActivity(intent);
        finish();
    }
}
