package com.example.b07_group_19;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

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

    /*@Mock
    LoginActivityView View;

    @Mock
    LoginActivityModel Model;

    private LoginActivityPresenter presenter;

    @Test
    public void testCheckDBUser_UserExists_Student() {
        String username = "Ousman";
        String role = "Student";
        String password = "Jikz10";
do
        doNothing().when(Model).queryDB(presenter,anyString(),anyString(),anyString());
        presenter = new LoginActivityPresenter(View, Model);
        presenter.checkDBuser(username,role,password);
        verify(View,times(1)).userExist(role);
    }

    @Test
    public void testCheckDBUser_UserExists_Admin() {
        String username = "Bob";
        String role = "Admin";
        String password = "bob123";

        doNothing().when(Model).queryDB(presenter,anyString(),anyString(),anyString());
        presenter = new LoginActivityPresenter(View, Model);
        presenter.checkDBuser(username,role,password);
        verify(View,times(1)).userExist(role);
    }

    @Test
    public void testCheckDBUser_UserNotExists() {
        String username = "NotOusman";
        String role = "Admin";
        String password = "Jikz10";

        doNothing().when(Model).queryDB(presenter, anyString(), anyString(), anyString());
        presenter = new LoginActivityPresenter(View, Model);
        presenter.checkDBuser(username, role, password);

        verify(View,times(1)).userNotFound();
    }

    @Test
    public void testMissing_Fields_Username(){
        String username = "";
        String role = "Student";
        String password = "Jikz10";

        doNothing().when(View).existing_user(anyString(),anyString(),anyString());
        View.existing_user(username,role,password);
        verify(View,times(1)).missingField();
    }

    @Test
    public void testMissing_Fields_Password(){
        String username = "Ousman";
        String role = "Student";
        String password = "";

        doNothing().when(View).existing_user(anyString(),anyString(),anyString());
        View.existing_user(username,role,password);
        verify(View,times(1)).missingField();
    }

    @Test
    public void testMissing_Fields_Password_and_Username(){
        String username = "";
        String role = "Student";
        String password = "";

        doNothing().when(View).existing_user(anyString(),anyString(),anyString());
        View.existing_user(username,role,password);
        verify(View,times(1)).missingField();
    }*/

}