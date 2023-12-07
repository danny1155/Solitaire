package entity;

import java.util.ArrayList;
import java.util.Map;

public abstract class GameCreator {
    private boolean legalPlacement;
    private boolean paused;
    private GameState gameState;
    public abstract Game createGame();
    private ArrayList<Card> cardsInplay;
}
