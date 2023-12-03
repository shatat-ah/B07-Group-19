package com.example.b07_group_19;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdminComplaintView extends AppCompatActivity {

    private AdminComplaintPresenter presenter;
    private RecyclerView recyclerView;
    private ComplaintAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints_admin);

        presenter = new AdminComplaintPresenter(this);

        recyclerView = findViewById(R.id.recyclerView);
        Button complaintsAdminBackBtn = findViewById(R.id.complaintsAdminBackBtn);

        adapter = new ComplaintAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        presenter.fetchComplaints();

        complaintsAdminBackBtn.setOnClickListener(v -> onBackPressed());
    }

    public void displayComplaints(List<StudentComplaint> complaints) {
        adapter.setComplaints(complaints);
    }
}
