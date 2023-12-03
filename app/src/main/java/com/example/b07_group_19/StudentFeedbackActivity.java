package com.example.b07_group_19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class StudentFeedbackActivity extends AppCompatActivity {

    private Button submit_btn;
    private EditText summaryText;
    private  RadioGroup RG;
    private RadioButton RButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_feedback);

        submit_btn = (Button)findViewById(R.id.submit_form);
        RG = findViewById(R.id.radio_group);
        DatabaseReference eventRevRef = FirebaseDatabase.getInstance().getReference().child("event-feedback");
        summaryText = findViewById(R.id.review_summary);
        String event_name = getIntent().getStringExtra("NAME");
        String student_email = getIntent().getStringExtra("EMAIL");
        Toast.makeText(StudentFeedbackActivity.this,"The student email is " + student_email + " and the event name is " + event_name,Toast.LENGTH_SHORT).show();
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String checked = getChecked(v);
                int rating = Integer.parseInt(checked);
                String Summary = summaryText.getText().toString();
                //check if the form is empty
                if (TextUtils.isEmpty(Summary) && rating == -1){
                    Toast.makeText(StudentFeedbackActivity.this,"Choose rating and/or enter summary",Toast.LENGTH_SHORT).show();
                }
                else{
                    Query query = eventRevRef.child(event_name).orderByChild("email").equalTo(student_email);
                    EventReviewObject revobj = new EventReviewObject(Summary,rating,student_email);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                //The student has already made a previous review for this event
                                Toast.makeText(StudentFeedbackActivity.this,"Submission already made for this event",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                //The student has never made a review for this event
                                eventRevRef.child(event_name).child(student_email).setValue(revobj);
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