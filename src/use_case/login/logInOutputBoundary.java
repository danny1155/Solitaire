package use_case.login;

public interface logInOutputBoundary {
    void prepareSuccessView(logInOutputData user);

    void prepareFailView(String error);
}
