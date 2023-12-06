package interface_adapter.Drawcard;

import use_case.draw_card.DrawcardInputBoundary;
import use_case.draw_card.DrawcardInputData;
import use_case.draw_card.DrawcardInteractor;

public class DrawcardController {
    private final DrawcardInteractor drawcardInteractor;

    public DrawcardController(DrawcardInteractor drawcardInteractor) {
        this.drawcardInteractor = drawcardInteractor;
    }

    public void drawCard() {
        // Create an instance of DrawcardInputData if needed
        DrawcardInputData inputData = new DrawcardInputData();

        // Call the drawCard method in DrawcardInteractor
        drawcardInteractor.drawCard(inputData);
    }
}