package com.example.b07_group_19;

public class FeedbackScrollObject {
        String event_name;
        String time;

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public FeedbackScrollObject(String event_name, String time){
            this.event_name = event_name;
            this.time = time;
        }

}
