package com.example.b07_group_19;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class StudentFeedbackActivity extends AppCompatActivity {


    private Button submit_btn;
    private EditText summaryText;
    private RadioGroup RG;
    private FirebaseDatabase db;
    private DatabaseReference eventRevRef;
    private RadioButton RButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_feedback);


        String event_name = getIntent().getStringExtra("NAME");
        String student_email = getIntent().getStringExtra("EMAIL");
        submit_btn = findViewById(R.id.submit_form);
        RG = findViewById(R.id.radio_group);
        db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference();
        // Calling the child method to ensure that eventFeedback node exists 1st
        eventRevRef = ref.child("eventFeedback").child(event_name);
        summaryText = findViewById(R.id.review_summary);


        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String checked = getChecked(v);
                int rating = Integer.parseInt(checked);
                String Summary = summaryText.getText().toString();


                //check if student user did not fill any fields
                if (TextUtils.isEmpty(Summary) && rating == -1) {
                    Toast.makeText(StudentFeedbackActivity.this, "Choose rating and/or enter summary", Toast.LENGTH_SHORT).show();
                } else {
                    Query query = eventRevRef.child("email").equalTo(student_email);
                    EventReviewObject revobj = new EventReviewObject(Summary, rating);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                Toast.makeText(StudentFeedbackActivity.this, "User already submitted feedback for this event", Toast.LENGTH_SHORT).show();
                            } else {
                                eventRevRef.child(student_email).setValue(revobj);
                                Toast.makeText(StudentFeedbackActivity.this, "Feedback submission successful!", Toast.LENGTH_SHORT).show();
                            }
                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {


                        }
                    });
                }
            }
        });
    }


    public String getChecked(View v){
        int checkedButton  = RG.getCheckedRadioButtonId();
        if(checkedButton == -1){
            return "-1";
        }
        else{
            RButton = findViewById(checkedButton);
            return RButton.getText().toString();
        }
    }






}
