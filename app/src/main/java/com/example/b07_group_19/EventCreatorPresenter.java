package com.example.b07_group_19;

public class EventCreatorPresenter {

    EventCreatorView view;
    EventCreatorModel model;
    public EventCreatorPresenter(EventCreatorView view, EventCreatorModel model){
        this.model = model;
        this.view = view;
    }

    public void eventFound() {
        view.eventFound();
    }

    public String getUserName() {
        return model.getUserName();
    }

    public void createNewEvent(String title, String description, String creator, String department, int maxParticipants, int day, int month, int year, int hour, int minute) {
        model.addDbEvent(this, title, description, creator, department, maxParticipants, day, month, year, hour, minute);
    }

    public void displayResult(boolean eventExists){
        if(eventExists){
            view.eventFound();
        }
        else{
            view.eventCreated();
        }
    }

    public void eventCreateFailed(){
        view.eventCreateFailed();
    }
}
