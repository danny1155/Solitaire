package use_case;

public class logInOutputData {
    private final String username;

    private boolean useCaseFailed;

    public logInOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }


}
