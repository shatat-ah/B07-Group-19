package com.example.b07_group_19;

import java.util.List;

public class EventListPresenter {

    EventListView view;
    EventListModel model;
    public EventListPresenter(EventListView view, EventListModel model){
        this.model = model;
        this.view = view;
    }

    public void getEventList() {
        model.getEventInfo(this);
    }

    public void updateEventList(List<Event> updatedList) {
        view.eventsList = (updatedList);
    }
    public void eventLoadFailed(){
        view.eventLoadFailed();
    }
}
