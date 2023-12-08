package entity;

public class LimitlessTimeGameCreator extends GameCreator{
    @Override
    public Game createGame() {
        return new LimitlessTimeGame();
    }
}
