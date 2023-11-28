package com.example.b07_group_19;

import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SignupActivityModel {

    FirebaseDatabase db;
    public SignupActivityModel(){
        db = FirebaseDatabase.getInstance();
    }
    public void addDBuser(SignupActivityPresenter presenter, String email, String username, String password, String role){
        DatabaseReference ref= db.getReference();
        DatabaseReference user_ref = ref.child("users");
        Query query1 = user_ref.orderByChild("email").equalTo(email);

        query1.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if(snapshot.exists()){
                    presenter.accountFound();
                }
                else {
                    user_ref.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                presenter.displayResult(snapshot.exists());
                            }
                            else{
                                User new_user = new User(email, password, username, role);
                                user_ref.child(username).setValue(new_user);
                                presenter.displayResult(snapshot.exists());
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }
}
