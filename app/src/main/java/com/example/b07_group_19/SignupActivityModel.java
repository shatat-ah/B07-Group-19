package com.example.b07_group_19;

import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SignupActivityModel {

    private FirebaseDatabase db;
    private FirebaseAuth mAuth;
    public SignupActivityModel(){

        db = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
    }
    public void addDBuser(SignupActivityPresenter presenter, String email, String username, String password, String role){
        DatabaseReference ref= db.getReference();
        DatabaseReference user_ref = ref.child("users");
        Query query1 = user_ref.orderByChild("email").equalTo(email);

        query1.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                        String role2 = userSnapshot.child("role").getValue(String.class);
                        presenter.displayResult(true, role2);
                        return;
                    }
                }
                else {
                    user_ref.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                presenter.uniqueName();
                            }
                            else{
                                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if(task.isSuccessful()){
                                            //FirebaseUser user = mAuth.getCurrentUser();
                                            User new_user = new User(email, password, username, role);
                                            user_ref.child(username).setValue(new_user);
                                            presenter.displayResult(false, role);
                                        }
                                        else{
                                            presenter.authError();
                                        }
                                    }
                                });
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
