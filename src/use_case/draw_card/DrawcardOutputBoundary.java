package use_case.draw_card;

public interface DrawcardOutputBoundary {
    void drawCardSuccess(DrawcardInteractor.Card drawnCard);
    void drawCardFailure(String errorMessage);
}