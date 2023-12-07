package entity;

public class SinglePlayerGameEasyCreator extends GameCreator {
    @Override
    public Game createGame() {
        return new SinglePlayerGameEasy();
    }
}
