package use_case.setup_game;

import entity.Game;

public class SetupInputData {
    final private String gameMode;

    public SetupInputData(String gameMode) {
        this.gameMode = gameMode;
    }

    String getConfig(){return gameMode;}
}