package com.example.b07_group_19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
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
    private FirebaseDatabase db;
    private DatabaseReference ref;
    private RadioButton RButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_feedback);

        String event_name = getIntent().getStringExtra("NAME");
        String student_email = getIntent().getStringExtra("EMAIL");
        submit_btn = (Button)findViewById(R.id.submit_form);
        RG = findViewById(R.id.radio_group);
        db = FirebaseDatabase.getInstance();
        ref = db.getReference();
        DatabaseReference eventRevRef = ref.child("eventFeedback");
        summaryText = findViewById(R.id.review_summary);
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
                    DatabaseReference eventNameRef = eventRevRef.child(event_name);
                    DatabaseReference studentRef = eventNameRef.child(student_email);
                    studentRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                //student of this email already gave feedback
                                Toast.makeText(StudentFeedbackActivity.this,"Submission already made for this event",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                EventReviewObject revobj = new EventReviewObject(Summary,rating);
                                studentRef.setValue(revobj);
                                Toast.makeText(StudentFeedbackActivity.this,"Feedback Successful",Toast.LENGTH_SHORT).show();

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