package com.example.b07_group_19;

public class EventReviewObject {
    private String summary;
    private String email;
    private int rating;
    public EventReviewObject(String summary, int rating, String email){
        this.rating = rating;
        this.summary = summary;
        this.email = email;
    }
}
