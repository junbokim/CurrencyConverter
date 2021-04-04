package CurrencyConverter;

public class User {

    private String username;
    private String password;

    private boolean isAdmin;

    User(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public boolean isAdmin(){
        return this.isAdmin;
    }

    public String getUsername(){
        return this.username;
    }

    public boolean tryLogIn(String username, String password){
        return (this.username.equals(username) && this.password.equals(password));
    }
}


