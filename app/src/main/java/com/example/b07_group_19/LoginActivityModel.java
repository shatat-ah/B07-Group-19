package com.example.b07_group_19;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class LoginActivityModel {

    FirebaseDatabase db;
    public LoginActivityModel(){
        db = FirebaseDatabase.getInstance();
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
                    String attached_password = userinfo.password;
                    String attached_role = userinfo.role;
                    if(attached_password.equals(password) && attached_role.equals(role)){
                        presenter.displayResult(true, role);
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
