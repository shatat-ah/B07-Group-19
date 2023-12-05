package com.example.b07_group_19;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {

    @Mock
    LoginActivityView View;

    @Mock
    LoginActivityModel Model;
    @InjectMocks
    LoginActivityPresenter presenter = new LoginActivityPresenter(View,Model);

    @Test
    public void testCheckDBUser_Student() throws InterruptedException{
        String username = "Ousman";
        String role = "Student";
        String password = "Jikz10";

        presenter.checkDBuser(username,role,password);
        verify(Model).queryDB(presenter,username,role,password);
    }

    @Test
    public void testCheckDBUser_Admin() {
        String username = "Bob";
        String role = "Admin";
        String password = "bob123";

        presenter.checkDBuser(username,role,password);
        verify(Model).queryDB(presenter,username,role,password);

    }

    @Test
    public void testCheckDBUser_Admin_UserNotExists() {
        String role = "Admin";

        doNothing().when(View).userNotFound();
        presenter = new LoginActivityPresenter(View, Model);
        presenter.displayResult(false,role);

        verify(View,times(1)).userNotFound();
    }

    @Test
    public void testCheckDBUser_Student_UserNotExists() {
        String role = "Student";

        doNothing().when(View).userNotFound();
        presenter = new LoginActivityPresenter(View, Model);
        presenter.displayResult(false,role);

        verify(View,times(1)).userNotFound();
    }

    @Test
    public void testCheckDBUser_Student_UserExists() {
        String role = "Student";

        doNothing().when(View).userExist(anyString());
        presenter.displayResult(true,role);

        verify(View,times(1)).userExist(role);
    }

    @Test
    public void testCheckDBUser_Admin_UserExists() {
        String role = "Admin";

        doNothing().when(View).userExist(anyString());
        presenter.displayResult(true,role);
        verify(View,times(1)).userExist(role);
    }

    @Test
    public void testMissing_Fields_Username(){
        String username = "";
        String role = "Student";
        String password = "Jikz10";


        doNothing().when(View).missingField();
        presenter.checkDBuser(username,role,password);
        verify(View,times(1)).missingField();
    }

    @Test
    public void testMissing_Fields_Password(){
        String username = "Ousman";
        String role = "Student";
        String password = "";

        doNothing().when(View).missingField();
        presenter.checkDBuser(username,role,password);
        verify(View,times(1)).missingField();
    }

    @Test
    public void testMissing_Fields_Password_and_Username() {
        String username = "";
        String role = "Student";
        String password = "";

        doNothing().when(View).missingField();
        presenter.checkDBuser(username,role,password);
        verify(View,times(1)).missingField();

    }
}