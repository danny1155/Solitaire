package use_case.setup_game;

import entity.Game;
import entity.SinglePlayerGame;

import java.util.ArrayList;
import java.util.List;

public class SetupInteractor {
    private Game game;
    public SetupInteractor() {
        this.game = new SinglePlayerGame();

    }
    public void execute() {
        List listShownCardImage = new ArrayList<String>();
        for (String code : game.getShownCards().split(",")) {
            listShownCardImage.add(game.getCardImageLink(code));
        }
        SetupOutputData setupOutputData = new SetupOutputData(listShownCardImage);
        System.out.println(setupOutputData.getCardsShown());
    }

}
