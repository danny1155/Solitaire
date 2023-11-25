package interface_adapter.Setup;

import java.time.Duration;
import java.util.List;

public class SetupState {
    private int score;
    private List<Duration> time;
    private int numMoves;
    private List<String> currentlyShownCardsImage;
    private String gameMode;

    public SetupState (SetupState copy) {
        score = copy.score;
        time = copy.time;
        numMoves = copy.numMoves;
        currentlyShownCardsImage = copy.currentlyShownCardsImage;
        gameMode = copy.gameMode;
    }

    public SetupState() {}

    public void setCurrentlyShownCardsImage(List<String> currentlyShownCardsImage) {
        this.currentlyShownCardsImage = currentlyShownCardsImage;
    }

    public List<String> getCurrentlyShownCardsImage() {
        return currentlyShownCardsImage;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public String getGameMode() {
        return this.gameMode;
    }
}