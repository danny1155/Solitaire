package entity;

import use_case.move_card.MoveCardOutputData;

import java.util.ArrayList;
import java.util.Map;

public interface Game {
    void setStrategy(Strategy strategy);
    MoveCardOutputData executeStrategy();
    void pauseGame();
    void setUpGame();
    String getDeckID();
    String drawCard(int number);
    String getShownCards();
    String getHiddenCards();
    String getCardImageLink(String card);
    Map<Integer, ArrayList<Card>> getColumns();
}
