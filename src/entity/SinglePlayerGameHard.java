package entity;

import use_case.move_card.MoveCardOutputData;

import java.util.ArrayList;
import java.util.Map;

public class SinglePlayerGameHard implements Game{
    private Strategy strategy;
    @Override
    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public MoveCardOutputData executeStrategy() {
        return null;
    }

    @Override
    public void pauseGame() {

    }

    @Override
    public void setUpGame() {

    }

    @Override
    public String getDeckID() {
        return null;
    }

    @Override
    public String drawCard(int number) {
        return null;
    }

    @Override
    public String getShownCards() {
        return null;
    }

    @Override
    public String getHiddenCards() {
        return null;
    }

    @Override
    public String getCardImageLink(String card) {
        return null;
    }

    @Override
    public Map<Integer, ArrayList<Card>> getColumns() {
        return null;
    }
}
