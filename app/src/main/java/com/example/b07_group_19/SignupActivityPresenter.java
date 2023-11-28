package com.example.b07_group_19;

public class SignupActivityPresenter {

    SignupActivityView view;
    SignupActivityModel model;
    public SignupActivityPresenter(SignupActivityView view, SignupActivityModel model){
        this.model = model;
        this.view = view;
    }

    public void accountFound() {
        view.accountFound();
    }

    public void create_new_user(String email, String username, String password, String role) {

        model.addDBuser(this, email, username, password, role);
    }

    public void displayResult(boolean exist){
        if(exist){
            view.uniqueUsername();
        }
        else{
            view.user_created();
        }
    }
}
