package interface_adapter.Setup;

import entity.Card;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SetupState {
    private int score;
    private List<Duration> time;
    private int numMoves;
    private List<String> currentlyShownCardsImage;
    private String gameMode;
    private Map<Integer, ArrayList<Card>> columns;

    public SetupState (SetupState copy) {
        score = copy.score;
        time = copy.time;
        numMoves = copy.numMoves;
        currentlyShownCardsImage = copy.currentlyShownCardsImage;
        gameMode = copy.gameMode;
        columns = copy.columns;
    }

    public SetupState() {}

    public void setCurrentlyShownCardsImage(List<String> currentlyShownCardsImage) {
        this.currentlyShownCardsImage = currentlyShownCardsImage;
    }

    public void setColumns(Map<Integer, ArrayList<Card>> columns) {
        this.columns = columns;
    }
    public Map<Integer, ArrayList<Card>> getColumns() {
        return columns;
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
