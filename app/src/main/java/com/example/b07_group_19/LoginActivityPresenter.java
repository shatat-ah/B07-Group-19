package com.example.b07_group_19;

import android.text.TextUtils;

public class LoginActivityPresenter implements PresenterInterface{

    LoginActivityView view;
    LoginActivityModel model;
    public LoginActivityPresenter(LoginActivityView view, LoginActivityModel model){
        this.model = model;
        this.view = view;
    }


    public void checkDBuser(String username, String role, String password) {
        if(username.equals("") || password.equals("")){
            view.missingField();
        }
        else{
            model.queryDB(this, username, role, password);
        }
    }
    @Override
    public void displayResult(boolean exists, String role) {
        if (exists){
            view.userExist(role);
        }
        else{
            view.userNotFound();
        }
    }
    @Override
    public void authError(){
        view.authError();
    }
}
