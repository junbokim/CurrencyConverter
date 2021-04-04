package CurrencyConverter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDataTest {
    @Test void userDataCreation(){
        UserData userData = new UserData();
        assertNotNull(userData, "User constructor should not return null");
    }


    @Test void addUser(){
        UserData userData = new UserData();
        userData.addUser("Artemis","thehuntress", false);
        assertNotNull(userData, "Basically, this shouldn't crash");
    }

    @Test void tryLogin(){
        UserData userData = new UserData();
        userData.addUser("Artemis","thehuntress", false);
        userData.addUser("Asteria","ofthestars", true);

        // Valid logins
        User loggedUser = userData.tryLogIn("Artemis", "thehuntress");
        assertEquals(loggedUser.getUsername(), "Artemis", "Logged in user should match");
        assertFalse(loggedUser.isAdmin(), "Logged in user should match");

        loggedUser = userData.tryLogIn("Asteria", "ofthestars");
        assertEquals(loggedUser.getUsername(), "Asteria", "Logged in user should match");
        assertTrue(loggedUser.isAdmin(), "Logged in user should match");

        User nullUser = userData.tryLogIn("Asteria", "thehuntress");
        assertNull(nullUser, "No valid user should be returned");

        nullUser = userData.tryLogIn("Jenkins", "theannoying");
        assertNull(nullUser, "No valid user should be returned");

    }

}
