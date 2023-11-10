package entity;

import java.text.FieldPosition;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameState {
    private int score;
    private List<Duration> time;
    private int numMoves;
    private List<String> moveableCards;
    private HashMap availablePositionsAndCardsForEach;

    public GameState (String moveableCards) {
        List<String> moveableCardsList = new ArrayList<>();
        int i = 0;
        while (i < moveableCards.length()) {
            moveableCardsList.add(moveableCards.substring(i, i + 2));
            i = i + 2;
        }
        this.moveableCards = moveableCardsList;
    }
    public List<String> getMoveableCards() {
        return moveableCards;
    }
    public HashMap getAvailablePositionsAndCardsForEach() {
        return availablePositionsAndCardsForEach;
    }
}
