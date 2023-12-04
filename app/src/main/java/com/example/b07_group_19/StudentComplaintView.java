package com.example.b07_group_19;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class StudentComplaintView extends AppCompatActivity {
    private StudentComplaintPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints_student);

        presenter = new StudentComplaintPresenter();

        TextInputLayout inputTitle = findViewById(R.id.inputTitle);
        TextInputLayout inputDescription = findViewById(R.id.inputDescription);
        Button submitButton = findViewById(R.id.submitBtn);
        Button backBtn = findViewById(R.id.backBtn);

        submitButton.setOnClickListener(v -> {
            String title = inputTitle.getEditText().getText().toString().trim();
            String description = inputDescription.getEditText().getText().toString().trim();

            if (!title.isEmpty() && !description.isEmpty()) {
                presenter.submitComplaint(title, description);
                Toast.makeText(this, "Complaint submitted", Toast.LENGTH_SHORT).show();
                // Optionally, clear the input fields after submission
                inputTitle.getEditText().setText("");
                inputDescription.getEditText().setText("");
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
