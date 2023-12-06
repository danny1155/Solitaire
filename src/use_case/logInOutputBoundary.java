package use_case;

public interface logInOutputBoundary {
    void prepareSuccessView(logInOutputData user);

    void prepareFailView(String error);
}
