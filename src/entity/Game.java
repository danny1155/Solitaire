package entity;

import java.util.ArrayList;

public abstract class Game {
    private boolean legalPlacement;
    private boolean paused;
    private GameState gameState;
    public abstract void pauseGame();
    public abstract void setUpGame();
    public abstract String getDeckID();
    public abstract String drawCard(int number);
    public abstract String getShownCards();
    public abstract String getHiddenCards();
    public abstract String getCardImageLink(String card);
    private ArrayList<Card> cardsInplay;
}
