package entity;

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
}
