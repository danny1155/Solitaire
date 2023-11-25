package interface_adapter.Setup;

import entity.Card;

import javax.swing.*;
import java.awt.*;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SetupState {
    private int score;
    private List<Duration> time;
    private int numMoves;
    private List<String> currentlyShownCardsImage;
    private String gameMode;
    private Map<Integer, ArrayList<Card>> columns;
   // private HashMap<Integer, ArrayList<JLabel>> moveableCards = new HashMap<Integer, ArrayList<JLabel>>();
    private boolean canBeMoved;
    private int movedColumn;
    private boolean isNewGame;

    public SetupState (SetupState copy) {
        score = copy.score;
        time = copy.time;
        numMoves = copy.numMoves;
        currentlyShownCardsImage = copy.currentlyShownCardsImage;
        gameMode = copy.gameMode;
        columns = copy.columns;
        canBeMoved = copy.canBeMoved;
        movedColumn = copy.movedColumn;
        isNewGame = copy.isNewGame;
        //moveableCards = copy.moveableCards;
    }

    public SetupState() {}

    public void setCurrentlyShownCardsImage(List<String> currentlyShownCardsImage) {
        this.currentlyShownCardsImage = currentlyShownCardsImage;
    }
    public void setMovedColumn(int movedColumn) {
        this.movedColumn = movedColumn;
    }
    public int getMovedColumn() {return movedColumn;}

//    public void setMoveableCards(int i, ArrayList<JLabel> moveableCardsList) {
//        this.moveableCards.put(i, moveableCardsList);
//    }
//    public void setMoveableCards(int i, int j, JLabel cardLabel) {
//        this.moveableCards.get(i).add(j, cardLabel);
//    }
//    public HashMap<Integer, ArrayList<JLabel>> getMoveableCards() {return getMoveableCards();}

    public void setCanBeMoved(boolean canBeMoved) {this.canBeMoved = canBeMoved;}
    public boolean getCanBeMoved() {return canBeMoved;}

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
    public void setIsNewGame(boolean isNewGame) {this.isNewGame = isNewGame;}
    public boolean getIsNewGame() {return this.isNewGame;}
}
