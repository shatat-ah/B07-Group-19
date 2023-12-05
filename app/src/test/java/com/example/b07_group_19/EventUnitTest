package com.example.b07_group_19;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

import android.text.Editable;
import android.view.View;
import android.widget.EditText;

import java.time.*;
import java.time.format.*;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EventUnitTest {

    @Test
    public void checkInvalidCreateTime(){
        Event event = new Event("EventName", "Description (10 characters)", "admin", "CS", 10, 1, 1, 2023, 12, 30);
        Assertions.AssertThrows(DateTimeException);
    }

    @Test
    public void checkInvalidSetTime(){
        Event event = new Event("EventName", "Description (10 characters)", "admin", "CS", 10, 1, 1, 2023, 12, 30);
        AssertTrue(event.setTime(2023, 20, 1, 12, 30) == 0);
    }

    @Test
    public void checkGetTime(){
        Event event = new Event("EventName", "Description (10 characters)", "admin", "CS", 10, 1, 1, 2023, 12, 30);
        String time = event.getTime();
        LocalDateTime newTime = LocalDateTime.of(2023, 1, 1, 12, 30);
        assertTrue(time.equals(newTime));
    }

    @Test
    public void checkGetTimeAsString(){
        Event event = new Event("EventName", "Description (10 characters)", "admin", "CS", 10, 1, 1, 2023, 12, 30);
        String time = event.getTimeAsString();
        assertEquals(time, "Sun, Jan 1, 2023, 12:30");
    }

    @Test
    public void checkSetTime(){
        Event event = new Event("EventName", "Description (10 characters)","admin", "CS", 10, 1, 1, 2023, 12, 30);
        Event event2 = new Event("Event2Name", "Description2 (10 characters)","admin", "CS", 10, 1, 6, 2024, 12, 30);
        event.setTime(2024, 6, 3,8, 40);
        assertTrue(event.getTime() == event2.getTime());
    }

    @Test
    public void checkCreator(){
        Event event = new Event("EventName", "Description (10 characters)","admin", "CS", 10, 1, 1, 2023, 12, 30);
        String creator = event.getCreator();
        assertTrue(creator == "admin");
    }

    @Test
    public void checkGetTitle(){
        Event event = new Event("EventName", "Description (10 characters)","admin", "CS", 10, 1, 1, 2023, 12, 30);
        assertEquals(event.getTitle(), "EventName");
    }

    @Test
    public void checkGetDescription){
        Event event = new Event("EventName", "Description (10 characters)","admin", "CS", 10, 1, 1, 2023, 12, 30);
        assertEquals(event.getDescription(), "Description (10 characters)");
    }

    @Test
    public void checkGetRvspList(){
        Event event = new Event("EventName", "Description (10 characters)","admin", "CS", 10, 1, 1, 2023, 12, 30);
        Student student = "student";
        List<String> dummy = new ArrayList<Student>();
        assertTrue(event.getRvspList().equals(dummy));
    }

    @Test
    public void checkAddParticipant(){
        Event event = new Event("EventName", "Description (10 characters)","admin", "CS", 10, 1, 1, 2023, 12, 30);
        String student = "email";
        event.addParticipant(student);
        List<String> dummy = new ArrayList<Student>();
        dummy.add(student);
        assertTrue(event.getRvspList().equals(dummy));
    }

    public void checkContainsParticipant(){
        Event event = new Event("EventName", "Description (10 characters)","admin", "CS", 10, 1, 1, 2023, 12, 30);
        String student = "email";
        String studentCopy = "email";
        event.addParticipant(student);
        assertTrue(event.containsParticipant(studentCopy));
    }

    public void checkDoesNotContainParticipant(){
        Event event = new Event("EventName", "Description (10 characters)","admin", "CS", 10, 1, 1, 2023, 12, 30);
        String student = "email";
        String studentDifferent = "emailDifferent";
        event.addParticipant(student);
        assertFalse(event.containsParticipant(studentDifferent));
    }

    @Test
    public void checkRemoveParticipant(){
        Event event = new Event("EventName", "Description (10 characters)","admin", "CS", 10, 1, 1, 2023, 12, 30);
        String student = "email1";
        event.addParticipant(student);
        event.removeParticipant(student);
        List<String> dummy = new List(Student);
        assertTrue(event.getRvspList.equals(dummy));
    }

    @Test
    public void checkGetMaxParticipants(){
        Event event = new Event("EventName", "Description (10 characters)","admin", "CS", 10, 1, 1, 2023, 12, 30);
        assertEquals(event.getMaxParticipants(), 10);
    }

    @Test
    public void checkGetCurrentParticipants(){
        Event event = new Event("EventName", "Description (10 characters)","admin", "CS", 10, 1, 1, 2023, 12, 30);
        assertEquals(event.getCurrentParticipants(), 0);
    }

    @Test
    public void checkInvalidRemoveParticipant(){
        Event event = new Event("EventName", "Description (10 characters)","admin", "CS", 10, 1, 1, 2023, 12, 30);
        String student = "email1";
        assertTrue(event.removeParticipant(student) == 0);
    }

    @Test
    public void checkFullRvspList(){
        Event event = new Event("EventName", "Description (10 characters)","admin", 1, 1, 1, 2023, 12, 30);
        String student = "email1";
        String student2 = "email2";
        event.addParticipant(student);
        assertTrue(event.addParticipant(student2) == 0);
    }
}
