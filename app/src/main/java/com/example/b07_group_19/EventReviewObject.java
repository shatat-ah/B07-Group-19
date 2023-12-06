package com.example.b07_group_19;

public class EventReviewObject {
    private String summary;
    private int rating;

    public EventReviewObject(){

    }
    public EventReviewObject(String summary, int rating){
        this.summary = summary;
        this.rating = rating;
    }
    public String getSummary() {
        return summary;
    }


    public void setSummary(String summary) {
        this.summary = summary;
    }


    public int getRating() {
        return rating;
    }


    public void setRating(int rating) {
        this.rating = rating;
    }


}

