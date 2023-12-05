package com.example.b07_group_19;

public class SignupActivityPresenter implements PresenterInterface{

    SignupActivityView view;
    SignupActivityModel model;
    public SignupActivityPresenter(SignupActivityView view, SignupActivityModel model){
        this.model = model;
        this.view = view;
    }


    public void create_new_user(String email, String username, String password, String role) {

        model.addDBuser(this, email, username, password, role);
    }
    @Override
    public void displayResult(boolean exist, String role){
        if(exist==true){
            view.accountFound(role);
        }
        else{
            view.user_created(role);
        }
    }
    @Override
    public void authError() {
        view.authError();
    }

    public void uniqueName(){
        view.uniqueUsername();
    }
}
