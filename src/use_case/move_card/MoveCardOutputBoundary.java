package use_case.move_card;

public interface MoveCardOutputBoundary {
    void prepareSuccessView(MoveCardOutputData game);

    void prepareFailView(String error);
}
