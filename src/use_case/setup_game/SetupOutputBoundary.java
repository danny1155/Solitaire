package use_case.setup_game;

public interface SetupOutputBoundary {
    void prepareSuccessView(SetupOutputData game);

    void prepareFailView(String error);
}