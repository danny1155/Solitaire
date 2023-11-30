package interface_adapter.Drawcard;

import use_case.draw_card.DrawcardOutputBoundary;
import use_case.draw_card.DrawcardInteractor;

public class DrawcardPresenter implements DrawcardOutputBoundary {
    private DrawcardViewModel drawCardViewModel;

    // Constructor to set the view model
    public DrawcardPresenter(DrawcardViewModel drawCardViewModel) {
        this.drawCardViewModel = drawCardViewModel;
    }
    // You can add attributes and methods for handling the presentation logic

    @Override
    public void drawCardSuccess(DrawcardInteractor.Card drawnCard) {
        // Handle the success scenario, update the view model, etc.
        drawCardViewModel.setDrawn(true);  // Set the appropriate state in your view model
        // Update your UI with the drawn card information
        drawCardViewModel.setDrawnCard(drawnCard);
    }

    @Override
    public void drawCardFailure(String errorMessage) {
        // Handle the failure scenario, show error messages, etc.
        drawCardViewModel.setDrawn(false);  // Set the appropriate state in your view model
        // Update your UI with error information
        drawCardViewModel.setErrorMessage(errorMessage);
    }
}
