package com.example.b07_group_19;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ComplaintAdapter extends RecyclerView.Adapter<ComplaintAdapter.ComplaintViewHolder> {

    private List<StudentComplaint> complaints;
    private LayoutInflater inflater;

    public ComplaintAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ComplaintViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.complaint_item, parent, false);
        return new ComplaintViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ComplaintViewHolder holder, int position) {
        if (complaints != null) {
            StudentComplaint current = complaints.get(position);
            holder.titleTextView.setText(current.getTitle());
            holder.descriptionTextView.setText(current.getDescription());
        }
    }

    @Override
    public int getItemCount() {
        return (complaints != null) ? complaints.size() : 0;
    }

    public void setComplaints(List<StudentComplaint> complaints) {
        this.complaints = complaints;
        notifyDataSetChanged();
    }

    static class ComplaintViewHolder extends RecyclerView.ViewHolder {
        private final TextView titleTextView;
        private final TextView descriptionTextView;

        private ComplaintViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
        }
    }
}
