package com.example.b07_group_19;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AnnouncementsActivity extends AppCompatActivity {
    private Button return_to_home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.announcements_page);

        return_to_home = findViewById(R.id.return_home);
        return_to_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        DatabaseReference dbr = FirebaseDatabase.getInstance().getReference("announcements");
        dbr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot announcementSnapshot : dataSnapshot.getChildren()) {
                    // Get the values for each announcement
                    String title = announcementSnapshot.child("title").getValue(String.class);
                    String message = announcementSnapshot.child("message").getValue(String.class);

                    // Do something with the data (e.g., display it in a TextView)
                    displayAnnouncement(title, true);
                    displayAnnouncement(message, false);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError dbE){}
        });

    }

    public void displayAnnouncement(String text, boolean Title){
        LinearLayout page = findViewById(R.id.announcements);
        LinearLayout announcements = new LinearLayout(this);

        if (Title){
            announcements.addView(createNewAnnouncementTextView(text, 30, 18, Title));
        } else {
            announcements.addView(createNewAnnouncementTextView(text, 24, 18, Title));
        }
        page.addView(announcements);

    }

    private TextView createNewAnnouncementTextView(String text, float size, int padding, boolean bold) {
        TextView textView = new TextView(this);
        textView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.VERTICAL));
        textView.setText(text);
        textView.setTextSize(size);
        textView.setPadding(padding, padding, padding, padding);

        if (bold){
            textView.setTypeface(null, Typeface.BOLD);
        }

        return textView;
    }
}
