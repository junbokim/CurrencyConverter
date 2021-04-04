package CurrencyConverter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test void userCreation(){
        User user = new User("Artemis","thehuntress", false);
        assertNotNull(user, "User constructor should return user instance");
    }


    @Test void isAdmin(){
        User standard = new User("Artemis","thehuntress", false);
        User admin = new User("Asteria","ofthestars", true);

        assertFalse(standard.isAdmin(), "User should not be admin");
        assertTrue(admin.isAdmin(), "User should be admin");
    }

    @Test void attemptIncorrectLogin(){
        User user = new User("Artemis","thehuntress", false);

        assertFalse(
                user.tryLogIn("Artemis","ofthestars"),
                "User should not be able to login on incorrect credentials");

        assertFalse(
                user.tryLogIn("Asteria","thehuntress"),
                "User should not be able to login on incorrect credentials");
    }

    @Test void attemptCorrectLogin(){
        User user = new User("Artemis","thehuntress", false);

        assertTrue(
                user.tryLogIn("Artemis","thehuntress"),
                "User should be able to login on correct credentials");
    }

    @Test void getUsername(){
        String usernameTest = "Artemis";
        User user = new User(usernameTest,"thehuntress", true);

        assertEquals(user.getUsername(), usernameTest, "Usernames should match");
    }
}