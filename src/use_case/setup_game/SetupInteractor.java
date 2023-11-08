package use_case.setup_game;

import entity.Game;
import entity.SinglePlayerGame;
import interface_adapter.Setup.SetupPresenter;

import java.util.ArrayList;
import java.util.List;

public class SetupInteractor implements SetupInputBoundary{
    private Game game;
    private SetupOutputBoundary setupPresenter;
    public SetupInteractor(SetupOutputBoundary setupOutputBoundary) {
        this.game = new SinglePlayerGame();
        this.setupPresenter = setupOutputBoundary;
    }
    public void execute(SetupInputData setupInputData) {
        List listShownCardImage = new ArrayList<String>();
        for (String code : game.getShownCards().split(",")) {
            listShownCardImage.add(game.getCardImageLink(code));
        }
        SetupOutputData setupOutputData = new SetupOutputData(listShownCardImage);
        System.out.println(setupOutputData.getCardsShown());
        setupPresenter.prepareSuccessView(setupOutputData);
    }

}
