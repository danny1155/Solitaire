package src.entity;

abstract class Game {
    private boolean legalPlacement;
    private boolean paused;

    public abstract void pauseGame();

    public abstract void setUpGame();

}
