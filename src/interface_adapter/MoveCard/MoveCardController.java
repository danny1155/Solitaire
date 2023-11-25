package interface_adapter.MoveCard;

import entity.Card;
import use_case.move_card.MoveCardInputBoundary;
import use_case.move_card.MoveCardInputData;
import use_case.setup_game.SetupInputBoundary;
import use_case.setup_game.SetupInputData;

import java.awt.*;

public class MoveCardController {
    final MoveCardInputBoundary moveCardInteractor;

    public MoveCardController(MoveCardInputBoundary moveCardInteractor) {
        this.moveCardInteractor = moveCardInteractor;
    }

    public void execute(Point cardPoint, Card card) {
        MoveCardInputData moveCardInputData = new MoveCardInputData(cardPoint, card);
        moveCardInteractor.execute(moveCardInputData);

    }
}
