package interface_adapter.Drawcard;

import use_case.draw_card.DrawcardInteractor;

public class DrawcardViewModel {
    private boolean isDrawn;
    private DrawcardInteractor.Card drawnCard;
    private String errorMessage;

    public boolean isDrawn() {
        return isDrawn;
    }

    public void setDrawn(boolean drawn) {
        isDrawn = drawn;
    }

    public DrawcardInteractor.Card getDrawnCard() {
        return drawnCard;
    }

    public void setDrawnCard(DrawcardInteractor.Card drawnCard) {
        this.drawnCard = drawnCard;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
