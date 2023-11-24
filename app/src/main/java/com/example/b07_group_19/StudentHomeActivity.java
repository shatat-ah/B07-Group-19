package com.example.b07_group_19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StudentHomeActivity extends AppCompatActivity {

    private CardView post_card;
    private CardView even_card;
    private CardView compl_card;
    private CardView announce_card;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);

        post_card = findViewById(R.id.post_requirements_card);
        post_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(this, );
                //startActivity(intent);
            }
        });

        even_card = findViewById(R.id.events_card);
        even_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(this, );
                //startActivity(intent);
            }
        });
        compl_card = findViewById(R.id.complaints_card);
        compl_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(this, );
                //startActivity(intent);
            }
        });
        announce_card = findViewById(R.id.announcement_card);
        announce_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(this, );
                //startActivity(intent);
            }
        });
    }
}