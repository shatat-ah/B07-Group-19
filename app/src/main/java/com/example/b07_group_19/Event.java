package com.example.b07_group_19;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Event {

    private String title;
    private String description;
    private ArrayList<String> rsvpList;
    private String department;
    private int maxParticipants;
    private int currentParticipants;
    private LocalDateTime time;
    private String creator;
    public Event(String title, String description, String creator, String department, int maxParticipants, int day, int month, int year, int hour, int minute){
        try{
            this.time = LocalDateTime.of(year, month, day, hour, minute);
        } catch (DateTimeException e) {
            //With good UX this should never need to run
        }
        this.title = title;
        this.department = department;
        this.maxParticipants = maxParticipants;
        this.description = description;
        this.creator = creator;
        this.rsvpList = new ArrayList<String>();
        this.currentParticipants = 0;
    }

    public Event(){
    }

    public String getTitle(){
        return this.title;
    }


    public String getDescription(){
        return this.description;
    }


    public ArrayList<String> getRvspList(){
        return this.rsvpList;
    }


    public int addParticipant(String student) {
        if (this.currentParticipants == this.maxParticipants)
            return 0;
        this.rsvpList.add(student);
        this.currentParticipants++;
        return 1;
    }
    public boolean containsParticipant(String student) {
        return (this.rsvpList.contains(student));
    }


    public int removeParticipant(String student) {
        boolean removal = this.rsvpList.remove(student);
        if (removal) //If removal succeeded
            return 1;
        return 0; //With good UX this should never need to run
    }


    public int getMaxParticipants() {
        return this.maxParticipants;
    }


    public int getCurrentParticipants() {
        return this.currentParticipants;
    }


    public LocalDateTime getTime(){
        return this.time;
    }


    public String getTimeAsString(){ //Supplant this into the view model instead when that's done
        DateTimeFormatter format = DateTimeFormatter.ofPattern("E, MMM dd, yyyy, HH:mm");
        return this.time.format(format);
    }


    public int setTime(int year, int month, int day, int hour, int minute) throws DateTimeException {
        try {
            this.time = LocalDateTime.of(year, month, day, hour, minute);
            return 1;
        } catch (DateTimeException e) {
            return 0; //With good UX this should never need to run
        }
    }


    public String getCreator(){
        return this.creator;
    }


}

