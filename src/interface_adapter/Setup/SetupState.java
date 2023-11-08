package interface_adapter.Setup;

import java.time.Duration;
import java.util.List;

public class SetupState {
    private int score;
    private List<Duration> time;
    private int numMoves;
    private String currentlyShownCards;
    private String gameMode;

    public SetupState (SetupState copy) {
        score = copy.score;
        time = copy.time;
        numMoves = copy.numMoves;
        currentlyShownCards = copy.currentlyShownCards;
        gameMode = copy.gameMode;
    }

    public SetupState() {}

    public void setCurrentlyShownCards(String currentlyShownCards) {
        this.currentlyShownCards = currentlyShownCards;
    }

    public String getCurrentlyShownCards() {
        return currentlyShownCards;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public String getGameMode() {
        return this.gameMode;
    }
}
