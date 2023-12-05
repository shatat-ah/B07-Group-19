package com.example.b07_group_19;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
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

    private LoginActivityPresenter presenter;

    @Test
    public void testCheckDBUser_Student() {
        String username = "Ousman";
        String role = "Student";
        String password = "Jikz10";

        presenter = new LoginActivityPresenter(View, Model);
        doNothing().when(Model).queryDB(presenter,anyString(),anyString(),anyString());
        presenter.checkDBuser(username,role,password);
        verify(Model,times(1)).queryDB(presenter,username,role,password);
    }

    @Test
    public void testCheckDBUser_Admin() {
        String username = "Bob";
        String role = "Admin";
        String password = "bob123";

        presenter = new LoginActivityPresenter(View, Model);
        doNothing().when(Model).queryDB(presenter,anyString(),anyString(),anyString());
        presenter.checkDBuser(username,role,password);
        verify(Model,times(1)).queryDB(presenter,username,role,password);
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
        presenter = new LoginActivityPresenter(View, Model);
        presenter.displayResult(true,role);

        verify(View,times(1)).userExist(role);
    }

    @Test
    public void testCheckDBUser_Admin_UserExists() {
        String role = "Admin";

        presenter = new LoginActivityPresenter(View, Model);
        presenter.displayResult(false,role);

        verify(View,times(1)).userExist(role);
    }

    @Test
    public void testMissing_Fields_Username(){
        String username = "";
        String role = "Student";
        String password = "Jikz10";

        LoginActivityView loginview = new LoginActivityView();
        doNothing().when(loginview).existing_user(anyString(),anyString(),anyString());
        loginview.existing_user(username,role,password);
        verify(loginview,times(1)).missingField();
    }

    @Test
    public void testMissing_Fields_Password(){
        String username = "Ousman";
        String role = "Student";
        String password = "";

        LoginActivityView loginview = new LoginActivityView();
        doNothing().when(loginview).existing_user(anyString(),anyString(),anyString());
        loginview.existing_user(username,role,password);
        verify(loginview,times(1)).missingField();
    }

    @Test
    public void testMissing_Fields_Password_and_Username() {
        String username = "";
        String role = "Student";
        String password = "";

        LoginActivityView loginview = new LoginActivityView();
        doNothing().when(loginview).existing_user(anyString(),anyString(),anyString());
        loginview.existing_user(username,role,password);
        verify(loginview,times(1)).missingField();

    }
}