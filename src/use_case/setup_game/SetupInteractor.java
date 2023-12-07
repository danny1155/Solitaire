package use_case.setup_game;

import data_access.GameDataAccessObject;
import entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SetupInteractor implements SetupInputBoundary{

    private SetupOutputBoundary setupPresenter;
    final private GameDataAccessObject gameDataAccessObject;
    public SetupInteractor(SetupOutputBoundary setupOutputBoundary, GameDataAccessObject gameDataAccessObject) {
        // this.game = new SinglePlayerGame();
        this.gameDataAccessObject = gameDataAccessObject;
        this.setupPresenter = setupOutputBoundary;
    }
    public void execute(SetupInputData setupInputData) {
        GameCreator gameCreator;
        if (Objects.equals(setupInputData.getGameMode(), "SinglePlayerGameEasy")) {
            gameCreator = new SinglePlayerGameEasyCreator();
        } else if (Objects.equals(setupInputData.getGameMode(), "SinglePlayerGameHard")) {
            gameCreator = new SinglePlayerGameHardCreator();
        } else {
            gameCreator = new LimitlessTimeGameCreator();
        }
        Game game = gameCreator.createGame();
        gameDataAccessObject.addGame(game);
        //Game game = gameDataAccessObject.getGame(1);
        List<String> listShownCardImage = new ArrayList<>();
        for (String code : game.getShownCards().split(",")) {
            listShownCardImage.add( game.getCardImageLink(code));
        }
        SetupOutputData setupOutputData = new SetupOutputData(listShownCardImage, game.getColumns());
        setupPresenter.prepareSuccessView(setupOutputData);
    }

}
