package use_case;

public class logInInputData {
    final private String username;
    final private String password;

    public logInInputData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

}
