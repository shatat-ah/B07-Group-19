package com.example.b07_group_19;

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

import java.util.Objects;

public class LoginActivityModel {

    private FirebaseDatabase db;
    private FirebaseAuth mAuth;
    public LoginActivityModel(){

        db = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
    }

    public void queryDB(LoginActivityPresenter presenter, String username, String role, String password){
        DatabaseReference ref= db.getReference();
        DatabaseReference username_ref = ref.child("users");

        username_ref.child(username).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //if we find that the user name exist
                if(snapshot.exists()){
                    User userinfo = snapshot.getValue(User.class);
                    String attached_password = userinfo.getPassword();
                    String attached_role = userinfo.getRole();
                    if(attached_password.equals(password) && attached_role.equals(role)){
                        mAuth.signInWithEmailAndPassword(userinfo.getEmail(), password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()) {
                                    presenter.displayResult(true, role);
                                }
                                else{
                                    presenter.authError();
                                }
                            }
                        });
                    }
                    else{
                        presenter.displayResult(false, role);
                    }
                }
                else{
                    presenter.displayResult(snapshot.exists(), role);
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }
}
