package CurrencyConverter;

import java.util.HashMap;
import java.util.Objects;

public class UserData {

    private HashMap<String, User> userLookup;

    UserData() {
        userLookup = new HashMap<>();
    }

    public void addUser(String username, String password, boolean admin){
        User newUser = new User(username, password, admin);
        userLookup.put(username, newUser);
    }

    public User tryLogIn(String username, String password){
        User attemptUser = userLookup.get(username);
        if (Objects.nonNull(attemptUser)){
            if (attemptUser.tryLogIn(username, password)){
                return attemptUser;
            }
        }

        return null;
    }
}
