package com.example.b07_group_19;

public class LoginActivityPresenter implements PresenterInterface{

    LoginActivityView view;
    LoginActivityModel model;
    public LoginActivityPresenter(LoginActivityView view, LoginActivityModel model){
        this.model = model;
        this.view = view;
    }


    public void checkDBuser(String username, String role, String password) {
        model.queryDB(this, username, role, password);
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
}
