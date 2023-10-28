package use_case.setup_game;

import entity.Game;
import entity.SinglePlayerGame;

public class SetupInteractor {
    public SetupInteractor() {
        Game game = new SinglePlayerGame();
    }
}
