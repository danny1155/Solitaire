package use_case.setup_game;

import entity.Game;
import entity.SinglePlayerGame;
import entity.Card;

import java.util.ArrayList;
import java.util.List;

public class SetupInteractor implements SetupInputBoundary{

    private SetupOutputBoundary setupPresenter;
    public SetupInteractor(SetupOutputBoundary setupOutputBoundary) {
        // this.game = new SinglePlayerGame();
        this.setupPresenter = setupOutputBoundary;
    }
    public void execute(SetupInputData setupInputData) {
        Game game = new SinglePlayerGame();
        List<String> listShownCardImage = new ArrayList<>();
        for (String code : game.getShownCards().split(",")) {
            listShownCardImage.add( game.getCardImageLink(code));
        }
        SetupOutputData setupOutputData = new SetupOutputData(listShownCardImage, game.getColumns());
        setupPresenter.prepareSuccessView(setupOutputData);
    }

}
