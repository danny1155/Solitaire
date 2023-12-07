package entity;

import use_case.move_card.MoveCardOutputData;

import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

public class FoundationToColumnStrategy implements Strategy{
    private Point point;
    private Card card;
    private Map<Integer, ArrayList<Card>> columns;
    private boolean newCardShown;
    private boolean movedToDifferentColumn;
    private boolean canBeMoved;
    private int score;
    private int movedColumn;

    public FoundationToColumnStrategy (Point point, Card card, Map<Integer, ArrayList<Card>> columns, boolean newCardShown, boolean movedToDifferentColumn) {
        this.point = point;
        this.card = card;
        this.columns = columns;
        this.newCardShown = newCardShown;
        this.movedToDifferentColumn = movedToDifferentColumn;
        this.canBeMoved = false;
        this.score = 0;
        this.movedColumn = -2;
    }

    @Override
    public MoveCardOutputData execute() {
        for (int i = 0; i < 7; i++) {
            int size = columns.get(i + 1).size();
            if (size == 0 && 110 * i + 70 <= point.getX() && point.getX() + 100 <= 110 * i + /*210*/+250 && 0 <= point.getY() && point.getY() + 140 <= 180 &&
                    card.getValue() == 13) {
                canBeMoved = true;
                if (this.movedToDifferentColumn) {
                    movedColumn = i + 1;
                    score = - 15;

                }
                break;
            } else if (size != 0 && 110 * i + 70 <= point.getX() && point.getX() + 100 <= 110 * i + 250 && size * 20 <= point.getY() && point.getY() + 140 <= size * 20 + 180
                    && card.getColor() == 1 - columns.get(i + 1).get(columns.get(i + 1).size() - 1).getColor() &&
                    card.getValue() == columns.get(i + 1).get(columns.get(i + 1).size() - 1).getValue() - 1) {
//                System.out.println("canBeMoved");
                canBeMoved = true;
                //System.out.println("here" + moveCardInputData.getNewCardShown());
                if (this.movedToDifferentColumn) {
                    movedColumn = i + 1;
                    score = 15;
                }
                break;
            }
        }
        return new MoveCardOutputData(canBeMoved, movedColumn, score);
    }
}
