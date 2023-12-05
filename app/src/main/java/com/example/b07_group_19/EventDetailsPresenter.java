package com.example.b07_group_19;

import java.util.List;

public class EventDetailsPresenter {

    EventDetailsView view;
    EventDetailsModel model;
    public EventDetailsPresenter(EventDetailsView view, EventDetailsModel model){
        this.model = model;
        this.view = view;
    }

    public void getEventInfo(String title) {
        model.getEventInfo(this, title);
    }

    public void updateEventInfo(Event event) {
        view.currentEvent = event;
    }

    public String getUserEmail() {
        model.getUserEmail();
    }
    
    public void eventNotFound() {
        view.eventNotFound();
    }
    public void eventLoadFailed() {
        view.eventLoadFailed();
    }
}
