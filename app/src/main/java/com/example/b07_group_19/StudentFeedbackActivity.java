package com.example.b07_group_19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class StudentFeedbackActivity extends AppCompatActivity {




    private Button submit_btn, back;
    private EditText summaryText;
    private RadioGroup RG;
    private FirebaseDatabase db;
    private RadioButton RButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_feedback);




        String event_name = getIntent().getStringExtra("NAME");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String id = user.getUid();
        submit_btn = (Button)findViewById(R.id.submit_btn);
        back = (Button)findViewById(R.id.close_btn);
        RG = findViewById(R.id.radio_group);
        db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference().child("feedback");
        // Calling the child method to ensure that eventFeedback node exists 1st
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
                }
                else {
                    Query query = ref.child(event_name).child(id);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                queryResult(true);
                            }
                            else{
                                EventReviewObject obj = new EventReviewObject(Summary,rating);
                                ref.child(event_name).child(id).setValue(obj);
                                queryResult(false);
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {


                        }
                    });
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToAttendedEvents();
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


    public void backToAttendedEvents(){
        Intent intent = new Intent(StudentFeedbackActivity.this,AttendedEventsActivity.class);
        startActivity(intent);
        finish();
    }


    public void queryResult(boolean found){
        if(found==true){
            Toast.makeText(StudentFeedbackActivity.this,"User has already submitted feedback",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(StudentFeedbackActivity.this,"Feedback submission successful!",Toast.LENGTH_SHORT).show();
        }
        backToAttendedEvents();
        finish();
    }
}
