package use_case.setup_game;

public class SetupInputData {
    // Add fields as needed for setup data
    private String gameMode;  // Example field for game mode selection

    public SetupInputData(String gameMode) {
        this.gameMode = gameMode;
    }

    public String getGameMode() {
        return gameMode;
    }

    // Add other getters and setters for additional setup data
}