package use_case.setup_game;

import entity.Game;
import entity.SinglePlayerGame;

public class SetupInteractor {
    private Game game;
    public SetupInteractor() {
        this.game = new SinglePlayerGame();

    }
    public void execute() {
        SetupOutputData setupOutputData = new SetupOutputData(game.getShownCards());
    }
}
