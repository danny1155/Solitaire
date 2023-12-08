package entity;

import use_case.move_card.MoveCardOutputData;

import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

public class ToFoundationStrategy implements Strategy{
    private boolean canBeMoved;
    private int movedColumn;
    private int scoreChange;
    private boolean movedToDifferentColumn;
    private boolean newCardShown;
    private Card card;
    private Point point;
    private Map<Integer, ArrayList<Card>> columns;
    public ToFoundationStrategy(Point point, Card card, Map<Integer, ArrayList<Card>> columns, boolean newCardShown, boolean movedToDifferentColumn) {
        this.card = card;
        this.point = point;
        this.columns = columns;
        this.movedToDifferentColumn = movedToDifferentColumn;
        canBeMoved = false;
        scoreChange = 0;
        movedColumn = -2;
        this.newCardShown = newCardShown;

    }

    @Override
    public MoveCardOutputData execute() {
        System.out.println("By foundation X");
        if (point.getY() >= -30 && point.getY() <= 100){
            System.out.println("By foundation Y");

            if (card.getName().substring(1).equals("C")) {
                if (card.getValue() == 1) {
                    System.out.println("First foundation for Club");
                    canBeMoved = true;
                    movedColumn = 8;
                    if (this.movedToDifferentColumn && newCardShown) {
                        scoreChange = 15;
                    } else if (this.movedToDifferentColumn) {
                        scoreChange = 10;
                    } else {
                        scoreChange = 0;
                    }

                } else if (!columns.get(8).isEmpty() && card.getValue() == columns.get(8).get(columns.get(8).size() - 1).getValue() + 1) {
                    System.out.println("Add card to Club Foundation");
                    canBeMoved = true;
                    movedColumn = 8;
                    if (this.movedToDifferentColumn && newCardShown) {
                        scoreChange = 15;
                    } else if (this.movedToDifferentColumn) {
                        scoreChange = 10;
                    } else {
                        scoreChange = 0;
                    }
                }
            }
        } else if (point.getY() >= 120 && point.getY() <= 250) {
            System.out.println("By foundation Y");
            if (card.getName().substring(1).equals("S")){
                if (card.getValue() == 1) {
                    System.out.println("First foundation for Spade");
                    canBeMoved = true;
                    movedColumn = 9;
                    if (this.movedToDifferentColumn && newCardShown) {
                        scoreChange = 15;
                    } else if (this.movedToDifferentColumn) {
                        scoreChange = 10;
                    } else {
                        scoreChange = 0;
                    }
                } else if (!columns.get(9).isEmpty() && card.getValue() == columns.get(9).get(columns.get(9).size() - 1).getValue() + 1) {
                    System.out.println("Add card to Spade Foundation");
                    canBeMoved = true;
                    movedColumn = 9;
                    if (this.movedToDifferentColumn && newCardShown) {
                        scoreChange = 15;
                    } else if (this.movedToDifferentColumn) {
                        scoreChange = 10;
                    } else {
                        scoreChange = 0;
                    }
                }
            }
        } else if (point.getY() >= 270 && point.getY() <= 400) {
            System.out.println("By foundation Y");
            if (card.getName().substring(1).equals("D")) {
                if (card.getValue() == 1) {
                    System.out.println("First foundation for Diamond");
                    canBeMoved = true;
                    movedColumn = 10;
                    if (this.movedToDifferentColumn && newCardShown) {
                        scoreChange = 15;
                    } else if (this.movedToDifferentColumn) {
                        scoreChange = 10;
                    } else {
                        scoreChange = 0;
                    }
                } else if (!columns.get(10).isEmpty() && card.getValue() == columns.get(10).get(columns.get(10).size() - 1).getValue() + 1) {
                    System.out.println("Add card to Diamond Foundation");
                    canBeMoved = true;
                    movedColumn = 10;
                    if (this.movedToDifferentColumn && newCardShown) {
                        scoreChange = 15;
                    } else if (this.movedToDifferentColumn) {
                        scoreChange = 10;
                    } else {
                        scoreChange = 0;
                    }
                }
            }
        } else if (point.getY() >= 420 && point.getY() <= 550) {
            System.out.println("By foundation Y");
            if (card.getName().substring(1).equals("H")) {
                if (card.getValue() == 1) {
                    System.out.println("First foundation for Heart");
                    movedColumn = 11;
                    canBeMoved = true;
                    if (this.movedToDifferentColumn && newCardShown) {
                        scoreChange = 15;
                    } else if (this.movedToDifferentColumn) {
                        scoreChange = 10;
                    } else {
                        scoreChange = 0;
                    }
                } else if (!columns.get(11).isEmpty() && card.getValue() == columns.get(11).get(columns.get(11).size() - 1).getValue() + 1) {
                    System.out.println("Add card to Heart Foundation");
                    movedColumn = 11;
                    canBeMoved = true;
                    if (this.movedToDifferentColumn && newCardShown) {
                        scoreChange = 15;
                    } else if (this.movedToDifferentColumn) {
                        scoreChange = 10;
                    } else {
                        scoreChange = 0;
                    }
                }
            }
        }
        return new MoveCardOutputData(canBeMoved, movedColumn, scoreChange);
    }
}
